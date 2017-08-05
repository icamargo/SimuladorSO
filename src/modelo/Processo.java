package modelo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import static visao.SimuladorSO.pidAtual;

/**
 *
 * @author IgorCamargo
 * @author tatianefpg
 */
public class Processo {
    //private Color cor;
    private Rectangle cor = new Rectangle(55, 10);
    private int pid; //Identificador processo
    private int prioridade;
    private String estado; //{Executando(está na CPU),Pronto(finalizou I/O e fica em loop),Bloqueado(I/O),Aguardando(aguardando CPU),Suspenso(suspndido pelo usuário)}
    private int tempoCPU; //tempo executando CPU
    private int qtdFrames;
    private String tipoProcesso; //{CPU-Bound, I/O-Bound}
    //Igual tempoCPU private int 
    private int quantumProcesso;//Escalonador (inicializando com zero);
    private int framesExecutados;  //Escalonador (iniciar com a quantidade de frames)
    
    public Processo(){
        this.pid = pidAtual++;
        this.estado = "Aguardando";
    }
    
    public Processo(Color cor, int prioridade, int qtdFrames, String tipoProcesso) {
        this.pid = pidAtual++;
        this.cor.setFill(cor);
        this.prioridade = prioridade;
        this.qtdFrames = qtdFrames;
        this.tipoProcesso = tipoProcesso;
    }
    
    public Rectangle getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor.setFill(cor);
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
    
    public int getFramesExecutados() {
        return framesExecutados;
    }

    public void setFramesExecutados(int framesExecutados) {
        this.framesExecutados = framesExecutados;
    }

    public int getQuantumProcesso() {
        return quantumProcesso;
    }

    public void setQuantumProcesso(int quantumProcesso) {
        this.quantumProcesso = quantumProcesso;
    }

}
