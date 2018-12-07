
import java.util.ArrayList;

public abstract class Carona
{
	private ArrayList<CaronaCaroneiro> caroneiros;
	private CaronaCaronante caronante;
	private double latitudeEncontro;
	private double longitudeEncontro;
	private double latitudeDestino;
	private double longitudeDestino;
	private String horaDiaEncontro;
	private int ocupacaoMaxima;
	private float valor;
	public CaronaCaronante getCaronante() {
		return caronante;
	}

	private ArrayList<MetodoPagamento> formaPagAceitas;
	
	public void setCaronante(CaronaCaronante caronante) {
		this.caronante = caronante;
	}
	
	public double getLatitudeEncontro() {
		return latitudeEncontro;
	}
	public void setLatitudeEncontro(double latitudeEncontro) {
		this.latitudeEncontro = latitudeEncontro;
	}
	public double getLongitudeEncontro() {
		return longitudeEncontro;
	}
	public void setLongitudeEncontro(double longitudeEncontro) {
		this.longitudeEncontro = longitudeEncontro;
	}
	public double getLatitudeDestino() {
		return latitudeDestino;
	}
	public void setLatitudeDestino(double latitudeDestino) {
		this.latitudeDestino = latitudeDestino;
	}
	public double getLongitudeDestino() {
		return longitudeDestino;
	}
	public void setLongitudeDestino(double longitudeDestino) {
		this.longitudeDestino = longitudeDestino;
	}
	public String getHoraDiaEncontro() {
		return horaDiaEncontro;
	}
	public void setHoraDiaEncontro(String horaDiaEncontro) {
		this.horaDiaEncontro = horaDiaEncontro;
	}
	public int getOcupacaoMaxima() {
		return ocupacaoMaxima;
	}
	public void setOcupacaoMaxima(int ocupacaoMaxima) {
		this.ocupacaoMaxima = ocupacaoMaxima;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public ArrayList<CaronaCaroneiro> getCaroneiros() {
		return caroneiros;
	}
	public void setCaroneiros(ArrayList<CaronaCaroneiro> caroneiros) {
		this.caroneiros = caroneiros;
	}

	public abstract boolean AdicionarCaroneiro(Caroneiro caroneiro1);
	
	public boolean RemoverCaroneiro(CaronaCaroneiro caroneiro)
	{
		for(int i=0; i < caroneiros.size(); i++)
		{
			if(caroneiros.get(i) == caroneiro)
			{
				caroneiros.remove(i);
				return true;
			}
		}
		
		return false;
	}
	
	public int verificaOcupacao()
	{
		return caroneiros.size();
	}
	
	public boolean caronaVazia()
	{
		if(verificaOcupacao() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString()
	{
		String out = "[Informacoes da Carona] \n"; 
		out += "Latitude Encontro: " + getLatitudeEncontro() + "\n";
		out += "Longitude Encontro: " + getLongitudeEncontro() + "\n";
		out += "Latidude Destino: " + getLatitudeDestino() + "\n";
		out += "Longitude Destino: " + getLongitudeDestino() + "\n";
		out += "Horario do Encontro: " + getHoraDiaEncontro() + "\n";
		out += "Ocupacao Maxima: " + getOcupacaoMaxima() + "\n";
		out += "Valor: " + getValor() + "\n";
		out += "\n";
		out += "[Caronante - CARONA ATUAL]\n";
		out += caronante.getCaronante().getPerfil().getUsuario().toString();
		out += "[Caroneiros - CARONA ATUAL]\n";
		
		for(int i = 0; i < verificaOcupacao(); i++) 
		{
			out += caroneiros.get(i).getCaroneiro().getPerfil().getUsuario().toString();
			out += "\n";
		}
		return out;
	}
	
	public boolean adicionarFormaPagamento(MetodoPagamento mp)
	{
		if(mp == MetodoPagamento.Gratis)
		{
			for(int i = 0; i < formaPagAceitas.size(); i++)
				formaPagAceitas.remove(i);
		}
		return this.formaPagAceitas.add(mp);
	}
	
	public boolean removerFormaPagamento(MetodoPagamento mp)
	{
		return this.formaPagAceitas.remove(mp);
	}
	
	public boolean checarExistenciaFormaPagamento(MetodoPagamento mp)
	{
		for(int i = 0; i < formaPagAceitas.size(); i++)
		{
			if(formaPagAceitas.get(i) == mp)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean caronaGratuita()
	{
		return checarExistenciaFormaPagamento(MetodoPagamento.Gratis);
	}
	
	public boolean atribuirNotaCaroneiro(int idUsuario, float avaliacao1)
	{
		for(int i=0; i < caroneiros.size(); i++)
		{
			if(caroneiros.get(i).getCaroneiro().getPerfil().getUsuario().getId() == idUsuario)
			{
				caroneiros.get(i).getCaroneiro().getPerfil().setAvaliacao(avaliacao1);
				return true;
			}
		}
		return false;
	}
	
	public boolean atribuirNotaCaronante(int avaliacao1)
	{
		if(caronante != null)
		{
			caronante.getCaronante().getPerfil().setAvaliacao(avaliacao1);
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public Carona(int capacidadeMaximaCaroneiros)
	{
		setOcupacaoMaxima(capacidadeMaximaCaroneiros);
		caroneiros = new ArrayList<CaronaCaroneiro>();
		formaPagAceitas = new ArrayList<MetodoPagamento>();
	}
	
	public Carona(double a, double b, double c, double d, String horaDia, int ocupacao, float valor1)
	{
		caroneiros = new ArrayList<CaronaCaroneiro>();
		formaPagAceitas = new ArrayList<MetodoPagamento>();
		
		setLatitudeEncontro(a);
		setLongitudeEncontro(b);
		setLatitudeDestino(c);
		setLongitudeDestino(d);
		setHoraDiaEncontro(horaDia);
		setOcupacaoMaxima(ocupacao);
		setValor(valor1);
	}
}