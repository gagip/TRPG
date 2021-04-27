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
 * FXML�� �����ϴ� Ŭ����
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
				"ü��: %d/%d\n"
				+ "���ݷ�: %d\n"
				+ "����: %d"
				, player.getHp(), player.getMaxHp()
				, player.getAttack()
				, player.getDefense()));
		
//		// �α��� ��ư �̺�Ʈ
//		loginBtn.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent arg0) {
//				String id = idInput.getText();
//				String pw = pwInput.getText();
//				
//				
//				switch (model.login(id, pw)) {
//				case SUCCESS:
//					resultLabel.setText("�α��� ����");
//					break;
//				case NOT_EXIST_ID:
//					resultLabel.setText("�������� �ʴ� ���̵��Դϴ�");
//					break;
//				case PASSWORD_MISMATCH:
//					resultLabel.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�");
//					break;
//				case EMPTY_FILED:
//					resultLabel.setText("���̵� ��й�ȣ�� �Է����� �ʾҽ��ϴ�");
//					break;
//				}
//			}
//		});
		
	}
	
}
