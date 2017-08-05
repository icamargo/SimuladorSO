package controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import modelo.Processo;
import static visao.SimuladorSO.listaProcessos;

/**
 *
 * @author tatianefpg
 */
public class ControleParametrosSistema implements Initializable {

    @FXML
    private ToggleGroup grupoPoliticasEscalonamento;

    @FXML
    private RadioButton radioButtonRoundRobinSimples;

    @FXML
    private RadioButton radioButtonRobinComPrioridades;

    @FXML
    private RadioButton radioButtonComPrioridades;

    @FXML
    private RadioButton radioButtonComPrioridadesDinamicas;

    @FXML
    private RadioButton radioButtonEscalonamentoDaLoteria;

    private ArrayList<Processo> listaEscalonador = new ArrayList();
    int quantumSistema = 3;
    int quantumAtual;
    int posicao = 0;
    private int quantidadeProcessos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioButtonRoundRobinSimples.setUserData("roundRobimSimples");
        radioButtonRobinComPrioridades.setUserData("robimComPrioridades");
        radioButtonComPrioridades.setUserData("comPrioridades");
        radioButtonComPrioridadesDinamicas.setUserData("comPrioridadesDinamicas");
        radioButtonEscalonamentoDaLoteria.setUserData("escalonamentoDaLoteria");
        listaEscalonador = (ArrayList<Processo>) listaProcessos;
        quantidadeProcessos = listaEscalonador.size();
        Scheduler();

    }

    public void Scheduler() {
        switch ((String) grupoPoliticasEscalonamento.getSelectedToggle().getUserData()) {
            case "roundRobimSimples":
                roundRobinSimples();
                break;
            case "obimComPrioridades":
                robimComPrioridades();
                break;
            case "comPrioridades":
                comPrioridades();
                break;
            case "comPrioridadesDinamicas":
                comPrioridadesDinamicas();
                break;
            case "escalonamentoDaLoteria":
                escalonamentoDaLoteria();
                break;
            default:
                break;
        }
    }

    private void roundRobinSimples() {
        //FALTA I/O

        //Verifica se existe processo na lista de processos
        while (quantidadeProcessos > 0) {
            //Roda o quantum para todos os processos
            for (posicao = 0; posicao <= quantidadeProcessos; posicao++) {
                //verifica se o processo nao esta suspenso
                if (!listaEscalonador.get(posicao).getEstado().equals("Suspenso")) {
                    //Se ainda tem frames a serem executados
                    if (listaEscalonador.get(posicao).getFramesExecutados() != listaEscalonador.get(posicao).getQtdFrames()) {
                        //processo ira para a execucao
                        listaEscalonador.get(posicao).setEstado("Executando");
                        //incrementa temSpo CPU
                        listaEscalonador.get(posicao).setTempoCPU(listaEscalonador.get(posicao).getTempoCPU() + 1);
                        //incrementa quantum processo pois sera executado 1x
                        listaEscalonador.get(posicao).setQuantumProcesso(listaEscalonador.get(posicao).getQuantumProcesso() + 1);
                        //passa o processo para a CPU
                        processoExecucaoRoundRobinSimples(listaEscalonador.get(posicao));
                        //Verifico se o processo precisa entrar em I/O
                        if ((listaEscalonador.get(posicao).getTipoProcesso().equals("CPU-Bound")) && (listaEscalonador.get(posicao).getQuantumProcesso() == 5) && (listaEscalonador.get(posicao).getFramesExecutados() > 0)) {
                            //processo ira para a espera
                            listaEscalonador.get(posicao).setEstado("Bloqueado");

                            //Implementar I/O
                            //break;
                            
                            //processo ira para a lista de aguardando
                            listaEscalonador.get(posicao).setEstado("Aguardando");
                            //zera o quantum processo
                            listaEscalonador.get(posicao).setQuantumProcesso(0);
                            //decrementar a quantidade de frames a serem executados
                            listaEscalonador.get(posicao).setFramesExecutados(listaEscalonador.get(posicao).getFramesExecutados() - 1);

                        } else if (listaEscalonador.get(posicao).getTipoProcesso().equals("I/O-Bound")) {
                            //processo ira para a espera
                            listaEscalonador.get(posicao).setEstado("Bloqueado");

                            //Implementar I/O
                            //processo ira para a lista de aguardando
                            listaEscalonador.get(posicao).setEstado("Aguardando");
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

    }

    //colocar processo em aguardado para a execucao
    private void processoAguardandoCPURoundRobinSimples(Processo processo) {

    }

    private void robimComPrioridades() {
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
