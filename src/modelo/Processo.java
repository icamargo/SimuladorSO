package modelo;

import javafx.scene.paint.Color;

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
    private int tempoCPU;   //ACHO QUE NÃO USA
    private int qtdFrames;
    private String tipoProcesso;
    //Tempo de execucao
    public static int quantum;
    //{EXECUTANDO,PRONTO,BLOQUEADO,AGUARDANDO}
    private String status;
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public static float getQuantum() {
        return quantum;
    }

    public static void setQuantum(int quantum) {
        Processo.quantum = quantum;
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

}
