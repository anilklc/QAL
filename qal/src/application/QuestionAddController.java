package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import DB.DBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class QuestionAddController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label answer_label;

    @FXML
    private TextField answer_text;

    @FXML
    private Button control_button;

    @FXML
    private TextField examName__text;

    @FXML
    private Label examName_label;

    @FXML
    private Label image_label;

    @FXML
    private TextField image_text;

    @FXML
    private Label info;

    @FXML
    private Label info1;

    @FXML
    private Label info1_label;

    @FXML
    private Label info2_label;

    @FXML
    private Label info3_label;

    @FXML
    private Label info4_label;

    @FXML
    private Label info5_label;

    @FXML
    private Label info6_label;

    @FXML
    private ComboBox<String> class_cBox;

    @FXML
    private Label lesson_label;

    @FXML
    private Label password_label;

    @FXML
    private PasswordField password_text;

    @FXML
    private Button questionAdd_button;

    @FXML
    private Label totalQuestion_label;

    @FXML
    private TextField totalQuestion_text;

    @FXML
    private Label username_label;

    @FXML
    private TextField username_text;

    @FXML
    private Label uyari_label;
    
    @FXML
    private Label finishDate_label;

    @FXML
    private DatePicker finish_date;
    
    @FXML
    private Label startDate_label;

    @FXML
    private DatePicker start_date;
    
    @FXML
    private Label questionInfo_label;
    
    @FXML
    private Label uyariExamName_label;
    
    DBHelper db=new DBHelper();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;
    
    
    void comboBoxLoad(ComboBox<String> cmb) {
    	
    	try {	
    		db.connectOpen();
        	query="SELECT * FROM student";
        	statement=db.connection.prepareStatement(query);
        	resultSet=statement.executeQuery();
        	
        	while (resultSet.next())
            { cmb.getItems().addAll(resultSet.getString("studentClass")); 
            }
        	db.connectClose();
            statement.close();
            resultSet.close();	
       
    		
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
		} 
    	
    }
   
    String lesson,teacherID;
    
    
    void userInfo() {
     	
     	try {	
     		db.connectOpen();
     		query="SELECT * FROM teacher where teacherUsername=? and teacherPassword=?";
         	statement=db.connection.prepareStatement(query);
        	statement.setString(1, username_text.getText().trim());
        	statement.setString(2, password_text.getText().trim());
        	resultSet=statement.executeQuery();
        	resultSet.next();
        	
        	if (username_text.getText().equals(resultSet.getString("teacherUsername")) && password_text.getText().equals(resultSet.getString("teacherPassword")))
            {  teacherID=resultSet.getString("teacherID");
               lesson=resultSet.getString("teacherLessonID");
            }
        	db.connectClose();
            statement.close();
            resultSet.close();
     	}catch (Exception e) {
     		System.out.println(e.getMessage());
 		} 
     	
     }
    int examControl=0;
    void examNameControl(String examName) {
    	try {
		db.connectOpen();
    	query="SELECT * FROM exam WHERE examName=?";
    	statement=db.connection.prepareStatement(query);
    	statement.setString(1,examName__text.getText().trim());
    	resultSet=statement.executeQuery();
    	resultSet.next();
    	if (examName__text.getText().equals(resultSet.getString("examName")))
        {  examControl=1;
        }
    	db.connectClose();
        statement.close();
        resultSet.close();
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}  
    }
    void examAdd(){
       	try {System.out.println(start_date.getValue());
    		db.connectOpen();
        	query="INSERT INTO exam(examName,totalQuestion,teacherID,lessonID,class,startDate,finishDate) VALUES (?,?,?,?,?,?,?)";
        	statement=db.connection.prepareStatement(query);
        	statement.setString(1,examName__text.getText().trim());
        	statement.setString(2,totalQuestion_text.getText().trim());
        	statement.setString(3,teacherID);
        	statement.setString(4,lesson);
        	statement.setString(5,class_cBox.getSelectionModel().getSelectedItem());
        	statement.setString(6,start_date.getValue().toString());
        	statement.setString(7,finish_date.getValue().toString());
        	statement.execute();
        	db.connectClose();
            statement.close();
            image_text.setDisable(false);
			answer_text.setDisable(false);
			questionAdd_button.setDisable(false);
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
    	}  
       }
    String examID;
    
   void examInfo() {
     	
     	try {	
     		db.connectOpen();
     		query="SELECT * FROM exam WHERE examName=? AND class=?";
         	statement=db.connection.prepareStatement(query);
        	statement.setString(1, examName__text.getText().trim());
        	statement.setString(2, class_cBox.getSelectionModel().getSelectedItem());
        	resultSet=statement.executeQuery();
        	resultSet.next();
        	if (examName__text.getText().equals(resultSet.getString("examName")) && class_cBox.getSelectionModel().getSelectedItem().equals(resultSet.getString("class")))
            {  examID=resultSet.getString("examID");
            }
        	db.connectClose();
            statement.close();
            resultSet.close();
     	}catch (Exception e) {
     		System.out.println(e.getMessage());
 		} 
     	
     }
    
       void questionAdd() {
    	
    	try {	
    		db.connectOpen();
    		query="INSERT INTO question(questionPath,questionAnswer,examID) VALUES (?,?,?)";
        	statement=db.connection.prepareStatement(query);
        	statement.setString(1,image_text.getText().trim());
        	statement.setString(2,answer_text.getText().trim());
        	statement.setString(3,examID);
        	statement.execute();
        	db.connectClose();
            statement.close();
    		
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
		} 
    	
    }
   
   int count=0;
    @FXML
    void control_button_Click(ActionEvent event) {
    	
    	if(username_text.getText()!="" & password_text.getText()!="") {
    		userInfo();
    		examNameControl(examID);
    		if(examControl==0) {
    		if(class_cBox.getSelectionModel().getSelectedItem()!=null & examName__text.getText()!="" & totalQuestion_text.getText()!="" & lesson!=null & teacherID!=null & start_date.toString()!=null & finish_date.toString()!=null) {
    			examAdd();
    			count=Integer.parseInt(totalQuestion_text.getText().trim());
    			questionInfo_label.setText("Kalan Eklenebilir Soru Sayısı: "+count);
    			
    		}
    		else {
    			uyari_label.setVisible(true);
    		}
    	}
    		else{
    			uyariExamName_label.setVisible(true);
    		}}
    	
    	else {
    		
    		uyari_label.setVisible(true);
    	}
    }
 String imageControl;
 
 
    @FXML
    void questionAdd_button_Click(ActionEvent event) {
    	examInfo();
    if(count>0) {
    	if(image_text.getText()!="" & answer_text.getText()!="") {
    		
        		questionAdd();
        	
    	}
    	count--;
    }
    questionInfo_label.setText("Kalan Eklenebilir Soru Sayısı: "+count);
    }

    @FXML
    void initialize() {
       comboBoxLoad(class_cBox);

    }

}
