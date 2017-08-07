package controle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.Processo;
import util.MaskTextField;
import static visao.SimuladorSO.listaProcessos;

/**
 * FXML Controller class
 *
 * @author IgorCamargo
 */
public class ControleNovaPrioridade implements Initializable {
    
    @FXML
    private MaskTextField txtFieldNovaPrioridade;
    
    private Processo processo;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFieldNovaPrioridade.setMask("NN");
    }
    
    @FXML
    public void alterarPrioridadeProcesso(){
        //listaProcessos.get(listaProcessos.indexOf(processo)).setPrioridade(Integer.parseInt(txtFieldNovaPrioridade.getText()));
        processo.setPrioridade(Integer.parseInt(txtFieldNovaPrioridade.getText()));
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }
    
}
