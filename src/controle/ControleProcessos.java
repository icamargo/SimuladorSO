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
import modelo.Processo;

/**
 *
 * @author IgorCamargo
 * @author Tatiane Paz
 */
public class ControleProcessos implements Initializable {
    
    //ArrayList de processos
    List<Processo> processos = new ArrayList<Processo>();
    List <int> listaPrioridades = new List<int>();             
    
    
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

    
}