package application;


import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import DB.DBHelper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.AnchorPane;


public class LoginController {
    
    @FXML
    private AnchorPane scenePane;
    
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
    
    @FXML
    private Label id_label;
    
    public void stage(String stageName) throws Exception {
    	Stage window= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource(stageName));
        Image icon = new Image(getClass().getResourceAsStream("/image/icon.png"));
		window.getIcons().add(icon); 
		window.setTitle("QAL");
		window.initStyle(StageStyle.UTILITY);
        window.setScene(new Scene(root));
        window.show();
        Stage stage = (Stage) scenePane.getScene().getWindow();
        stage.close();
    }
  
   

	DBHelper db=new DBHelper();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;

	
    @FXML
    void login_button_Click(ActionEvent event) {   	 
        if(username_text.getText()!="" & password_text.getText()!="") {
    	if(student_rbutton.isSelected()) {
        	try {
        		db.connectOpen();
        	query="SELECT * FROM student where studentUsername=? and studentPassword=?";
        	statement=db.connection.prepareStatement(query);
        	statement.setString(1, username_text.getText().trim());
        	statement.setString(2, password_text.getText().trim());
        	resultSet=statement.executeQuery();
        	resultSet.next();
        	
        	if (username_text.getText().equals(resultSet.getString("studentUsername")) && password_text.getText().equals(resultSet.getString("studentPassword")))
            {  
        		stage("MainStudent.fxml");
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
        	resultSet.next();
        	if (username_text.getText().equals(resultSet.getString("teacherUsername")) && password_text.getText().equals(resultSet.getString("teacherPassword")))
            {
        		stage("MainTeacher.fxml");
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
        	resultSet.next();
        	if (username_text.getText().equals(resultSet.getString("adminUsername")) && password_text.getText().equals(resultSet.getString("adminPassword")))
            {
        		stage("MainAdmin.fxml");
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
