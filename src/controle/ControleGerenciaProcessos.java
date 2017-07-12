package controle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;

/**
 *
 * @author IgorCamargo
 */
public class ControleGerenciaProcessos implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private StackPane stack;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void chamaTelaCriacaoProcesso(){
        stack.getChildren().clear();
        stack.getChildren().add(getNode("CriacaoProcesso.fxml"));
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
