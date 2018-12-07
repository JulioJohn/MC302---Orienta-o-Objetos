

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GrupoPublico extends Grupo
{
	private ArrayList<CaronaPublica> caronas;
	
	public void adicionarMembro(Usuario usuario)
	{
		GrupoUsuario novoMembro = new GrupoUsuario(usuario.getId(), usuario, this);
		usuario.getGrupos().add(novoMembro); // verificar essa alteracao, pode ser que adicione 2 vezes!!!
		super.getMembros().add(novoMembro);
	}
	
	public ArrayList<CaronaPublica> getCaronas() {
		return caronas;
	}

	public void setCaronas(ArrayList<CaronaPublica> caronas) {
		this.caronas = caronas;
	}

	public String toString()
	{
		String out = "[Grupo Publico]\n"; 
		out += "Id: " + getId() + " / Gerador Id: " + getGeradorId() + "\n";
		out += "Nome: " + getNome() + " / Descricao: " + getDescricao() + "\n";
		out += "Membros: \n";
		for(int i = 0; i < getMembros().size(); i++) 
		{
			out += "Id: " + getMembros().get(i).getUsuario().getId() + " / Usuario: "+ getMembros().get(i).getUsuario().getNome() + "\n";
		}
		return out;
	}

	public GrupoPublico()
	{
		super();
		caronas = new ArrayList<CaronaPublica>();
	}
	
	public GrupoPublico(int id, String nome, String descricao)
	{
		super(id, nome, descricao);
		caronas = new ArrayList<CaronaPublica>();
	}

	@Override
	public void salvarParaArquivo() {
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("GrupoPublico.txt", true)));
			out.writeUTF(this.toString());
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
