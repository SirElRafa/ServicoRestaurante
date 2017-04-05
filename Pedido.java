public class Pedido{

    private int numero;
    private int ordem;
    private int tempo;
    
    public Pedido(int numero, int ordem, int tempo){
     
        this.numero = numero;
        this.ordem = ordem;
        this.tempo = tempo;
    }

    public getOrdem(){
       return ordem; 
    }
    
    public getNumero(){
       return numero; 
    }
    
    public getTempo(){
       return tempo; 
    }
    
    public toString(){
       return "ordem " + ordem + " numero " + numero + " tempo "+ tempo;
    } 
}
    
