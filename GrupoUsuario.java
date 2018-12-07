
public class GrupoUsuario implements Salvavel{
	private int id;
	private Usuario usuario;
	private Grupo grupo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public GrupoUsuario(int id, Usuario usuario, Grupo grupo)
	{
		this.id = id;
		this.usuario = usuario;
		this.grupo = grupo;
	}
	@Override
	public void salvarParaArquivo() {
		this.grupo.salvarParaArquivo();
	}	
}
