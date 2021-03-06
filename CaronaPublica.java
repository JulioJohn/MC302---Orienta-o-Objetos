
import java.util.ArrayList;

public class CaronaPublica extends Carona
{
	private ArrayList<GrupoPublico> grupos;
	
	public boolean AdicionarCaroneiro(Caroneiro caroneiro)
	{
		for(int i = 0; i < grupos.size(); i++)
		{
			if(grupos.get(i).checarPresencaUsuario(caroneiro.getPerfil().getUsuario()) == true)
			{
				if(super.getCaroneiros().size() < getOcupacaoMaxima())
				{
					CaronaCaroneiro novaCaronaCaroneiro = new CaronaCaroneiro(10, this, caroneiro);
					super.getCaroneiros().add(novaCaronaCaroneiro);
					System.out.println("Caroneiro adicionado com sucesso!");
					return true;
				}
				else
				{
					System.out.println("Ocupacao maxima atingida, nao foi possivel adicionar");
					return false;
				}
			}
		}
		System.out.println("Nao foi possivel adicionar, caroneiro nao esta no grupo!");
		//se a carona nao tiver grupos associados, qualquer um pode ser adicionado
		if(grupos.size() == 0){
			if(super.getCaroneiros().size() < getOcupacaoMaxima())
			{
				CaronaCaroneiro novaCaronaCaroneiro = new CaronaCaroneiro(10, this, caroneiro);
				super.getCaroneiros().add(novaCaronaCaroneiro);
				System.out.println("Carona nao tem grupos associados, adicionado com sucesso!");
				return true;
			}
			else
			{
				return false;
			}
		}
			
		return false;
	}
	
	public boolean adicionarGrupo(GrupoPublico grupo)
	{
		Usuario usuario1 = this.getCaronante().getCaronante().getPerfil().getUsuario();
		//adiciona apenas se o caronante estiver no grupo
		if(grupo.checarPresencaUsuario(usuario1) == true)
		{
			System.out.println("Caronante participa do grupo, adicionado ao grupo com sucesso!");
			grupo.getCaronas().add(this);
			return this.grupos.add(grupo);
		}
		System.out.println("Caronante n�o participa do grupo, n�o foi poss�vel adicionar ao grupo!");
		return false;
	}
	
	public CaronaPublica(int capacidade)
	{
		super(capacidade);
		grupos = new ArrayList<GrupoPublico>();
	}
	
	public CaronaPublica(double a, double b, double c, double d, String horaDia, int ocupacao, float valor1)
	{
		super(a, b, c, d, horaDia, ocupacao, valor1);
		grupos = new ArrayList<GrupoPublico>();
	}
}
