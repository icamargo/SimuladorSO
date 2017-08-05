package controle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import modelo.CelulaArqPaginacao;
import modelo.Processo;
import static visao.SimuladorSO.arquivoPaginacao;
import static visao.SimuladorSO.listaNosArqPaginacao;

/**
 * FXML Controller class
 *
 * @author IgorCamargo
 */
public class ControleArquivoPaginacao implements Initializable {

    @FXML
    private GridPane gridPaneArqPaginacao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 0; i < 100; i++) {
            Label labelIndice = new Label();
            labelIndice.setFont(Font.font(8));
            labelIndice.setText(String.valueOf(i));
            labelIndice.setPadding(new Insets(0, 0, 10, 0));

            Label labelR = new Label();
            labelR.setText("R");
            labelR.setFont(Font.font(12));
            labelR.setTextFill(Color.RED);
            labelR.setAlignment(Pos.CENTER);
            labelR.setVisible(false);

            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(5);
            hBox.getChildren().addAll(labelIndice, labelR);
            listaNosArqPaginacao.add(hBox);
        }
        int indice = 0;
        for (int i = 0; i < 10; i++) { //i = linha
            for (int j = 0; j < 10; j++) { // j = coluna
                gridPaneArqPaginacao.add(listaNosArqPaginacao.get(indice), i, j);
                indice++;
            }
        }
    }

    public void adicionaProcessoArqPaginacao(Processo processo) {
        for (int i = 0; i < processo.getQtdFrames(); i++) {
            CelulaArqPaginacao novaCelulaArqPaginacao = new CelulaArqPaginacao(processo);

            arquivoPaginacao.add(novaCelulaArqPaginacao);

            int indiceArqPaginacao = (arquivoPaginacao.size() - 1);
            mostraR(indiceArqPaginacao);
        }
    }
    
    private void mostraR(int indiceArqPaginacao){
        HBox hBoxAux = new HBox();
        Label labelR = new Label();
        Label labelIndice = new Label();
        
        hBoxAux = (HBox) listaNosArqPaginacao.get(indiceArqPaginacao);
        
        labelIndice = (Label) hBoxAux.getChildren().get(0);
        labelIndice.setTextFill(Color.RED);
        
        labelR = (Label) hBoxAux.getChildren().get(1);
        labelR.setVisible(true);
    }
}
