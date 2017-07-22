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
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author IgorCamargo
 */
public class ControleProcessos implements Initializable {
    
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
        Stage stageCriacaoProcesso = new Stage();
        StackPane stackCriacaoProcesso = new StackPane();
        stackCriacaoProcesso.getChildren().add(getNode("/visao/CriacaoProcesso.fxml"));
        Scene sceneCriacaoProcesso = new Scene(stackCriacaoProcesso, 500, 250);
        stageCriacaoProcesso.setScene(sceneCriacaoProcesso);
        stageCriacaoProcesso.show();
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
