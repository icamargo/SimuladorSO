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
import javafx.stage.Modality;
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
    
    ControleMemoria controleMemoria = new ControleMemoria();

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configuraColunas();
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
    }

    @FXML
    public void suspenderProcesso() {
        Processo processoAux = tblProcessos.getSelectionModel().getSelectedItem();
        processoAux.setEstado("Suspenso");
    }

    @FXML
    public void prosseguirProcesso() {
        Processo processoAux = tblProcessos.getSelectionModel().getSelectedItem();
        processoAux.setEstado("Aguardando");
    }
    
    @FXML
    public void finalizarProcesso(){
        Processo processoAux = tblProcessos.getSelectionModel().getSelectedItem();
        listaProcessos.remove(processoAux);
        controleMemoria.removeProcessoMemoria(processoAux);
    }

    @FXML
    public void chamaTelaAlteracaoPrioridade() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent telaNovaPrioridade = fxmlLoader.load(getClass().getResource("/visao/NovaPrioridade.fxml"));
        
        ControleNovaPrioridade controleNovaPrioridade = fxmlLoader.<ControleNovaPrioridade>getController();
        controleNovaPrioridade.setProcesso(tblProcessos.getSelectionModel().getSelectedItem());
        
        Scene sceneNovaPrioridade = new Scene(telaNovaPrioridade);
        stage.setScene(sceneNovaPrioridade);
        stage.setTitle("Alteração de Prioridade");
        stage.setResizable(false);
        stage.show();
        
        
        
        
        
        /*FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/visao/NovaPrioridade.fxml"));
        Scene sceneNovaPrioridade = new Scene(fxmlLoader.load());
        
        ControleNovaPrioridade controleNovaPrioridade = fxmlLoader.<ControleNovaPrioridade>getController();
        controleNovaPrioridade.setProcesso(tblProcessos.getSelectionModel().getSelectedItem());
        
        Stage stageNovaPrioridade = new Stage();
        stageNovaPrioridade.initModality(Modality.APPLICATION_MODAL);
        stageNovaPrioridade.setScene(sceneNovaPrioridade);
        stageNovaPrioridade.show();
        */
    }
    
    @FXML
    public void atualizaDadosTabela(){
        tblProcessos.getItems().setAll(listaProcessos);
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
    
    
}
