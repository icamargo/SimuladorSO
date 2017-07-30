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
        stageGerenciaMemoria.setX(610);
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
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
