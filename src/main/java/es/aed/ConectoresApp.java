package es.aed;

import es.aed.controllers.CoinController;
import es.aed.controllers.HistoryController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class ConectoresApp extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		CoinController coinController = new CoinController();
		HistoryController historyController = new HistoryController();
		
		TabPane tabPane = new TabPane();
		
		tabPane.getTabs().add(new Tab("Monedas", coinController.getView()));
		tabPane.getTabs().add(new Tab("Histórico", historyController.getView()));
		
		Scene scene = new Scene(tabPane, 640, 640);
		primaryStage.setTitle("Conectores");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
