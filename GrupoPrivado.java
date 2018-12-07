

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class GrupoPrivado extends Grupo
{
	private ArrayList<CaronaPrivada> caronas;
	
	public ArrayList<CaronaPrivada> getCaronas() {
		return caronas;
	}

	public void setCaronas(ArrayList<CaronaPrivada> caronas) {
		this.caronas = caronas;
	}

	public void adicionarMembro(Usuario usuario)
	{
		GrupoUsuario novoMembro = new GrupoUsuario(usuario.getId(), usuario, this);
		usuario.getGrupos().add(novoMembro); // verificar essa alteracao, pode ser que adicione 2 vezes!!!
		super.getMembros().add(novoMembro);
	}
	
	public String toString()
	{
		String out = "[Grupo Privado]\n"; 
		out += "Id: " + getId() + " / Gerador Id: " + getGeradorId() + "\n";
		out += "Nome: " + getNome() + " / Descricao: " + getDescricao() + "\n";
		out += "Membros: \n";
		for(int i = 0; i < getMembros().size(); i++) 
		{
			out += "Id: " + getMembros().get(i).getUsuario().getId() + " / Usuario: "+ getMembros().get(i).getUsuario().getNome() + "\n";
		}
		return out;
	}
	
	public GrupoPrivado()
	{
		super();
		caronas = new ArrayList<CaronaPrivada>();
	}
	
	public GrupoPrivado(int id, String nome, String descricao)
	{
		super(id, nome, descricao);
		caronas = new ArrayList<CaronaPrivada>();
	}

	@Override
	public void salvarParaArquivo() {
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("GrupoPrivado.txt", true)));
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
