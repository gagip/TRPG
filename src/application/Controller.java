package application;

import java.net.URL;
import java.util.ResourceBundle;

import character.Player;
import enemy.Enemy;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import manager.BattleManager;
import manager.GameManager;
import manager.ScriptManager;



/**
 * FXML을 제어하는 클래스
 * @author gagip
 *
 */
public class Controller implements Initializable {
	@FXML private Label timerLbl;
	@FXML public TextArea gameInfoTxtAr, statTxtAr, inventoryTxtAr;
	@FXML private TextField userInputTxtFd;
	@FXML private Button userInputBtn, startBtn;
	
	private GameManager gm;
	private Thread timerThread;
	
	
	
	public Controller() {
		// 게임 매니저 세팅
		this.gm = GameManager.getInstance();
		GameManager.setController(this);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gameInfoTxtAr.setText("");
		statTxtAr.setText("");
		inventoryTxtAr.setText("");
		
		
		
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				gm.gameStart();
			}
		});
		
		userInputBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				String str = userInputTxtFd.getText();
				userInputTxtFd.clear();
				gm.userInput(str);
			}
		});
	}
		
	public void startTimer() {
		timerThread = new Thread(new Timer());
		timerThread.start();
	}
	
	private class Timer implements Runnable {
		@Override
		public void run() {
			while(gm.getRunning()) {
				try {
					gm.subtractTimer();
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							timerLbl.setText(Integer.toString(gm.getTimer()));
						}
					});
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

