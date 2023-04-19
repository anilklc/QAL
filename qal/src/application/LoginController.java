package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import DB.DBHelper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class LoginController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton admin_rbutton;

    @FXML
    private Button close_button;

    @FXML
    private ToggleGroup giris_tipi;

    @FXML
    private Button login_button;

    @FXML
    private PasswordField password_text;

    @FXML
    private RadioButton student_rbutton;

    @FXML
    private RadioButton teacher_rbutton;

    @FXML
    private TextField username_text;
    
    @FXML
    private Label info_label;

    DBHelper db=new DBHelper();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;
    @FXML
    void login_button_Click(ActionEvent event) {
        if(username_text.getText()!="" & password_text.getText()!="") {
    	if(student_rbutton.isSelected()) {
        	try {db.connectOpen();
        	query="SELECT * FROM student where studentUsername=? and studentPassword=?";
        	statement=db.connection.prepareStatement(query);
        	statement.setString(1, username_text.getText().trim());
        	statement.setString(2, password_text.getText().trim());
        	resultSet=statement.executeQuery();
        	while(resultSet.next()) {
        		
        		System.out.println("ogrenci giriş başarılı");
        	}
        	db.connectClose();
            statement.close();
            resultSet.close();
        	}catch (Exception e) {
        	  info_label.setVisible(true);
        	}
        		
        }
        else if(teacher_rbutton.isSelected()) {
        	try {db.connectOpen();
        	query="SELECT * FROM teacher where teacherUsername=? and teacherPassword=?";
        	statement=db.connection.prepareStatement(query);
        	statement.setString(1, username_text.getText().trim());
        	statement.setString(2, password_text.getText().trim());
        	resultSet=statement.executeQuery();
        	while(resultSet.next()) {
        		
        		System.out.println("ogretmen giriş başarılı");
        	}
        	db.connectClose();
            statement.close();
            resultSet.close();
        	}catch (Exception e) {
        	  info_label.setVisible(true);
        	}
        }
        else if(admin_rbutton.isSelected()) {
        	try {db.connectOpen();
        	query="SELECT * FROM admin where adminUsername=? and adminPassword=?";
        	statement=db.connection.prepareStatement(query);
        	statement.setString(1, username_text.getText().trim());
        	statement.setString(2, password_text.getText().trim());
        	resultSet=statement.executeQuery();
        	while(resultSet.next()) {
        		
        		System.out.println("admin giriş başarılı");
        	}
        	db.connectClose();
            statement.close();
            resultSet.close();
        	}catch (Exception e) {
        	  info_label.setVisible(true);
        	}
        }
        else {
        info_label.setVisible(true);
        	
        }}
        
        else {
        	info_label.setVisible(true);
        }
        
    }
    @FXML
    void close_button_Click(ActionEvent event) {
      Platform.exit();
    }
    
    @FXML
    void initialize() {
        info_label.setVisible(false);
    }

}
