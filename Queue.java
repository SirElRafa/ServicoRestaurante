

/** NOT COMPLETED
 * Queue representa uma fila, i.e., uma colecao linear de elementos que suporta 
 * a remocao de elementos na frente da fila e a insercao de elementos no fim da 
 * fila, usando uma lista simplesmente ligada.
 * 
 * @author Jose almeida
 *
 * @param <E> Tipo de elementos.
 */
public class Queue<E> {


    //Classe aninhada para os nós das listas

    private static class Node<E>{

        /**Atributos*/
        //o elemento 
        private E element;
        //proximo noh
        private Node<E> next;

        /**
         * Um noh com um dado elemento e a indicacao do proximo noh.
         * @param element o elemento
         * @param node o proximo noh
         * @requires element nao null
         */
        public Node(E element, Node<E> node){
            this.element = element; 
            this.next= node;
        }

        public String toString(){
            return element.toString();
        }

    }



    /**Agora as listas propriamente ditas.*/

    /*Atributos*/
    private Node<E> head;
    private Node<E> rear;
    private int size;

    //construtores

    //completar
    public Queue() {
        size=0;
    }

    //completar
    public Queue(E element){
        head = new Node<E>(element,null );
        head=rear;
        size++;
    }
    
    //completar
    public int size(){
        return size;
    }

    //completar
    public boolean isEmpty(){
        return size==0;
    }

    //completar
    public E front(){
        return head.element;
    }

    //completar
    public void enqueue(E element) {
        Node<E> oldRear = rear;
        rear = new Node<E>(element,null);
        if(isEmpty())
            head = rear;
        else
            oldRear.next = rear;
    }

    //completar
    public void dequeue() {
        if(head.next == null)
            head=tail=null;
        else
            head = head.next;
    }

    public String toString(){
        Node<E> tmp = head;
        StringBuilder res = new StringBuilder("<");
        while(head.next != null){
            res.append(" " + tmp.element.toString());
            tmp = tmp.next;
        }
        res.append(" " + tmp.element.toString() + ">");
        return res.toString();
    }    

}
