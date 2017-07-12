package modelo;

import javafx.scene.paint.Color;

/**
 *
 * @author IgorCamargo
 */
public class Processo {
    private Color cor;
    private int pid;
    private int prioridade;
    private String estado;
    private int tempoCPU;
    private int qtdFrames;
    private String tipoProcesso;

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
