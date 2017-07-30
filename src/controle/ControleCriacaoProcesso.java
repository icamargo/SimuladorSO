package controle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import modelo.Processo;
import util.MaskTextField;
import static visao.SimuladorSO.listaProcessos;

/**
 *
 * @author IgorCamargo
 */
public class ControleCriacaoProcesso implements Initializable {

    private ControleMemoria controleMemoria = new ControleMemoria();
    private ControleProcessos controleProcessos = new ControleProcessos();

    @FXML
    private ChoiceBox chBoxPrioridade;
    @FXML
    private ChoiceBox chBoxFrames;
    @FXML
    private ChoiceBox chBoxTipoProcesso;
    @FXML
    private ColorPicker clrPickerCorProcesso;
    @FXML
    private MaskTextField txtFieldNroProcessos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chBoxPrioridade.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        chBoxFrames.getItems().addAll(1, 2, 3, 4, 5);
        chBoxTipoProcesso.getItems().addAll("CPU-Bound", "I/O-Bound");
        txtFieldNroProcessos.setMask("NNN");
    }

    @FXML
    public void criarProcesso(){
        Processo novoProcesso = new Processo();

        novoProcesso.setPrioridade((int) chBoxPrioridade.getValue());
        novoProcesso.setQtdFrames((int) chBoxFrames.getValue());
        novoProcesso.setTipoProcesso((String) chBoxTipoProcesso.getValue());
        novoProcesso.setCor(clrPickerCorProcesso.getValue());
        listaProcessos.add(novoProcesso);
        
        controleMemoria.adicionaProcessoMemoria(novoProcesso);
        //controleProcessos.atualizaDadosTabela();
    }
}
