

import java.util.ArrayList;
import java.io.*;

public class Usuario implements Salvavel
{
	private int id;
	private String nome;
	private String email;
	private String senha;
	private boolean status;
	private ArrayList <GrupoUsuario> grupos;
	private Perfil perfil;
	private static int geradorId;
	
	public int getId() {
		return id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public static int getGeradorId() {
		return geradorId;
	}

	public ArrayList<GrupoUsuario> getGrupos() {
		return grupos;
	}

	public void setGrupos(ArrayList<GrupoUsuario> grupos) {
		this.grupos = grupos;
	}

	public Usuario()
	{
		grupos = new ArrayList<GrupoUsuario>();
		geradorId++;
		setId(geradorId);
		setNome("Desconhecido");
		setEmail("Desconhecido");
		setSenha("Nao cadastrada");
		setStatus(false);
		
		Perfil perfilVazio = new Perfil();
		perfilVazio.setUsuario(this);
	}
	
	public Usuario(String nome1, String email1, String senha1, boolean status1)
	{
		grupos = new ArrayList<GrupoUsuario>();
		geradorId++;
		setId(geradorId);
		setNome(nome1);
		setEmail(email1);
		setSenha(senha1);
		setStatus(status1);
		
		Perfil perfil1 = new Perfil();
		setPerfil(perfil1);
		perfil1.setUsuario(this); //relacionamento
	}
	
	public void adicionarGrupo(GrupoPublico group)
	{
		GrupoUsuario novoGrupo = new GrupoUsuario(this.id, this, group);
		this.grupos.add(novoGrupo);
		group.getMembros().add(novoGrupo);
	}
	
	public GrupoPublico criarGrupoPublico(int id, String nome, String descricao)
	{
		Grupo novoGrupoPublico = new GrupoPublico(id, nome, descricao);
		novoGrupoPublico.setDono(this);
		adicionarGrupo((GrupoPublico)novoGrupoPublico);
		return (GrupoPublico)novoGrupoPublico;
	}
	
	public GrupoPrivado criarGrupoPrivado(int id, String nome, String descricao)
	{
		//criar grupo
		Grupo novoGrupoPrivado = new GrupoPrivado(id, nome, descricao);
		//escolher o dono
		novoGrupoPrivado.setDono(this);
		//criar a ligacao grupoUsuario
		GrupoUsuario novoGrupo = new GrupoUsuario(this.id, this, novoGrupoPrivado);
		//adicionar aos membros do grupo
		novoGrupoPrivado.adicionarMembro(this);
		//adicionar a lista de grupos do usuario
		grupos.add(novoGrupo);
		return (GrupoPrivado)novoGrupoPrivado;
	}
	
	//para adicionar ao grupo, usu1 devera ser o dono do grupo
	public void adicionarUsuarioAUmGrupo(GrupoPrivado grupo, Usuario usu1, Usuario usu2)
	{
		SistemaCaronaExcecao excecao1 = new SistemaCaronaExcecao("Usuario nao eh dono do grupo, impossivel adicionar ao grupo!");
		try //pode haver uma excecao
		{
			if(grupo.getDono() != usu1) { //caso em que ocorre a excecao
				throw excecao1; //joga a excecao que eu criei acima na tela
			}
			grupo.adicionarMembro(usu2);
		}
		catch(SistemaCaronaExcecao novaExcecao1)
		{
			System.err.println(novaExcecao1); //imprime a excecao, caso o try pegue-a
		}
	}
	
	public void removerGrupo(GrupoPublico grupo)
	{
		SistemaCaronaExcecao excecao1 = new SistemaCaronaExcecao("Usuario " + this.getNome() + " nao participa do grupo publico!");
		try {			
			for(int i = 0; i < grupos.size(); i++)
			{
				if(this.grupos.get(i).getGrupo() == grupo)
				{	
					System.out.println("Usuario " + this.getNome() + " removido com sucesso do grupo publico " + grupo.getNome());
					grupos.remove(i);
					return;
				}
			}
			throw excecao1;
		}
		catch(SistemaCaronaExcecao novaExcecao1){
			System.err.println(novaExcecao1);
		}
	}
	
	public void removerGrupo(GrupoPrivado grupo)
	{
		SistemaCaronaExcecao excecao1 = new SistemaCaronaExcecao("Usuario " + this.getNome() + " nao participa do grupo privado");
		try {	
			for(int i = 0; i < grupos.size(); i++)
			{
				if(grupos.get(i).getGrupo() == grupo && grupo.getDono() != this) //um dono nao pode se remover do grupo
				{	
					System.out.println("Usuario removido com sucesso do grupo privado " + grupo.getNome());
					grupos.remove(i);
				}
			}
			throw excecao1;
		}
		catch(SistemaCaronaExcecao novaExcecao1){
			System.err.println(novaExcecao1);
		}
	}
	
	public void removerGrupo(int idGrupo)
	{
		for(int i = 0; i < grupos.size(); i++)
		{
			if(grupos.get(i).getId() == idGrupo && grupos.get(i).getGrupo().getDono() != this)
			{
				grupos.remove(i);
			}
		}
	}
	
	public void atualizarGrupo(Usuario dono, int idGrupo, String nome, String descricao)
	{
		for(int i = 0; i < dono.getGrupos().size(); i++)
		{
			if(dono.getGrupos().get(i).getId() == idGrupo)
			{
				dono.getGrupos().get(i).getGrupo().setDescricao(descricao);
				dono.getGrupos().get(i).getGrupo().setNome(nome);
			}
		}
	}
	
	public void atualizarGrupo(Usuario dono, int idGrupo, String descricao)
	{
		for(int i = 0; i < dono.getGrupos().size(); i++)
		{
			if(dono.getGrupos().get(i).getId() == idGrupo)
			{
				dono.getGrupos().get(i).getGrupo().setDescricao(descricao);
			}
		}
	}
	
	//eu fiz essa para facilitar na hora de encontrar se o usuario esta no grupo
	public boolean verificarExistenciaUsuarioGrupo(Usuario usu1, Grupo grupo1)
	{
		for(int i = 0; i < usu1.grupos.size(); i++)
		{
			if(usu1.getGrupos().get(i).getGrupo() == grupo1) {
				return true;
			}
		}
		
		return false;
	}
	
	public String toString()
	{
		String out = "[Usuario " + getId() + "]\n";
		out += "Nome: " + getNome() + "\n";
		out += "Email: " + getEmail() + "\n";
		out += "Senha: " + getSenha() + "\n";
		out += "Status: " + isStatus() + "\n";
		if(getPerfil() == null)
		{
			out += "Perfil: Nao inicializado\n";
		}
		else
		{
			out += getPerfil() + "\n";
		}
		return out;
	}
	
	@Override
	public void salvarParaArquivo()
	{
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Usuarios.txt", true)));
			out.writeUTF(this.toString());
			this.getPerfil().salvarParaArquivo();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(out != null)
					out.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}