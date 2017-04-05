

public class Cozinheiro {

    private int tempoEspera;
    private int nrCozinheiro;
    private Queue<Pedido> filaPedidos;

    // completar
    public Cozinheiro (int i) {
        tempoEspera = 0;
        nrCozinheiro = i;
        filaPedidos = new Queue<>();
    }
    
    public void addPedido(int ordem,int numero,int tempo){
        filaPedidos.enqueue(new Pedido(ordem,numero,tempo));
        tempoEspera += tempo;
    }
    
    public void removePedido(){
        //rever em IDE
        tempoEspera -= filaPedidos.front().getTempo();
        filaPedidos.dequeue();
    }
    
    public int tempoNaFila(){
        return tempoEspera; 
    }
}
