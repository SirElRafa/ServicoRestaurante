
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Profs de LabP
 * 
 * NAO PODE ALTERAR ESTA CLASSE
 *
 */
public class RunRestaurantes{

    public static final int ARGUMENTO0 = 0;
    public static final int ARGUMENTO1 = 1;
    public static final int ARGUMENTO2 = 2;

    public static final int NUMERO_INICIAL_DE_COZINHEIROS = 4;

    public static void main(String[] args) throws IOException {

        //try with resources. Mais info em 
        //https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        try( FileReader inicial = new FileReader(args[ARGUMENTO0]); 
                FileWriter escrita =  new FileWriter(args[ARGUMENTO1]);
                FileReader evolucao = new FileReader(args[ARGUMENTO2]) ){

            BufferedReader in = new BufferedReader(inicial);
            BufferedWriter out = new BufferedWriter(escrita);
            BufferedReader decurso = new BufferedReader(evolucao);

            // Criacao de uma distribuicao de servico inicial
            DistServico boaPaparoca = new DistServico(NUMERO_INICIAL_DE_COZINHEIROS);
            boaPaparoca.leDistribui(in);

            // guarda o que vai ser escrito no ficheiro de output
            StringBuffer aEscrever = new StringBuffer();
            // eh o inicio do servico
            boolean inicio = true;

            //o servico decorre de acordo com o Buffer decurso
            String lido;
            while((lido=decurso.readLine())!=null){
                String[] operacao = lido.split("-");
                switch (operacao[0]) {
                    case "Prato Servido":   
                        boaPaparoca.out(Integer.parseInt(operacao[1]));
                        break;
                    case "Novo Pedido":
                        String[] pedido = operacao[1].split(" ");
                        boaPaparoca.request(Integer.parseInt(pedido[0]), Integer.parseInt(pedido[1]) );
                        break;
                    case "Mais Antigo":
                        aEscrever.append("O pedido mais antigo ainda nao servido eh:" + boaPaparoca.oldest() + "\n");
                        break;
                    case "Report":
                        if(inicio){
                            primeiroRegisto(aEscrever, boaPaparoca, operacao[1]);
                            inicio = false;
                        }else{
                            proximosRegistos(aEscrever, boaPaparoca, operacao[1]);
                        }
                        break;
                    default:
                        System.out.println("Comando desconhecido");
                        break;
                }
            }
            out.write(aEscrever.toString());    
            out.close();
        }
    }




    //escrita do 1o registo
    /**
     * escrita do primeiro registo
     * @param aEscrever - StringBuffered onde se vai escrever
     * @param boaPaparoca - a distribuicao de servica
     * @param f - string com a indicacao dos minutos
     * @requires f, aEscrever, boaPaparoca != null
     */
    private static void primeiroRegisto(StringBuffer aEscrever, 
            DistServico boaPaparoca, String f) {
        aEscrever.append("Total de pedidos iniciais: " + boaPaparoca.total() + "\n");
        aEscrever.append("Distribuicao de pedidos iniciais:\n");
        aEscrever.append(boaPaparoca.toString() + "\n");

        aEscrever.append("O(s) cozinheiro(s) com tempo de servico superior a " + f +" minutos eh/sao:\n");
        aEscrever.append(maisAtarefados(boaPaparoca,Integer.parseInt(f)) +  "\n");
        aEscrever.append("Para agora o tempo minimo de espera para um novo cliente ser servido: ");
        aEscrever.append(boaPaparoca.tempoMinimoEspera());
        aEscrever.append("\n\n");

    }

    /**
     * escrita dos registos que nao o primeiro
     * @param aEscrever - StringBuffered onde se vai escrever
     * @param boaPaparoca - a distribuicao de servica
     * @param f - string com a indicacao dos minutos
     * @requires f, aEscrever, boaPaparoca != null
     */
    private static void proximosRegistos(StringBuffer aEscrever, 
            DistServico boaPaparoca, String f) {
        aEscrever.append("Depois de algum tempo de servico a situacao " + "na cozinha eh:\n");
        aEscrever.append("Total de pedidos ainda ativos: " + boaPaparoca.total() + "\n");
        aEscrever.append(boaPaparoca.toString()+"\n");
        aEscrever.append("Os cozinheiros com tempo de espera superior a " + f + " eh/sao:\n");
        aEscrever.append(maisAtarefados(boaPaparoca,Integer.parseInt(f))+"\n");
        aEscrever.append("Tempo minimo de espera para um novo cliente" + " ser servido: ");
        aEscrever.append(boaPaparoca.tempoMinimoEspera());
        aEscrever.append("\n\n");
    }

    /**
     * representacao em string dos cozinheiros com mais servico
     * @param servico - a distribicao de servico em uso
     * @param i - o numero do cozinheiro
     * @requires servico != null e 0 <= i <= numero total cozinheiros servico;
     */
    private static String maisAtarefados(DistServico servico, int i){
        StringBuilder result = new StringBuilder();
        Queue<String> listagem = new Queue<String>();
        listagem = servico.tempoSuperior(i);
        int k = listagem.size(); 
        for(int j=0; j<k; j++ ){
            String cozinheiro = listagem.front();
            listagem.dequeue();
            result.append("Cozinheiro " + cozinheiro + "\n");
        }
        return result.toString();
    }

}
