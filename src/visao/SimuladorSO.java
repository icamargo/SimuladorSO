/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author igor_
 */
public class SimuladorSO extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent telaGerenciaProcessos=  FXMLLoader.load(getClass().getResource("GerenciaProcessos.fxml"));
        
        Scene tela1 = new Scene(telaGerenciaProcessos);
        
        stage.setScene(tela1);
        stage.show();
        
        /*URL fxml = getClass().getResource("GerenciaProcessos.fxml");
		Parent parent = FXMLLoader.load(fxml);
		stage.setTitle("Gerência de Processos");
		stage.setScene(new Scene(parent));
		stage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
