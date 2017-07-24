package controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

/**
 * FXML Controller class
 *
 * @author IgorCamargo
 */
public class ControleMemoria implements Initializable {

    @FXML
    private GridPane gridPaneMemoria;
    @FXML
    private TextField txtFieldLPL;
    @FXML
    private TextField txtFieldLPM;
    @FXML
    private ProgressBar progBarLPL;
    @FXML
    private ProgressBar progBarLPM;

    private ArrayList<Node> listaNos = new ArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < 100; i++) {
            Label label = new Label();
            label.setText(String.valueOf(i));
            label.setPadding(new Insets(0, 0, 10, 0));

            Circle circulo = new Circle(10, Color.GREENYELLOW);
            circulo.setStroke(Color.BLACK);
            circulo.setStrokeType(StrokeType.INSIDE);
            circulo.setVisible(false);

            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(5);
            hBox.getChildren().addAll(label, circulo);
            listaNos.add(hBox);
        }
        int indice = 0;
        for (int i = 0; i < 10; i++) { //i = linha
            for (int j = 0; j < 10; j++) { // j = coluna
                gridPaneMemoria.add(listaNos.get(indice), i, j);
                indice++;
            }
        }
    }

    @FXML
    void mostraCirculo() {
        int indice = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                HBox hBoxAux = new HBox();
                hBoxAux = (HBox) listaNos.get(indice);
                hBoxAux.getChildren().get(1).setVisible(true);
                indice++;
            }
        }
    }

    @FXML
    void ocultaCirculo() {
        int indice = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                HBox hBoxAux = new HBox();
                hBoxAux = (HBox) listaNos.get(indice);
                hBoxAux.getChildren().get(1).setVisible(false);
                indice++;
            }
        }
    }

}
