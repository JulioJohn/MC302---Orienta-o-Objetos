
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Caronante implements Salvavel //motorista de uber

{
	private int tempoHabilitacao;
	private String generoMusicalFavorito;
	private String placaVeiculo;
	private final String carteiraMotorista;
	private String marcaVeiculo;
	private String modeloVeiculo;
	private ArrayList<CaronaCaronante> caronas;
	private Perfil perfil;
	
	public int getTempoHabilitacao() 
	{
		return tempoHabilitacao;
	}
	
	public String getGeneroMusicalFavorito() 
	{
		return generoMusicalFavorito;
	}

	public String getPlacaVeiculo() 
	{
		return placaVeiculo;
	}
	
	public String getCarteiraMotorista()
	{
		return carteiraMotorista;
	}
	
	public String getMarcaVeiculo() 
	{
		return marcaVeiculo;
	}

	public String getModeloVeiculo()
	{
		return modeloVeiculo;
	}

	public void setTempoHabilitacao(int tempoHabilitacao)
	{
		this.tempoHabilitacao = tempoHabilitacao;
	}
	
	public void setGeneroMusicalFavorito(String generoMusicalFavorito)
	{
		this.generoMusicalFavorito = generoMusicalFavorito;
	}
	
	public void setPlacaVeiculo(String placaVeiculo)
	{
		this.placaVeiculo = placaVeiculo;
	}
	
	public void setMarcaVeiculo(String marcaVeiculo)
	{
		this.marcaVeiculo = marcaVeiculo;
	}
	
	public void setModeloVeiculo(String modeloVeiculo)
	{
		this.modeloVeiculo = modeloVeiculo;
	}

	public Perfil getPerfil() 
	{
		return perfil;
	}

	public void setPerfil(Perfil perfil) 
	{
		this.perfil = perfil;
		perfil.setCaronante(this);
	}

	public ArrayList<CaronaCaronante> getCaronas() {
		return caronas;
	}

	public boolean adicionarCaronaCaronante(CaronaCaronante cc)
	{
		return caronas.add(cc);
	}

	public Carona oferecerCaronaPublica(double a, double b, double c, double d, String horaDia, int ocupacao, float valor1)
	{
		Carona novaCarona = new CaronaPublica(a, b, c, d, horaDia, ocupacao, valor1);
		CaronaCaronante novaCaronaCaronante = new CaronaCaronante(this, novaCarona, valor1); //realiza a ligacao carona-caronante
		//havia algo aqui, verificar!!!
		return (Carona)novaCarona;
	}
	
	public Carona oferecerCaronaPrivada(double a, double b, double c, double d, String horaDia, int ocupacao, float valor1)
	{
		Carona novaCarona = new CaronaPrivada(a, b, c, d, horaDia, ocupacao, valor1);
		CaronaCaronante novaCaronaCaronante = new CaronaCaronante(this, novaCarona, valor1); //realiza a ligacao carona-caronante
		return (Carona)novaCarona;
	}


	public String toString()
	{
		String out = "Tempo Habilitacao: " + getTempoHabilitacao() + "\n";
		out += "Genero Musical Favorito: " + getGeneroMusicalFavorito() + "\n";
		out += "Placa do Veiculo: " + getPlacaVeiculo() + "\n";
		out += "Carteira de Motorista: " + getCarteiraMotorista() + "\n";
		out += "Marca do Veiculo: " + getMarcaVeiculo() + "\n";
		out += "Modelo do Veiculo: " + getModeloVeiculo() + "\n";
		return out;
	}

	//construtor inicializando dados
	public Caronante()
	{ 
		this.caronas = new ArrayList<CaronaCaronante>() ;
		setTempoHabilitacao(0);
		setGeneroMusicalFavorito("Desconhecido");
		setPlacaVeiculo("Desconhecido");
		carteiraMotorista = "Desconhecido"; //foi necessario retirar o set e inicializar no construtor
		setMarcaVeiculo("Desconhecido");
		setModeloVeiculo("Desconhecido");
	}
	
	public Caronante(int tempo, String a, String b, String c, String d, String e, Perfil perfil1)
	{
		this.caronas = new ArrayList<CaronaCaronante>() ;
		tempoHabilitacao = tempo;
		generoMusicalFavorito = a;
		placaVeiculo = b;
		carteiraMotorista = c;
		marcaVeiculo = d;
		modeloVeiculo = e;
		
		setPerfil(perfil1);
		perfil1.setCaronante(this);
	}

	@Override
	public void salvarParaArquivo() {
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Caronante.txt", true)));
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