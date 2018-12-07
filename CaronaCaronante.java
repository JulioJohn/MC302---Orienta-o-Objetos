
public class CaronaCaronante 
{
	private Caronante caronante;
	private Carona carona;
	private float avaliacao;
	
	public Caronante getCaronante() {
		return caronante;
	}
	public void setCaronante(Caronante caronante) {
		this.caronante = caronante;
	}
	public Carona getCarona() {
		return carona;
	}
	public void setCarona(Carona carona) {
		this.carona = carona;
	}
	public float getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(float avaliacao) {
		this.avaliacao = avaliacao;
	}
	
	public CaronaCaronante(Caronante caronante1, Carona carona1, float avaliacao1)
	{
		setCaronante(caronante1);
		caronante1.adicionarCaronaCaronante(this);
		setCarona(carona1);
		carona1.setCaronante(this);
		setAvaliacao(avaliacao1);
	}
	
}
