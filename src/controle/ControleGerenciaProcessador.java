package controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import modelo.Processo;
import static visao.SimuladorSO.listaProcessos;

/**
 *
 * @author IgorCamargo
 */
public class ControleGerenciaProcessador implements Initializable{
    
    @FXML
    private Circle circuloCPU;
    @FXML
    private Circle circuloEspera;
    @FXML
    private Circle circuloPronto1;
    @FXML
    private Circle circuloPronto2;
    @FXML
    private Circle circuloPronto3;
    @FXML
    private Circle circuloPronto4;
    @FXML
    private Circle circuloPronto5;
    
    private ArrayList<Processo> listaEscalonador = new ArrayList();
    private int quantumSistema = 3;
    private int quantumAtual;
    private int posicao = 0;
    private int quantidadeProcessos;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        circuloCPU.setVisible(false);
        circuloEspera.setVisible(false);
        circuloPronto1.setVisible(false);
        circuloPronto2.setVisible(false);
        circuloPronto3.setVisible(false);
        circuloPronto4.setVisible(false);
        circuloPronto5.setVisible(false);
        quantidadeProcessos = listaEscalonador.size();
        //scheduler();
        roundRobinSimples();
    }
    /*
    public void scheduler() {
        switch ((String) botoesEscalonamento.getSelectedToggle().getUserData()) {
            case "RoundRobinSimples":
                roundRobinSimples();
                break;
            case "RoundRobinComPrioridades":
                robinComPrioridades();
                break;
            case "ComPrioridades":
                comPrioridades();
                break;
            case "ComPrioridadesDinamicas":
                comPrioridadesDinamicas();
                break;
            case "EscalonamentoDaLoteria":
                escalonamentoDaLoteria();
                break;
            default:
                break;
        }
    }
    */
    @FXML
    public void roundRobinSimples() {
        Processo processoAux = new Processo();
        //FALTA I/O
        listaEscalonador = listaProcessos;
        quantidadeProcessos = listaEscalonador.size();

        //Verifica se existe processo na lista de processos
        while (quantidadeProcessos > 0) {
            //Roda o quantum para todos os processos
            for (posicao = 0; posicao < quantidadeProcessos; posicao++) {
                processoAux = listaEscalonador.get(posicao);
                //verifica se o processo nao esta suspenso
                if (!processoAux.getEstado().equals("Suspenso")) {
                    //Se ainda tem frames a serem executados
                    if (processoAux.getFramesExecutados() != processoAux.getQtdFrames()) {
                        //processo ira para a execucao
                        processoAux.setEstado("Executando");
                        //incrementa temSpo CPU
                        processoAux.setTempoCPU(processoAux.getTempoCPU() + 1);
                        //incrementa quantum processo pois sera executado 1x
                        processoAux.setQuantumProcesso(processoAux.getQuantumProcesso() + 1);
                        //passa o processo para a CPU
                        processoExecucaoRoundRobinSimples(processoAux);
                        //Verifico se o processo precisa entrar em I/O
                        if ((processoAux.getTipoProcesso().equals("CPU-Bound")) && (processoAux.getQuantumProcesso() == 5) && (processoAux.getFramesExecutados() > 0)) {
                            //processo ira para a espera
                            processoAux.setEstado("Bloqueado");

                            //Implementar I/O
                            //break;
                            
                            //processo ira para a lista de aguardando
                            processoAux.setEstado("Aguardando");
                            //zera o quantum processo
                            processoAux.setQuantumProcesso(0);
                            //decrementar a quantidade de frames a serem executados
                            processoAux.setFramesExecutados(processoAux.getFramesExecutados() - 1);

                        } else if (processoAux.getTipoProcesso().equals("I/O-Bound")) {
                            //processo ira para a espera
                            processoAux.setEstado("Bloqueado");

                            //Implementar I/O
                            //processo ira para a lista de aguardando
                            processoAux.setEstado("Aguardando");
                        }
                    }

                    //não tem mais frames a serem executados (entra em loop pronto-execucao)
                 else {
                    for (int j = 0; j < quantidadeProcessos; j++) {
                        //processo ira para a execucao
                        listaEscalonador.get(j).setEstado("Executando");
                        //incrementa tempo CPU
                        listaEscalonador.get(posicao).setTempoCPU(listaEscalonador.get(posicao).getTempoCPU() + 1);
                        //passa o processo para a CPU
                        processoExecucaoRoundRobinSimples(listaEscalonador.get(j));
                        //processo ira para lista de aguardando
                        listaEscalonador.get(j).setEstado("Aguardando");
                        //incrementa tempo CPU
                        listaEscalonador.get(posicao).setTempoCPU(listaEscalonador.get(posicao).getTempoCPU() + 1);
                        //passa o processo para aguardar execucao
                        processoAguardandoCPURoundRobinSimples(listaEscalonador.get(j));
                    }
                    }
                }
            }
        }
    }
    
    //colocar processo em execucao
    private void processoExecucaoRoundRobinSimples(Processo processo) {
        circuloPronto1.setVisible(false);
        circuloEspera.setVisible(false);
        
        circuloCPU.setFill(processo.getCor().getFill());
        circuloCPU.setVisible(true);
    }

    //colocar processo em aguardado para a execucao
    private void processoAguardandoCPURoundRobinSimples(Processo processo) {
        circuloEspera.setVisible(false);
        circuloCPU.setVisible(false);
        
        circuloPronto1.setFill(processo.getCor().getFill());
        circuloPronto1.setVisible(true);
    }
    
    private void robinComPrioridades() {
        listaDePrioridades();
        //COPIA CODIGO roundRobinSimples
    }

    private void listaDePrioridades() {
        int aux = 0;
        //Coloca os processos em ordem de prioridade
        for (int i = 0; i < listaEscalonador.size(); i++) {
            for (int j = 0; j < listaEscalonador.size() - 1; j++) {
                if (listaEscalonador.get(j).getPid() > listaEscalonador.get(j + 1).getPid()) {
                    aux = listaEscalonador.get(j).getPid();
                    listaEscalonador.get(j).setPid(listaEscalonador.get(j + 1).getPid());
                    listaEscalonador.get(j + 1).setPid(aux);
                }
            }
        }
    }

    private void comPrioridades() {
        //Para evitar starvation,o escalonador pode reduzir sua prioridade a cada tick.
        //Quando a prioridade do processo atual ficar abaixo da prioridade
        //de outro processo pronto, ocorre preempção.
        //Comando nice: Um usuário pode reduzir a prioridade de seu
        //processo caso ele não seja importante.

        if (quantidadeProcessos > 0) {
            //ordena os processos por prioridade
            listaDePrioridades();
        }
        //Verifica se existe processo na lista de processos
        while (quantidadeProcessos > 0) {
            //Roda o quantum para todos os processos
            for (posicao = 0; posicao <= quantidadeProcessos; posicao++) {
                quantumAtual = 0;
                listaEscalonador.get(posicao).setEstado("EXECUTANDO");

                if (listaEscalonador.get(posicao).getQuantumProcesso() > quantumSistema) {
                    quantumAtual = quantumSistema;
                    //VERIFICAR NO CASO DA LISTA Q TIVER 1 ELEMENTO
                    while ((quantumAtual > 0) && (listaEscalonador.get(posicao).getPrioridade() >= listaEscalonador.get(posicao + 1).getPrioridade())) {
                        //Decrementa 1 quantum do QuantumProcesso
                        listaEscalonador.get(posicao).setQuantumProcesso(listaEscalonador.get(posicao).getQuantumProcesso() - 1);
                        listaEscalonador.get(posicao).setPrioridade(listaEscalonador.get(posicao).getPrioridade() - 1);
                        quantumAtual--;
                        listaEscalonador.get(posicao).setTempoCPU(listaEscalonador.get(posicao).getTempoCPU() + 1);
                    }
                    //processso ficara aguardando pois acabou seu quantum
                    listaEscalonador.get(posicao).setEstado("AGUARDANDO");
                    listaDePrioridades();
                } else {
                    while ((listaEscalonador.get(posicao).getQuantumProcesso() > 0) && (listaEscalonador.get(posicao).getPrioridade() >= listaEscalonador.get(posicao + 1).getPrioridade())) {
                        listaEscalonador.get(posicao).setTempoCPU(listaEscalonador.get(posicao).getTempoCPU() + 1);
                        listaEscalonador.get(posicao).setPrioridade(listaEscalonador.get(posicao).getPrioridade() - 1);
                    }
                    //processo finalizado, printar informacoes
                    listaEscalonador.get(posicao).setQuantumProcesso(0);
                    listaEscalonador.get(posicao).setEstado("PRONTO");
                    //Remove processo da lista
                    listaEscalonador.remove(posicao);
                    //Atualiza a quantidade de processos
                    quantidadeProcessos--;
                    listaDePrioridades();
                }
            }

        }
    }

    private void comPrioridadesDinamicas() {

    }

    private void escalonamentoDaLoteria() {

    }

}
