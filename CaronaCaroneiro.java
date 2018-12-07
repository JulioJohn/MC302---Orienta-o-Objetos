
public class CaronaCaroneiro
{
	private Caroneiro caroneiro;
	private Carona carona;
	private float avaliacao;
	
	public Caroneiro getCaroneiro() {
		return caroneiro;
	}
	public void setCaroneiro(Caroneiro caroneiro) {
		this.caroneiro = caroneiro;
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
	
	public CaronaCaroneiro(float avaliacao1, Carona carona1, Caroneiro caroneiro1)
	{
		setAvaliacao(avaliacao1);
		setCarona(carona1);
		setCaroneiro(caroneiro1);
		caroneiro1.adicionarCarona(this);
	}
}
