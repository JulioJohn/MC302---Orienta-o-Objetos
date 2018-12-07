

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Comparable;
import java.util.Collections;

public class Perfil implements Comparable<Perfil>, Salvavel
{
	private char sexo;
	private final String dataNascimento;
	private String cidade;
	private String estado;
	private String telefone;
	private boolean fumante;
	private float avaliacao;
	private Caroneiro caroneiro;
	private Caronante caronante;
	private Usuario usuario;
	
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public boolean isFumante() {
		return fumante;
	}
	public void setFumante(boolean fumante) {
		this.fumante = fumante;
	}
	public float getAvaliacao() {
		return atualizarAvaliacao();
	}
	public void setAvaliacao(float avaliacao) {
		this.avaliacao = avaliacao;
	}
	public Caroneiro getCaroneiro() {
		return caroneiro;
	}
	public void setCaroneiro(Caroneiro caroneiro) {
		this.caroneiro = caroneiro;
	}
	public Caronante getCaronante() {
		return caronante;
	}
	public void setCaronante(Caronante caronante) {
		this.caronante = caronante;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Perfil(char sex, String date, String city, String state, String telephone, boolean smoker, float rating, Usuario user)
	{
		dataNascimento = date;
		setSexo(sex);
		setCidade(city);
		setEstado(state);
		setTelefone(telephone);
		setFumante(smoker);
		setAvaliacao(rating);
		
		setUsuario(user);
		user.setPerfil(this);
	}
	
	public Perfil()
	{
		dataNascimento = "00/00/0000";
		setSexo('0');
		setCidade("Desconhecido");
		setEstado("Desconhecido");
		setTelefone("Desconhecido");
	}

	public String toString() 
	{
		String out = "[Perfil]\n"; 
		out += "Sexo: " + getSexo() + "\n";
		out += "Data Nascimento: " + getDataNascimento() + "\n";
		out += "Cidade: " + getCidade() + "\n";
		out += "Estado: " + getEstado() + "\n";
		out += "Telefone: " + getTelefone() + "\n";
		out += "Fumante: " + isFumante() + "\n";
		out += "Avaliacao: " + atualizarAvaliacao() + "\n";
		return out;
	}
	
	//atualizo a avaliacao quando chamo o toString da classe
	public float atualizarAvaliacao()
	{
		float notaAvaliacao = 0, notaCaroneiro = 0, notaCaronante = 0; //notaCaroneiro � a nota dada pelos integrantes nas corridas como caroneiro
		int i, j;
		
		for(i = 0; i < this.caroneiro.getCaronas().size(); i++)
		{
			notaCaroneiro += this.caroneiro.getCaronas().get(i).getAvaliacao();
		}
		
		for(j = 0; j < this.caronante.getCaronas().size(); j++)
		{
			notaCaronante += this.caronante.getCaronas().get(j).getAvaliacao();
		}
		
		notaAvaliacao = (notaCaronante + notaCaroneiro) / (i + j); 
		
		this.avaliacao = notaAvaliacao;
		
		return notaAvaliacao;
	}
	
	@Override //Para ordernar agora eh soh utilizar Collections.sort(lista) na main
	public int compareTo(Perfil outroPerfil) {
		if (this.avaliacao < outroPerfil.avaliacao){
			return -1;
	    }
	    if (this.avaliacao > outroPerfil.avaliacao){
	    	return 1;
	    }
	    return 0;
	}
	
	public void salvarParaArquivo()
	{
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Perfil.txt", true)));
			out.writeUTF(this.toString());
			this.caronante.salvarParaArquivo();
			this.caroneiro.salvarParaArquivo();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(out != null)
					out.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}