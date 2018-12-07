
import java.util.ArrayList;

public abstract class Grupo implements Salvavel
{
	private int id;
	private static int geradorId;
	private String nome;
	private String descricao;
	private ArrayList<GrupoUsuario> membros;
	private Usuario dono;
	
	public int getId() {
		return id;
	}
	public static int getGeradorId() {
		return geradorId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ArrayList<GrupoUsuario> getMembros() {
		return membros;
	}
	public Usuario getDono() {
		return dono;
	}
	public void setDono(Usuario dono) {
		this.dono = dono;
	}
	
	public abstract void adicionarMembro(Usuario usuario);
	
	public Grupo()
	{
		this.membros = new ArrayList<GrupoUsuario>();
		geradorId++;
		setId(geradorId);
		setNome("Nao inicializado");
		setDescricao("Nao inicializado");
	}
	
	public Grupo(int id, String nome, String descricao)
	{
		this.membros = new ArrayList<GrupoUsuario>();
		geradorId++;
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public String toString()
	{
		String out = "[Grupo]\n"; 
		out += "Id: " + getId() + "\n";
		out += "Gerador Id: " + getGeradorId() + "\n";
		out += "Nome: " + getNome() + "\n";
		out += "Descricao: " + getDescricao() + "\n";
		for(int i = 0; i < this.membros.size(); i++) 
		{
			out += "Membros: \n" + membros.get(i).getUsuario().toString() + "\n";
		}
		return out;
	}
	
	public void alterarDono(Usuario antigoDono, Usuario novoDono)
	{
		if(dono == antigoDono)
		{
			setDono(novoDono);
		}
	}
	
	public boolean checarPresencaUsuario(Usuario usuario1)
	{
		SistemaCaronaExcecao excecao1 = new SistemaCaronaExcecao("Usuario " + usuario1.getNome() + " nao pertence ao grupo!");
		try {
			for(int i = 0; i < getMembros().size(); i++)
			{
				if(getMembros().get(i).getUsuario() == usuario1) 
					return true;
			}
			throw excecao1;
		}
		catch(SistemaCaronaExcecao novaExcecao1){
			System.err.println(novaExcecao1);
		}
		return false;
	}
	
}
