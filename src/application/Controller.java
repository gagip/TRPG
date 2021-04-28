package application;

import java.net.URL;
import java.util.ResourceBundle;

import character.Player;
import item.Inventory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import manager.GameManager;
import manager.ScriptManager;



/**
 * FXML을 제어하는 클래스
 * @author gagip
 *
 */
public class Controller implements Initializable {
	@FXML private Label timerLbl;
	@FXML private TextArea gameInfoTxtAr, statTxtAr, inventoryTxtAr;
	@FXML private TextField userInputTxtFd;
	@FXML private Button userInputBtn, startBtn;
	
	private static Controller instance = new Controller();
	
	public static Controller getInstance() {
		return instance;
	}
	
	Player player;
   	public static ObjectProperty<Player> playerProperty = new SimpleObjectProperty<Player>(); 
	public static ObjectProperty<Inventory> invenProperty = new SimpleObjectProperty<Inventory>();
	public static StringProperty gameInfoProperty = new SimpleStringProperty();
	
	public Controller() {
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gameInfoTxtAr.textProperty().bind(gameInfoProperty);
		statTxtAr.textProperty().bind(playerProperty.asString());
		inventoryTxtAr.textProperty().bind(invenProperty.asString());
		
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				GameManager.getInstance().start();
			}
		});
	}
	
	public void setGameInfoProperty(String str) {
		gameInfoProperty.set(str);
	}
	
	public void setPlayerProperty(Player player) {
		playerProperty.set(player);
	}
	
	public void setInvenProperty(Inventory inven) {
		invenProperty.set(inven);
	}
}
