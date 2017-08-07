package controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
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
    private ArrayList<Processo> listaEsperaIO = new ArrayList();
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

        //Verifica se existe processo na lista de processos
        while (quantidadeProcessos > 0) {
            //Roda o quantum para todos os processos
            for (posicao = 0; posicao <= quantidadeProcessos; posicao++) {
                //atualiza a listaEscalonador  e a quantidade de processos (pode ser que tenham inserido mais)
                listaEscalonador = (ArrayList<Processo>) listaProcessos;
                quantidadeProcessos = listaEscalonador.size();

                //verifica se o processo nao esta suspenso
                if (!listaEscalonador.get(posicao).getEstado().equals("Suspenso")) {
                    //primeira iteracao do processo
                    if ((listaEscalonador.get(posicao).getFramesExecutados() == 0) && (listaEscalonador.get(posicao).getTempoCPU() == 0)) {
                        //processo ira para a espera
                        listaEscalonador.get(posicao).setEstado("Bloqueado");
                        //Decrementa quantidade de frames executados
                        listaEscalonador.get(posicao).setFramesExecutados(listaEscalonador.get(posicao).getFramesExecutados() - 1);
                        //adiciona o processo a lista de esperaIO
                        listaEsperaIO.add(listaEscalonador.get(posicao));
                        //remove o processo da listaEscalonador
                        listaEscalonador.remove(posicao);
                        //passa o processo para I/O
                        processoEsperaIO();
                        break;
                    } //Se ainda tem frames a serem executados
                    else if (listaEscalonador.get(posicao).getFramesExecutados() != listaEscalonador.get(posicao).getQtdFrames()) {
                        //processo ira para a execucao
                        listaEscalonador.get(posicao).setEstado("Executando");
                        //incrementa tempo CPU
                        listaEscalonador.get(posicao).setTempoCPU(listaEscalonador.get(posicao).getTempoCPU() + 1);
                        //incrementa quantum processo pois sera executado 1x
                        listaEscalonador.get(posicao).setQuantumProcesso(listaEscalonador.get(posicao).getQuantumProcesso() + 1);
                        //passa o processo para a CPU
                        processoExecucaoRound(listaEscalonador.get(posicao));
                        //Verifico se o processo "CPU-Bound" precisa entrar em I/O
                        if ((listaEscalonador.get(posicao).getTipoProcesso().equals("CPU-Bound")) && (listaEscalonador.get(posicao).getQuantumProcesso() == 5) && (listaEscalonador.get(posicao).getFramesExecutados() > 0)) {
                            //processo ira para a espera
                            listaEscalonador.get(posicao).setEstado("Bloqueado");

                            //adiciona o processo a lista de esperaIO
                            listaEsperaIO.add(listaEscalonador.get(posicao));
                            //remove o processo da listaEscalonador
                            listaEscalonador.remove(posicao);
                            //passa o processo para I/O
                            processoEsperaIO();
                            break;

                        } else if (listaEscalonador.get(posicao).getTipoProcesso().equals("I/O-Bound")) {
                            //processo ira para a espera
                            listaEscalonador.get(posicao).setEstado("Bloqueado");

                            //adiciona o processo a lista de esperaIO
                            listaEsperaIO.add(listaEscalonador.get(posicao));
                            //remove o processo da listaEscalonador
                            listaEscalonador.remove(posicao);
                            //passa o processo para I/O
                            processoEsperaIO();
                            break;
                        }
                    } //não tem mais frames a serem executados (entra em loop pronto-execucao)
                    else {
                        for (int j = 0; j < quantidadeProcessos; j++) {
                            //processo ira para a execucao
                            listaEscalonador.get(j).setEstado("Executando");
                            //incrementa tempo CPU
                            listaEscalonador.get(posicao).setTempoCPU(listaEscalonador.get(posicao).getTempoCPU() + 1);
                            //passa o processo para a CPU
                            processoExecucaoRound(listaEscalonador.get(j));
                            //processo ira para lista de aguardando
                            listaEscalonador.get(j).setEstado("Aguardando");
                            //incrementa tempo CPU
                            listaEscalonador.get(posicao).setTempoCPU(listaEscalonador.get(posicao).getTempoCPU() + 1);
                            //passa o processo para aguardar execucao
                            processoAguardandoCPU(listaEscalonador.get(j));
                        }
                    }
                }
            }
        }
    }

    //colocar processo em execucao
    private void processoExecucaoRound(Processo processo) {

    }

    //colocar processo em aguardado para a execucao
    private void processoAguardandoCPU(Processo processo) {

    }

    private void processoEsperaIO() {
        //processo ira para a lista de aguardando
        listaEscalonador.get(posicao).setEstado("Aguardando");
        //zera o quantum processo
        listaEscalonador.get(posicao).setQuantumProcesso(0);
        //decrementar a quantidade de frames a serem executados
        listaEscalonador.get(posicao).setFramesExecutados(listaEscalonador.get(posicao).getFramesExecutados() - 1);

        //adiciona o processo a lista de escalonador
        listaEscalonador.add(listaEsperaIO.get(posicao));
        //remove o processo da listaEsperaIO
        listaEsperaIO.remove(posicao);
    }

    private void robimComPrioridades() {

        //Verifica se existe processo na lista de processos
        while (quantidadeProcessos > 0) {
            //Roda o quantum para todos os processos
            for (posicao = 0; posicao <= quantidadeProcessos; posicao++) {
                //atualiza a listaEscalonador  e a quantidade de processos (pode ser que tenham inserido mais)
                listaEscalonador = (ArrayList<Processo>) listaProcessos;
                quantidadeProcessos = listaEscalonador.size();
                //ordena os processos por prioridade
                listaDePrioridades();

                //verifica se o processo nao esta suspenso
                if (!listaEscalonador.get(posicao).getEstado().equals("Suspenso")) {
                    //primeira iteracao do processo
                    if ((listaEscalonador.get(posicao).getFramesExecutados() == 0) && (listaEscalonador.get(posicao).getTempoCPU() == 0)) {
                        //processo ira para a espera
                        listaEscalonador.get(posicao).setEstado("Bloqueado");
                        //Decrementa quantidade de frames executados
                        listaEscalonador.get(posicao).setFramesExecutados(listaEscalonador.get(posicao).getFramesExecutados() - 1);
                        //adiciona o processo a lista de esperaIO
                        listaEsperaIO.add(listaEscalonador.get(posicao));
                        //remove o processo da listaEscalonador
                        listaEscalonador.remove(posicao);
                        //passa o processo para I/O
                        processoEsperaIO();
                        break;
                    }
                    //Se ainda tem frames a serem executados, executa o porcesso com maior prioridade por completo
                    while (listaEscalonador.get(posicao).getFramesExecutados() != listaEscalonador.get(posicao).getQtdFrames()) {
                        //processo ira para a execucao
                        listaEscalonador.get(posicao).setEstado("Executando");
                        //incrementa temSpo CPU
                        listaEscalonador.get(posicao).setTempoCPU(listaEscalonador.get(posicao).getTempoCPU() + 1);
                        //incrementa quantum processo pois sera executado 1x
                        listaEscalonador.get(posicao).setQuantumProcesso(listaEscalonador.get(posicao).getQuantumProcesso() + 1);
                        //passa o processo para a CPU
                        processoExecucaoRound(listaEscalonador.get(posicao));
                        //Verifico se o processo precisa entrar em espera I/O
                        if ((listaEscalonador.get(posicao).getTipoProcesso().equals("CPU-Bound")) && (listaEscalonador.get(posicao).getQuantumProcesso() == 5) && (listaEscalonador.get(posicao).getFramesExecutados() > 0)) {
                            //processo ira para a espera
                            listaEscalonador.get(posicao).setEstado("Bloqueado");

                            //adiciona o processo a lista de esperaIO
                            listaEsperaIO.add(listaEscalonador.get(posicao));
                            //remove o processo da listaEscalonador
                            listaEscalonador.remove(posicao);
                            //passa o processo para I/O
                            processoEsperaIO();
                            break;

                        } else if (listaEscalonador.get(posicao).getTipoProcesso().equals("I/O-Bound")) {
                            //processo ira para a espera
                            listaEscalonador.get(posicao).setEstado("Bloqueado");

                            //adiciona o processo a lista de esperaIO
                            listaEsperaIO.add(listaEscalonador.get(posicao));
                            //remove o processo da listaEscalonador
                            listaEscalonador.remove(posicao);
                            //passa o processo para I/O
                            processoEsperaIO();
                            break;
                        }
                    } //não tem mais frames a serem executados (entra em loop pronto-execucao)
                    //executa o loop apenas com o processo demaior prioridade
                    if (listaEscalonador.get(posicao).getFramesExecutados() == listaEscalonador.get(posicao).getQtdFrames()) {
                        //processo ira para a execucao
                        listaEscalonador.get(0).setEstado("Executando");
                        //incrementa tempo CPU
                        listaEscalonador.get(0).setTempoCPU(listaEscalonador.get(0).getTempoCPU() + 1);
                        //passa o processo para a CPU
                        processoExecucaoRound(listaEscalonador.get(0));
                        //processo ira para lista de aguardando
                        listaEscalonador.get(0).setEstado("Aguardando");
                        //passa o processo para aguardar 
                        processoAguardandoCPU(listaEscalonador.get(0));
                    }
                }
            }
        }

    }

    private void listaDePrioridades() {
        int aux = 0;
        //Coloca os processos em ordem de prioridade
        for (int i = 0; i < listaEscalonador.size(); i++) {
            for (int j = 0; j < listaEscalonador.size() - 1; j++) {
                if (listaEscalonador.get(j).getPrioridade() > listaEscalonador.get(j + 1).getPrioridade()) {
                    aux = listaEscalonador.get(j).getPrioridade();
                    listaEscalonador.get(j).setPrioridade(listaEscalonador.get(j + 1).getPrioridade());
                    listaEscalonador.get(j + 1).setPrioridade(aux);
                }
            }
        }
    }

    private void comPrioridades() {
        //Para evitar starvation,o escalonador pode reduzir sua prioridade a cada tick.
        //Se a prioridade do processo atual ficar abaixo da prioridade de outro processo pronto, ocorre preempcao.

        //Verifica se existe processo na lista de processos
        while (quantidadeProcessos > 0) {
            //Roda o quantum para todos os processos
            for (posicao = 0; posicao <= quantidadeProcessos; posicao++) {
                //atualiza a listaEscalonador  e a quantidade de processos (pode ser que tenham inserido mais)
                listaEscalonador = (ArrayList<Processo>) listaProcessos;
                quantidadeProcessos = listaEscalonador.size();
                //ordena os processos por prioridade
                listaDePrioridades();

                //verifica se o processo nao esta suspenso
                if (!listaEscalonador.get(posicao).getEstado().equals("Suspenso")) {
                    //primeira iteracao do processo
                    if ((listaEscalonador.get(posicao).getFramesExecutados() == 0) && (listaEscalonador.get(posicao).getTempoCPU() == 0)) {
                        //processo ira para a espera
                        listaEscalonador.get(posicao).setEstado("Bloqueado");
                        //Decrementa quantidade de frames executados
                        listaEscalonador.get(posicao).setFramesExecutados(listaEscalonador.get(posicao).getFramesExecutados() - 1);
                        //adiciona o processo a lista de esperaIO
                        listaEsperaIO.add(listaEscalonador.get(posicao));
                        //remove o processo da listaEscalonador
                        listaEscalonador.remove(posicao);
                        //passa o processo para I/O
                        processoEsperaIO();
                        break;
                    }
                    //Se ainda tem frames a serem executados, executa o porcesso com maior prioridade por completo
                    //SVerifica se a prioridade do processo atual e maior que a do segundo processo
                    while ((listaEscalonador.get(posicao).getPrioridade() > listaEscalonador.get(posicao + 1).getPrioridade()) && (listaEscalonador.get(posicao).getFramesExecutados() != listaEscalonador.get(posicao).getQtdFrames())) {
                        //processo ira para a execucao
                        listaEscalonador.get(posicao).setEstado("Executando");
                        //incrementa temSpo CPU
                        listaEscalonador.get(posicao).setTempoCPU(listaEscalonador.get(posicao).getTempoCPU() + 1);
                        //incrementa quantum processo pois sera executado 1x
                        listaEscalonador.get(posicao).setQuantumProcesso(listaEscalonador.get(posicao).getQuantumProcesso() + 1);
                        //passa o processo para a CPU
                        processoExecucaoRound(listaEscalonador.get(posicao));
                        //Verifico se o processo precisa entrar em espera I/O
                        if ((listaEscalonador.get(posicao).getTipoProcesso().equals("CPU-Bound")) && (listaEscalonador.get(posicao).getQuantumProcesso() == 5) && (listaEscalonador.get(posicao).getFramesExecutados() > 0)) {
                            //processo ira para a espera
                            listaEscalonador.get(posicao).setEstado("Bloqueado");
                            //Decrementa prioridade para não ocorrer starvation
                            listaEscalonador.get(posicao).setPrioridade(listaEscalonador.get(posicao).getPrioridade() - 1);
                            //adiciona o processo a lista de esperaI/O
                            listaEsperaIO.add(listaEscalonador.get(posicao));
                            //remove o processo da listaEscalonador
                            listaEscalonador.remove(posicao);
                            //passa o processo para I/O
                            processoEsperaIO();
                            break;

                        } else if (listaEscalonador.get(posicao).getTipoProcesso().equals("I/O-Bound")) {
                            //processo ira para a espera
                            listaEscalonador.get(posicao).setEstado("Bloqueado");

                            //adiciona o processo a lista de esperaIO
                            listaEsperaIO.add(listaEscalonador.get(posicao));
                            //remove o processo da listaEscalonador
                            listaEscalonador.remove(posicao);
                            //passa o processo para I/O
                            processoEsperaIO();
                            break;
                        }
                    } //não tem mais frames a serem executados (entra em loop pronto-execucao)
                    //executa o loop apenas com o processo de maior prioridade
                    if (listaEscalonador.get(posicao).getFramesExecutados() == listaEscalonador.get(posicao).getQtdFrames()) {
                        //processo ira para a execucao
                        listaEscalonador.get(0).setEstado("Executando");
                        //incrementa tempo CPU
                        listaEscalonador.get(0).setTempoCPU(listaEscalonador.get(0).getTempoCPU() + 1);
                        //passa o processo para a CPU
                        processoExecucaoRound(listaEscalonador.get(0));
                        //processo ira para lista de aguardando
                        listaEscalonador.get(0).setEstado("Aguardando");
                        //passa o processo para aguardar 
                        processoAguardandoCPU(listaEscalonador.get(0));
                    }
                }
            }
        }
    }

    private void listaDePrioridadeDinamica() {
        int aux = 0;
        //Coloca os processos em ordem de prioridade
        for (int i = 0; i < listaEscalonador.size(); i++) {
            for (int j = 0; j < listaEscalonador.size() - 1; j++) {
                if (listaEscalonador.get(j).getPrioridade() > listaEscalonador.get(j + 1).getPrioridade()) {
                    aux = listaEscalonador.get(j).getPrioridade();
                    listaEscalonador.get(j).setPrioridade(listaEscalonador.get(j + 1).getPrioridade());
                    listaEscalonador.get(j + 1).setPrioridade(aux);
                }
            }
        }
    }

    private void comPrioridadesDinamicas() {
        //Cada processo recebe uma prioridade que representa o inverso da fração utilizada de seu quantum;
        //Se o escalonador deu 100 ms de quantum a um processo.
        //Se ele utilizar 2 ms do quantum, sua prioridade será 50;
        //Se usar 50 ms, sua prioridade será 2;
        //Se tiver usado todo o quantum, sua prioridade será 1.

        //Verifica se existe processo na lista de processos
        while (quantidadeProcessos > 0) {
            //Roda o quantum para todos os processos
            for (posicao = 0; posicao <= quantidadeProcessos; posicao++) {
                //atualiza a listaEscalonador  e a quantidade de processos (pode ser que tenham inserido mais)
                listaEscalonador = (ArrayList<Processo>) listaProcessos;
                quantidadeProcessos = listaEscalonador.size();
                //ordena os processos por prioridade
                listaDePrioridades();

                //verifica se o processo nao esta suspenso
                if (!listaEscalonador.get(posicao).getEstado().equals("Suspenso")) {
                    //primeira iteracao do processo
                    if ((listaEscalonador.get(posicao).getFramesExecutados() == 0) && (listaEscalonador.get(posicao).getTempoCPU() == 0)) {
                        //processo ira para a espera
                        listaEscalonador.get(posicao).setEstado("Bloqueado");
                        //Decrementa quantidade de frames executados
                        listaEscalonador.get(posicao).setFramesExecutados(listaEscalonador.get(posicao).getFramesExecutados() - 1);
                        //adiciona o processo a lista de esperaIO
                        listaEsperaIO.add(listaEscalonador.get(posicao));
                        //remove o processo da listaEscalonador
                        listaEscalonador.remove(posicao);
                        //passa o processo para I/O
                        processoEsperaIO();
                        break;
                    }
                    //Se ainda tem frames a serem executados, executa o porcesso com maior prioridade por completo
                    //Verifica se a prioridade do processo atual e maior que a do segundo processo
                    while ((listaEscalonador.get(posicao).getPrioridade() > listaEscalonador.get(posicao + 1).getPrioridade()) && (listaEscalonador.get(posicao).getFramesExecutados() != listaEscalonador.get(posicao).getQtdFrames())) {
                        //processo ira para a execucao
                        listaEscalonador.get(posicao).setEstado("Executando");
                        //incrementa temSpo CPU
                        listaEscalonador.get(posicao).setTempoCPU(listaEscalonador.get(posicao).getTempoCPU() + 1);
                        //incrementa quantum processo pois sera executado 1x
                        listaEscalonador.get(posicao).setQuantumProcesso(listaEscalonador.get(posicao).getQuantumProcesso() + 1);
                        //passa o processo para a CPU
                        processoExecucaoRound(listaEscalonador.get(posicao));
                        //atualiza sua prioridade
                        listaEscalonador.get(posicao).setPrioridade((listaEscalonador.get(posicao).getPrioridade() / listaEscalonador.get(posicao).getQuantumProcesso()));
                        //Verifico se o processo precisa entrar em espera I/O
                        if ((listaEscalonador.get(posicao).getTipoProcesso().equals("CPU-Bound")) && (listaEscalonador.get(posicao).getQuantumProcesso() == 5) && (listaEscalonador.get(posicao).getFramesExecutados() > 0)) {
                            //processo ira para a espera
                            listaEscalonador.get(posicao).setEstado("Bloqueado");
                            //Decrementa prioridade para não ocorrer starvation
                            listaEscalonador.get(posicao).setPrioridade(listaEscalonador.get(posicao).getPrioridade() - 1);
                            //adiciona o processo a lista de esperaI/O
                            listaEsperaIO.add(listaEscalonador.get(posicao));
                            //remove o processo da listaEscalonador
                            listaEscalonador.remove(posicao);
                            //passa o processo para I/O
                            processoEsperaIO();
                            break;
                            //se processo for I/O-Bound
                        } else if (listaEscalonador.get(posicao).getTipoProcesso().equals("I/O-Bound")) {
                            //processo ira para a espera
                            listaEscalonador.get(posicao).setEstado("Bloqueado");

                            //adiciona o processo a lista de esperaIO
                            listaEsperaIO.add(listaEscalonador.get(posicao));
                            //remove o processo da listaEscalonador
                            listaEscalonador.remove(posicao);
                            //passa o processo para I/O
                            processoEsperaIO();
                            break;
                        }
                    } //não tem mais frames a serem executados (entra em loop pronto-execucao)
                    //executa o loop apenas com o processo de maior prioridade
                    if (listaEscalonador.get(posicao).getFramesExecutados() == listaEscalonador.get(posicao).getQtdFrames()) {
                        //processo ira para a execucao
                        listaEscalonador.get(0).setEstado("Executando");
                        //incrementa tempo CPU
                        listaEscalonador.get(0).setTempoCPU(listaEscalonador.get(0).getTempoCPU() + 1);
                        //passa o processo para a CPU
                        processoExecucaoRound(listaEscalonador.get(0));
                        //processo ira para lista de aguardando
                        listaEscalonador.get(0).setEstado("Aguardando");
                        //passa o processo para aguardar 
                        processoAguardandoCPU(listaEscalonador.get(0));
                    }
                }
            }
        }
    }

    private void sorteiaTicket() {
        for (posicao = 0; posicao <= quantidadeProcessos; posicao++) {
            //instância um objeto da classe Random usando o construtor básico
            Random gerador = new Random();
            int numero = 0;
            numero = gerador.nextInt(quantidadeProcessos - 1);
            //Processos de mais prioridade recebem tickets extras
            listaEscalonador.get(posicao).setPrioridade(numero * listaEscalonador.get(posicao).getPrioridade());
        }
    }

    private void escalonamentoDaLoteria() {
        //Os processos recebem “tickets de loteria” para uso de vários recursos, inclusive CPU.
        //O escalonador sorteia um ticket ao acaso. Cada ticket vale uma certa “quantidade do recurso”.
        //O processo que possuir este ticket ganha acesso ao recurso.
        //Processos de mais importância podem receber tickets extras.

        //Verifica se existe processo na lista de processos
        while (quantidadeProcessos > 0) {
            //executa o sorteio de tickets
            //Roda o quantum para todos os processos
            for (posicao = 0; posicao <= quantidadeProcessos; posicao++) {
                //atualiza a listaEscalonador  e a quantidade de processos (pode ser que tenham inserido mais)
                listaEscalonador = (ArrayList<Processo>) listaProcessos;
                quantidadeProcessos = listaEscalonador.size();
                //ordena os processos por prioridade
                listaDePrioridades();

                //verifica se o processo nao esta suspenso
                if (!listaEscalonador.get(posicao).getEstado().equals("Suspenso")) {
                    //primeira iteracao do processo
                    if ((listaEscalonador.get(posicao).getFramesExecutados() == 0) && (listaEscalonador.get(posicao).getTempoCPU() == 0)) {
                        //processo ira para a espera
                        listaEscalonador.get(posicao).setEstado("Bloqueado");
                        //Decrementa quantidade de frames executados
                        listaEscalonador.get(posicao).setFramesExecutados(listaEscalonador.get(posicao).getFramesExecutados() - 1);
                        //adiciona o processo a lista de esperaIO
                        listaEsperaIO.add(listaEscalonador.get(posicao));
                        //remove o processo da listaEscalonador
                        listaEscalonador.remove(posicao);
                        //passa o processo para I/O
                        processoEsperaIO();
                        break;
                    }
                    //Se ainda tem frames a serem executados, executa o porcesso com maior prioridade por completo
                    while (listaEscalonador.get(posicao).getFramesExecutados() != listaEscalonador.get(posicao).getQtdFrames()) {
                        //processo ira para a execucao
                        listaEscalonador.get(posicao).setEstado("Executando");
                        //incrementa temSpo CPU
                        listaEscalonador.get(posicao).setTempoCPU(listaEscalonador.get(posicao).getTempoCPU() + 1);
                        //incrementa quantum processo pois sera executado 1x
                        listaEscalonador.get(posicao).setQuantumProcesso(listaEscalonador.get(posicao).getQuantumProcesso() + 1);
                        //passa o processo para a CPU
                        processoExecucaoRound(listaEscalonador.get(posicao));
                        //Verifico se o processo precisa entrar em espera I/O
                        if ((listaEscalonador.get(posicao).getTipoProcesso().equals("CPU-Bound")) && (listaEscalonador.get(posicao).getQuantumProcesso() == 5) && (listaEscalonador.get(posicao).getFramesExecutados() > 0)) {
                            //processo ira para a espera
                            listaEscalonador.get(posicao).setEstado("Bloqueado");

                            //adiciona o processo a lista de esperaIO
                            listaEsperaIO.add(listaEscalonador.get(posicao));
                            //remove o processo da listaEscalonador
                            listaEscalonador.remove(posicao);
                            //passa o processo para I/O
                            processoEsperaIO();
                            break;

                        } else if (listaEscalonador.get(posicao).getTipoProcesso().equals("I/O-Bound")) {
                            //processo ira para a espera
                            listaEscalonador.get(posicao).setEstado("Bloqueado");

                            //adiciona o processo a lista de esperaIO
                            listaEsperaIO.add(listaEscalonador.get(posicao));
                            //remove o processo da listaEscalonador
                            listaEscalonador.remove(posicao);
                            //passa o processo para I/O
                            processoEsperaIO();
                            break;
                        }
                    } //não tem mais frames a serem executados (entra em loop pronto-execucao)
                    //executa o loop apenas com o processo demaior prioridade
                    if (listaEscalonador.get(posicao).getFramesExecutados() == listaEscalonador.get(posicao).getQtdFrames()) {
                        //processo ira para a execucao
                        listaEscalonador.get(0).setEstado("Executando");
                        //incrementa tempo CPU
                        listaEscalonador.get(0).setTempoCPU(listaEscalonador.get(0).getTempoCPU() + 1);
                        //passa o processo para a CPU
                        processoExecucaoRound(listaEscalonador.get(0));
                        //processo ira para lista de aguardando
                        listaEscalonador.get(0).setEstado("Aguardando");
                        //passa o processo para aguardar 
                        processoAguardandoCPU(listaEscalonador.get(0));
                    }
                }
            }
        }
    }

}
