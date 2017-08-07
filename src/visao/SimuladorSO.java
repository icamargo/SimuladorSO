package visao;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.CelulaArqPaginacao;
import modelo.CelulaMemoria;
import modelo.Processo;

/**
 *
 * @author IgorCamargo
 */
public class SimuladorSO extends Application {
    
    public static ArrayList<Processo> listaProcessos = new ArrayList();
   //public static ObservableList<Processo> listaProcessos = FXCollections.observableArrayList();
    public static int pidAtual = 0;
    public static ArrayList<CelulaMemoria> memoria = new ArrayList();
    public static ArrayList<Node> listaNosMemoria = new ArrayList();
    public static ArrayList<Node> listaNosArqPaginacao = new ArrayList();
    public static ArrayList<CelulaArqPaginacao> arquivoPaginacao = new ArrayList();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent telaGerenciaProcessos =  FXMLLoader.load(getClass().getResource("GerenciaProcessos.fxml"));
        Scene sceneGerenciaProcessos = new Scene(telaGerenciaProcessos);
        stage.setScene(sceneGerenciaProcessos);
        stage.setTitle("Gerência de Processos");
        stage.setX(0);
        stage.setY(0);
        stage.setResizable(false);
        stage.show();
        
        Stage stageGerenciaMemoria = new Stage();
        Parent telaGerenciaMemoria = FXMLLoader.load(getClass().getResource("GerenciaMemoria.fxml"));
        Scene sceneGerenciaMemoria = new Scene(telaGerenciaMemoria);
        stageGerenciaMemoria.setScene(sceneGerenciaMemoria);
        stageGerenciaMemoria.setTitle("Gerência de Memória");
        stageGerenciaMemoria.setX(620);
        stageGerenciaMemoria.setY(0);
        stageGerenciaMemoria.setResizable(false);
        stageGerenciaMemoria.show();
        
        Stage stageGerenciaProcessador = new Stage();
        Parent telaGerenciaProcessador = FXMLLoader.load(getClass().getResource("GerenciaProcessador.fxml"));
        Scene sceneGerenciaProcessador = new Scene(telaGerenciaProcessador);
        stageGerenciaProcessador.setScene(sceneGerenciaProcessador);
        stageGerenciaProcessador.setTitle("Gerência de Processador");
        stageGerenciaProcessador.setX(0);
        stageGerenciaProcessador.setY(270);
        stageGerenciaProcessador.setResizable(false);
        stageGerenciaProcessador.show();
        
        Stage stageArquivoPaginacao = new Stage();
        Parent telaArquivoPaginacao = FXMLLoader.load(getClass().getResource("ArquivoPaginacao.fxml"));
        Scene sceneArquivoPaginacao = new Scene(telaArquivoPaginacao);
        stageArquivoPaginacao.setScene(sceneArquivoPaginacao);
        stageArquivoPaginacao.setTitle("Arquivo de Paginação");
        stageArquivoPaginacao.setX(1070);
        stageArquivoPaginacao.setY(0);
        stageArquivoPaginacao.setResizable(false);
        stageArquivoPaginacao.show();
        
        Stage stageParametrosSistema = new Stage();
        Parent telaParametrosSistema = FXMLLoader.load(getClass().getResource("ParametrosSistema.fxml"));
        Scene sceneParametrosSistema = new Scene(telaParametrosSistema);
        stageParametrosSistema.setScene(sceneParametrosSistema);
        stageParametrosSistema.setTitle("Parametros do Sistema");
        stageParametrosSistema.setX(620);
        stageParametrosSistema.setY(360);
        stageParametrosSistema.setResizable(false);
        stageParametrosSistema.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
