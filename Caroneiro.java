
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Caroneiro implements Salvavel //pede o uber
{
	private String cartaoDeCredito;
	private ArrayList <CaronaCaroneiro> caronas;
	private Perfil perfil;
	
	public String getCartaoDeCredito()
	{
		return cartaoDeCredito;
	}
	
	public void setCartaoDeCredito(String cartaoDeCredito)
	{
		this.cartaoDeCredito = cartaoDeCredito;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public ArrayList<CaronaCaroneiro> getCaronas() {
		return caronas;
	}

	public boolean adicionarCarona(CaronaCaroneiro caroneiro)
	{
		return caronas.add(caroneiro);
	}

	public String toString()
	{
		String out = "[Caroneiro]\n";
		out += "Cartao de Credito: " + getCartaoDeCredito() + "\n";
		//retirei o perfil a fim de salvar out += "[Perfil do Caroneiro]\n" + getPerfil() + "\n";
		return out;
	}
	
	public Caroneiro(String credito) //pagamento em cartao
	{
		this.caronas = new ArrayList<CaronaCaroneiro>();
		cartaoDeCredito = credito;
	}
	
	public Caroneiro() //construtor sem parametros
	{
		this.caronas = new ArrayList<CaronaCaroneiro>();
		setCartaoDeCredito("Vazio");
		Perfil perfil1 = new Perfil();
		
		this.perfil = perfil1;
		perfil1.setCaroneiro(this);
	}
	
	public Caroneiro(String cartao, Perfil perfil1)
	{
		this.caronas = new ArrayList<CaronaCaroneiro>();
		setCartaoDeCredito(cartao);
		
		setPerfil(perfil1);
		perfil1.setCaroneiro(this);
	}
	
	public boolean pedirCarona(Carona carona)
	{
		CaronaCaroneiro nova = new CaronaCaroneiro(perfil.getAvaliacao(), carona, this);
		return this.caronas.add(nova);
	}

	@Override
	public void salvarParaArquivo() {
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("Caroneiro.txt", true)));
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