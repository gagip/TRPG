package application;

import java.net.URL;
import java.util.ResourceBundle;

import character.Player;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	@FXML private TextArea gameInfoTxtAr, statTxtAr, InventoryTxtAr;
	@FXML private TextField userInputTxtFd;
	@FXML private Button userInputBtn;
	
	Player player;

	
	public Controller() {
		player = GameManager.getInstance().player;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		statTxtAr.setText(String.format(
				"체력: %d/%d\n"
				+ "공격력: %d\n"
				+ "방어력: %d"
				, player.getHp(), player.getMaxHp()
				, player.getAttack()
				, player.getDefense()));
		
//		// 로그인 버튼 이벤트
//		loginBtn.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				String id = idInput.getText();
//				String pw = pwInput.getText();
//				
//				
//				switch (model.login(id, pw)) {
//				case SUCCESS:
//					resultLabel.setText("로그인 성공");
//					break;
//				case NOT_EXIST_ID:
//					resultLabel.setText("존재하지 않는 아이디입니다");
//					break;
//				case PASSWORD_MISMATCH:
//					resultLabel.setText("비밀번호가 일치하지 않습니다");
//					break;
//				case EMPTY_FILED:
//					resultLabel.setText("아이디나 비밀번호를 입력하지 않았습니다");
//					break;
//				}
//			}
//		});
		
	}
	
}
