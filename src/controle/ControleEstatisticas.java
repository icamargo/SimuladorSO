package controle;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import modelo.Processo;
import static visao.SimuladorSO.listaProcessos;

/**
 *
 * @author IgorCamargo 
 * @author Tatiane Paz
 */
public class ControleEstatisticas implements Initializable{
    
    @FXML
    private Label LabelHorarioDeInicio;
    
    @FXML
    private Label LabelNumeroDeProcessos;
    
    @FXML
    private  Label TempoTotalUCP;
            
    @FXML
    private Label LabelEstadoProntoEspera;
            
    @FXML
    private Label LabelMemoriaBytes;
            
    @FXML
    private Label LabelTotalPageFault;
    
    @FXML
    private Label LabelTempoDecorrido;
    
    @FXML
    private Label LabelProntos;
    
    @FXML
    private Label LabelExecucao;
    
    @FXML
    private Label LabelEspera;
    
    @FXML
    private Label LabelThroughput;
    
    @FXML
    private Label LabelTurnaround;
    
    @FXML
    private Label LabelUtilizacaoUCP;
    
    @FXML
    private Label LabelEsperaMedia;
    
    @FXML
    private Label LabelEsperaAcumulada;
    
    @FXML
    private Label LabelMemoriaEmUso;
    
    @FXML
    private Label LabelLivre;
    
    @FXML
    private Label LabelPageFaults;
    
    @FXML
    private Label LabelHwPageFault;
    
    @FXML
    private Label LabelSwPageFault;
    
    @FXML
    private Label SeparatorEstatisticas;
    
    @FXML
    private Label LabelProcessosEscalonados;
    
    private ArrayList<Processo> listaEscalonador = new ArrayList();
    private ArrayList<Processo> listaEsperaIO = new ArrayList();
    private int quantidadeProcessos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaEscalonador = (ArrayList<Processo>) listaProcessos;
        quantidadeProcessos = listaEscalonador.size();
        atualizaEstatisticas();
        LabelHorarioDeInicio.setUserData(0);
    } 

    public void atualizaEstatisticas(){
        
    }
}
