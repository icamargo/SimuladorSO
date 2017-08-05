package controle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modelo.Processo;
import static visao.SimuladorSO.listaProcessos;

/**
 *
 * @author IgorCamargo
 * @author Tatiane Paz
 */
public class ControleProcessos implements Initializable {

    @FXML
    private TableView<Processo> tblProcessos;
    @FXML
    private TableColumn<Processo, Color> colCor;
    @FXML
    private TableColumn<Processo, Integer> colPID;
    @FXML
    private TableColumn<Processo, String> colPrioridade;
    @FXML
    private TableColumn<Processo, String> colEstado;
    @FXML
    private TableColumn<Processo, Integer> colTempoCPU;
    @FXML
    private TableColumn<Processo, Integer> colFrames;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configuraColunas();
        atualizaDadosTabela();
    }

    @FXML
    public void chamaTelaCriacaoProcesso() throws IOException {
        Stage stage = new Stage();
        Parent telaCriacaoProcessos = FXMLLoader.load(getClass().getResource("/visao/CriacaoProcesso.fxml"));
        Scene sceneCriacaoProcessos = new Scene(telaCriacaoProcessos);
        stage.setScene(sceneCriacaoProcessos);
        stage.setTitle("Criação de Processo");
        stage.setResizable(false);
        stage.show();
        atualizaDadosTabela();
    }

    public Node getNode(String node) {
        Node no = null;
        try {
            no = FXMLLoader.load(getClass().getResource(node));
        } catch (Exception e) {
        }
        return no;

    }

    private void configuraColunas() {
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));
        colPID.setCellValueFactory(new PropertyValueFactory<>("pid"));
        colPrioridade.setCellValueFactory(new PropertyValueFactory<>("prioridade"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colTempoCPU.setCellValueFactory(new PropertyValueFactory<>("tempoCPU"));
        colFrames.setCellValueFactory(new PropertyValueFactory<>("qtdFrames"));
    }
    
    public void atualizaDadosTabela(){
        tblProcessos.getItems().setAll(listaProcessos);
    }
}
