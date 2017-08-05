/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import static visao.SimuladorSO.listaProcessos;
import modelo.Processo;

/**
 *
 * @author tatianefpg
 */


public class ControleParametrosSistema implements Initializable{

    @FXML
    private ToggleGroup grupoPoliticasEscalonamento;

    @FXML
    private RadioButton radioButtonRoundRobimSimples;
    
    @FXML
    private RadioButton radioButtonRobimComPrioridades;
    
    @FXML
    private RadioButton radioButtonComPrioridades;
    
    @FXML
    private RadioButton radioButtonComPrioridadesDinamicas;
            
    @FXML
    private RadioButton radioButtonEscalonamentoDaLoteria;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioButtonRoundRobimSimples.setUserData("roundRobimSimples");
        radioButtonRobimComPrioridades.setUserData("robimComPrioridades");
        radioButtonComPrioridades.setUserData("comPrioridades");
        radioButtonComPrioridadesDinamicas.setUserData("comPrioridadesDinamicas");
        radioButtonEscalonamentoDaLoteria.setUserData("escalonamentoDaLoteria");
        quantidadeProcessos = listaProcessos.size();
        Scheduler();
        
    }
    
    int quantumSistema = 3;
    int quantumAtual;
    int posicao = 0;    
    private int quantidadeProcessos;
    
    private void Scheduler (){
        switch((String) grupoPoliticasEscalonamento.getSelectedToggle().getUserData()){
            case "roundRobimSimples":
                roundRobimSimples();
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
    
    
    private void roundRobimSimples (){    
        //FALTA I/O
        
        //Verifica se existe processo na lista de processos
        while(quantidadeProcessos > 0){      
            //Roda o quantum para todos os processos
            for(posicao = 0; posicao <= quantidadeProcessos; posicao++){
                quantumAtual = 0;
                listaProcessos.get(posicao).setStatus("EXECUTANDO");
                
                if(listaProcessos.get(posicao).getQuantumProcesso() > quantumSistema){
                    //listaProcessos.get(posicao).getQuantumProcesso() -= quantumSistema;
                    quantumAtual = quantumSistema;
                    while(quantumAtual > 0){
                        //Decrementa 1 quantum do QuantumProcesso
                        listaProcessos.get(posicao).setQuantumProcesso(listaProcessos.get(posicao).getQuantumProcesso() -1);
                        System.out.println(listaProcessos.get(posicao).getQuantumProcesso());
                        quantumAtual --;
                        listaProcessos.get(posicao).setTempoCPU(listaProcessos.get(posicao).getTempoCPU() +1);
                    }
                    //processso ficara aguardando pois acabou seu quantum
                    listaProcessos.get(posicao).setStatus("AGUARDANDO");
                    
                }
                else{
                    while(listaProcessos.get(posicao).getQuantumProcesso() > 0){
                        listaProcessos.get(posicao).setTempoCPU(listaProcessos.get(posicao).getTempoCPU() +1);
                    }
                    //processo finalizado, printar informacoes
                    listaProcessos.get(posicao).setQuantumProcesso(0);
                    listaProcessos.get(posicao).setStatus("PRONTO");
                    //Remove processo da lista
                    listaProcessos.remove(posicao);
                    //Atualiza a quantidade de processos
                    quantidadeProcessos --;
                }    
            }
        
        }
    }
    
    
    private void robimComPrioridades (){
        listaDePrioridades();
        //COPIA CODIGO roundRobimSimples
    }
    
    private void listaDePrioridades(){
        int aux = 0;
        //Coloca os processos em ordem de prioridade
        for(int i = 0; i < listaProcessos.size(); i++){
		for(int j = 0; j < listaProcessos.size() -1; j++){
			if(listaProcessos.get(j).getPid() > listaProcessos.get(j+1).getPid()){
				aux = listaProcessos.get(j).getPid();
				listaProcessos.get(j).setPid(listaProcessos.get(j+1).getPid());
				listaProcessos.get(j+1).setPid(aux);
			}
		}
	}
    }
    
    private void comPrioridades (){
        //Para evitar starvation,o escalonador pode reduzir sua prioridade a cada tick.
        //Quando a prioridade do processo atual ficar abaixo da prioridade
            //de outro processo pronto, ocorre preempção.
        //Comando nice: Um usuário pode reduzir a prioridade de seu
            //processo caso ele não seja importante.
    
    if(quantidadeProcessos>0){
        //ordena os processos por prioridade
        listaDePrioridades();
    }
    //Verifica se existe processo na lista de processos
    while(quantidadeProcessos > 0){
        //Roda o quantum para todos os processos
        for(posicao = 0; posicao <= quantidadeProcessos; posicao++){
            quantumAtual = 0;
            listaProcessos.get(posicao).setStatus("EXECUTANDO");
                
                if(listaProcessos.get(posicao).getQuantumProcesso() > quantumSistema){
                    quantumAtual = quantumSistema;
                    //VERIFICAR NO CASO DA LISTA Q TIVER 1 ELEMENTO
                    while((quantumAtual > 0) && (listaProcessos.get(posicao).getPrioridade(posicao) >= listaProcessos.get(posicao).getPrioridade(posicao +1))){
                        //Decrementa 1 quantum do QuantumProcesso
                        listaProcessos.get(posicao).setQuantumProcesso(listaProcessos.get(posicao).getQuantumProcesso()-1);
                        listaProcessos.get(posicao).setPrioridade(listaProcessos.get(posicao).getPrioridade() -1);
                        quantumAtual --;
                        listaProcessos.get(posicao).setTempoCPU(listaProcessos.get(posicao).getTempoCPU() +1);
                    }
                    //processso ficara aguardando pois acabou seu quantum
                    listaProcessos.get(posicao).setStatus("AGUARDANDO");
                    listaDePrioridades();
                }
                else{
                    while((listaProcessos.get(posicao).getQuantumProcesso() > 0) && (listaProcessos.get(posicao).getPrioridade(posicao) >= listaProcessos.get(posicao).getPrioridade(posicao +1))){
                        listaProcessos.get(posicao).setTempoCPU(listaProcessos.get(posicao).getTempoCPU() +1);
                        listaProcessos.get(posicao).setPrioridade(listaProcessos.get(posicao).getPrioridade() -1);
                    }
                    //processo finalizado, printar informacoes
                    listaProcessos.get(posicao).setQuantumProcesso(0);
                    listaProcessos.get(posicao).setStatus("PRONTO");
                    //Remove processo da lista
                    listaProcessos.remove(posicao);
                    //Atualiza a quantidade de processos
                    quantidadeProcessos --;
                    listaDePrioridades();
                }    
            }
        
        }
    }
    
    private void comPrioridadesDinamicas (){
        
    }
    
    private void escalonamentoDaLoteria (){
        
    }
}
