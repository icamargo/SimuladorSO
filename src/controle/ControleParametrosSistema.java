package controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import modelo.Processo;
import static visao.SimuladorSO.listaProcessos;

/**
 *
 * @author tatianefpg
 */
public class ControleParametrosSistema implements Initializable {

    @FXML
    private RadioButton rdBtnRoundRobinSimples;

    @FXML
    private RadioButton rdBtnRoundRobinComPrioridades;

    @FXML
    private RadioButton rdBtnComPrioridades;

    @FXML
    private RadioButton rdBtnComPrioridadesDinamicas;

    @FXML
    private RadioButton rdBtnEscalonamentoDaLoteria;
    
    @FXML
    private ChoiceBox chBoxPoliticaPaginas;
    
    private ToggleGroup botoesEscalonamento = new ToggleGroup();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rdBtnRoundRobinSimples.setUserData("RoundRobinSimples");
        rdBtnRoundRobinSimples.setToggleGroup(botoesEscalonamento);
        rdBtnRoundRobinComPrioridades.setUserData("RoundRobinComPrioridades");
        rdBtnRoundRobinComPrioridades.setToggleGroup(botoesEscalonamento);
        rdBtnComPrioridades.setUserData("ComPrioridades");
        rdBtnComPrioridades.setToggleGroup(botoesEscalonamento);
        rdBtnComPrioridadesDinamicas.setUserData("ComPrioridadesDinamicas");
        rdBtnComPrioridadesDinamicas.setToggleGroup(botoesEscalonamento);
        rdBtnEscalonamentoDaLoteria.setUserData("EscalonamentoDaLoteria");
        rdBtnEscalonamentoDaLoteria.setToggleGroup(botoesEscalonamento);
        
        chBoxPoliticaPaginas.getItems().addAll("Antecipada", "Por Demanda");
//        listaEscalonador = (ArrayList<Processo>) listaProcessos;
        
        
    }
}
