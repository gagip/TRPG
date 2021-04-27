package application;

import java.net.URL;
import java.util.ResourceBundle;

import character.Player;
import item.Inventory;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import manager.GameManager;



/**
 * FXML을 제어하는 클래스
 * @author gagip
 *
 */
public class Controller implements Initializable {
	@FXML private Label timerLbl;
	@FXML private TextArea gameInfoTxtAr, statTxtAr, inventoryTxtAr;
	@FXML private TextField userInputTxtFd;
	@FXML private Button userInputBtn;
	
	private static Controller instance = new Controller();
	
	public static Controller getInstance() {
		return instance;
	}
	
	Player player;
   	public ObjectProperty<Player> playerProperty = new SimpleObjectProperty<Player>(); 
	public ObjectProperty<Inventory> invenProperty = new SimpleObjectProperty<Inventory>();
	public Controller() {
		// player ��ü�� ����
		player = GameManager.getInstance().player;
		playerProperty.set(player);
		invenProperty.set(player.getInven());
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		statTxtAr.textProperty().bind(playerProperty.asString());
		inventoryTxtAr.textProperty().bind(invenProperty.asString());
			
	}
	
}
