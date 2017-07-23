package controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author IgorCamargo
 */
public class ControleProcessos implements Initializable {
    
    //ArrayList de processos
    List<Processo> processos = new ArrayList<Processo>();
                  
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    void chamaTelaCriacaoProcesso(){
        Stage stage2 = new Stage();
        StackPane stack = new StackPane();
        //stack.getChildren().add(getNode("/visao/CriacaoProcesso.fxml"));
        stack.getChildren().add(getNode("/visao/GerenciaMemoria.fxml"));
        
        //Scene criacaoProcesso = new Scene(stack, 500, 250); // CriacaoProcesso
        Scene criacaoProcesso = new Scene(stack, 575, 575); // GerenciaMemoria
        stage2.setScene(criacaoProcesso);
        stage2.show();
    }
    
    public Node getNode(String node){
        Node no = null;
        try {
            no = FXMLLoader.load(getClass().getResource(node));
        } catch (Exception e) {
        }
        return no;
        
    }

public static class Processo {
    private Color cor;
    private int pid;
    private int prioridade;
    private String estado;
    private int tempoCPU;
    private int qtdFrames;
    private String tipoProcesso;
    //Tempo de execucao
    private static float quantum;
    //{EXECUTANDO,PRONTO,BLOQUEADO,AGUARDANDO}
    private static String status;

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
}
