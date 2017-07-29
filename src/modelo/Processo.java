package modelo;

import javafx.scene.paint.Color;
import static visao.SimuladorSO.pidAtual;

/**
 *
 * @author IgorCamargo
 * @author tatianefpg
 */
public class Processo {
    private Color cor;
    private int pid;
    private int prioridade;
    private String estado;  //ACHO QUE NÃO USA
    //{EXECUTANDO,PRONTO,BLOQUEADO,AGUARDANDO}
    private int tempoCPU;
    private int qtdFrames;
    private String tipoProcesso;
    //Tempo de execucao
    private int quantumProcesso;
    private String status;
    public int id;

    public Processo(){
        this.pid = pidAtual++;
        this.estado = "Pronto";
    }
    
    public Processo(Color cor, int prioridade, int qtdFrames, String tipoProcesso) {
        this.pid = pidAtual++;
        this.cor = cor;
        this.prioridade = prioridade;
        this.qtdFrames = qtdFrames;
        this.tipoProcesso = tipoProcesso;
    }

    public int getQuantumProcesso() {
        return quantumProcesso;
    }

    public void setQuantumProcesso(int quantumProcesso) {
        this.quantumProcesso = quantumProcesso;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTempoCPU() {
        return tempoCPU;
    }

    public void setTempoCPU(int tempoCPU) {
        this.tempoCPU = tempoCPU;
    }

    public int getQtdFrames() {
        return qtdFrames;
    }

    public void setQtdFrames(int qtdFrames) {
        this.qtdFrames = qtdFrames;
    }

    public String getTipoProcesso() {
        return tipoProcesso;
    }

    public void setTipoProcesso(String tipoProcesso) {
        this.tipoProcesso = tipoProcesso;
    }

    public void getQuantumProcesso(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
