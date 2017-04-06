
public class Pedido {
	
	private int ordem;
	private int numero;
	private int tempo;
	
	public Pedido(int ordem, int numero, int tempo){
		this.ordem = ordem;
		this.numero = numero;
		this.tempo = tempo;
	}
	
	public int getTempo(){
		return tempo;
	}
	
	public int getOrdem(){
		return ordem;
	}
	
	public int getMenu(){
		return numero;
	}
	
	public String toString(){
		return "Pedido numero "+ordem+"- Menu " + numero;
	}

}
