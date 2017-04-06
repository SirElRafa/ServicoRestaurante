

public class Cozinheiro {

	private int idCozinheiro;
	private int tempoEmEspera;
	private Queue<Pedido> pedidos;
	// completar
	public Cozinheiro (int i) {
		pedidos = new Queue<>();
		idCozinheiro = i;
	}

	public void adicionaPedido(int ordem, int menu, int tempo){
		pedidos.enqueue(new Pedido(ordem,menu,tempo));
		tempoEmEspera += tempo;
	}

	public void removePedido(){
		if(!pedidos.isEmpty()){
			tempoEmEspera -= pedidos.front().getTempo();
			pedidos.dequeue();
		}
	}

	public int pedidoAtual(){
		return pedidos.front().getOrdem();
	}

	public String pedidoAtualToString(){
		return "Pedido numero "+ pedidos.front().getOrdem() +"- Menu " + pedidos.front().getMenu();
	}

	public int tempoEmEspera(){
		return tempoEmEspera;
	}

	public String toString(){
		return pedidos.toString();
	}

}
