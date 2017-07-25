/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import modelo.Processo;

/**
 *
 * @author tatianefpg
 */
public class ControleParametrosSistema {

    @FXML
    private Group GroupPoliticasDeEscalonamentoController;

    int quantumSistema = 10;
    int quantumAtual;
    int posicao = 0;
    
    public void Scheduler (Group GroupPoliticasDeEscalonamentoController ,ArrayList<Processo> processos){
        switch (GroupPoliticasDeEscalonamentoController.hashCode()) {
            //RadioButtonRoundRobimSimples
            case 1:
                roundRobimSimples(processos);
                break;
            //RadioButtonRoundRobimComPrioridades
            case 2:
            	robimComPrioridades(processos);
		break;
            //RadioButtonComPrioridades
            case 3:
		comPrioridades(processos);
		break;
            //RadioButtonComPrioridadesDinamicas
            case 4:
		comPrioridadesDinamicas(processos);
		break;
            //RadioButtonEscalonamentoDaLoteria
            case 5:
                escalonamentoDaLoteria(processos);
                break;
            default:
		break;
	}			
    }
    
    private void roundRobimSimples (ArrayList<Processo> processos){
        int quantidadeProcessos = processos.size();
        
        //Verifica se existe processo na lista de processos
        while(quantidadeProcessos > 0){
            
            //Roda o quantum para todos os processos
            for(posicao = 0; posicao <= quantidadeProcessos; posicao++){
                quantumAtual = 0;
                processos.get(posicao).setStatus("EXECUTANDO");
                
                if(processos.get(posicao).quantum > quantumSistema){
                    processos.get(posicao).quantum = processos.get(posicao).quantum - quantumSistema;
                    while(quantumAtual <= quantumSistema){
                        System.out.println(processos.get(posicao).quantum);
                        quantumAtual ++;
                    }
                    //processso ficou bloqueado pois acabou seu quantum
                    processos.get(posicao).setStatus("BLOQUEADO");
                    
                }
                else{
                    while(processos.get(posicao).quantum != quantumAtual){
                        quantumAtual ++;
                    }
                    //processo finalizado
                    processos.get(posicao).quantum = 0;
                    processos.get(posicao).setStatus("PRONTO");
                    //Remove processo da lista
                    processos.remove(posicao);
                }    
            }
        
        }
    }
    
    
    private void robimComPrioridades (ArrayList<Processo> processos){
        listaDePrioridades(processos);
        //COPIA CODIGO roundRobimSimples
    }
    
    private ArrayList listaDePrioridades(ArrayList<Processo> processos){
        
        int aux = 0;
        
        //Coloca os processos em ordem de prioridade
        for(int i = 0; i < processos.size(); i++){
		for(int j = 0; j < processos.size() -1; j++){
			if(processos.get(j).id > processos.get(j+1).id){
				aux = processos.get(j).id;
				processos.get(j).id = processos.get(j+1).id;
				processos.get(j+1).id = aux;
			}
		}
	}
        return processos;
    }
    
    private void comPrioridades (ArrayList<Processo> processos){
        
    }
    
    private void comPrioridadesDinamicas (ArrayList<Processo> processos){
        
    }
    
    private void escalonamentoDaLoteria (ArrayList<Processo> processos){
        
    }
}
