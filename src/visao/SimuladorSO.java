package visao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author IgorCamargo
 */
public class SimuladorSO extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent telaGerenciaProcessos=  FXMLLoader.load(getClass().getResource("GerenciaProcessos.fxml"));
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
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
