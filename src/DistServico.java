
import java.io.BufferedReader;
import java.io.IOException;

public class DistServico {

	private static final int DEFAULT_TIME = 10;
	private int nCozServico;
	private int ordem;
	private int pedidosAtivos;
	private Cozinheiro[] listaCozinheiro;


	/**
	 * Cria uma distribuicao de servico vazia.
	 *
	 *@param nCozServico
	 *@requires nCozServico > 0
	 */
	public DistServico(int nCozServico) {
		this.nCozServico = nCozServico;
		listaCozinheiro = new Cozinheiro[nCozServico];
		ordem = 0;
		for(int i = 0; i<nCozServico; i++){
			listaCozinheiro[i] = new Cozinheiro(i);
		}
	}


	//completar
	public void leDistribui(BufferedReader in) throws IOException{
		String input;
		while( (input = in.readLine()) != null ){
			atribuiPedido(input);
			pedidosAtivos++;
		}
	}

	/**
	 * 
	 * @param linha
	 */
	private void atribuiPedido(String linha){
		String [] menuEtempo = linha.split(" ");
		int menu = Integer.parseInt(menuEtempo[0]);
		if(menuEtempo.length == 1)
			listaCozinheiro[melhorCozinheiro()].adicionaPedido(++ordem, menu, DEFAULT_TIME);
		else{
			int tempo = Integer.parseInt(menuEtempo[1]);
			listaCozinheiro[melhorCozinheiro()].adicionaPedido(++ordem, menu, tempo);
		}
	}

	private int melhorCozinheiro(){
		int melhor = 0;
		for(int i = 1; i < nCozServico; i++)
			if(listaCozinheiro[melhor].tempoEmEspera() > listaCozinheiro[i].tempoEmEspera())
				melhor = i;
		return melhor;
	}


	//completar
	public int total(){
		return pedidosAtivos;
	}

	//completar
	public Queue<String> tempoSuperior(int i){
		Queue<String> nova = new Queue<>();
		for(int j = 0; j < nCozServico; j++){
			if(listaCozinheiro[j].tempoEmEspera() > i){
				nova.enqueue("" + (j+1));
			}
		}
		return nova;
	}


	//completar
	public int tempoMinimoEspera(){
		int min = 0;
		for(int i = 0; i < nCozServico; i++)
			if(listaCozinheiro[i].tempoEmEspera() < listaCozinheiro[min].tempoEmEspera())
				min = i;
		return listaCozinheiro[min].tempoEmEspera() + DEFAULT_TIME;		
	}

	//completar
	public void request(int descricao, int tempo){
		listaCozinheiro[melhorCozinheiro()].adicionaPedido(++ordem, descricao, tempo);
		pedidosAtivos++;
	}

	//completar
	public void out(int nCozinheiro){
		listaCozinheiro[--nCozinheiro].removePedido();
		pedidosAtivos--;
	}


	//completar
	public String oldest() {
		int maisAntigo = 0;
		for(int i = 1; i < nCozServico; i++){
			if(listaCozinheiro[maisAntigo].pedidoAtual()>listaCozinheiro[i].pedidoAtual())
				maisAntigo = i;
		}

		return listaCozinheiro[maisAntigo].pedidoAtualToString();
	}

	public String toString(){
		StringBuilder ja = new StringBuilder();
		for(int i = 0; i < nCozServico; i++){
			ja.append("Cozinheiro " + (i+1) + ":\n");
			ja.append(listaCozinheiro[i].toString() + "\n");
		}
		return ja.toString();
	}

}
