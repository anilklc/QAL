package application;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import DB.DBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ExamStudentController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private RadioButton a_rbutton;

    @FXML
    private RadioButton b_rbutton;

    @FXML
    private RadioButton c_rbutton;

    @FXML
    private RadioButton d_rbutton;

    @FXML
    private RadioButton e_rbutton;

    @FXML
    private Button examControl_button;

    @FXML
    private Button examGo_button;

    @FXML
    private ComboBox<String> exam_cBox;

    @FXML
    private Button finishExam_button;

    @FXML
    private Label info;

    @FXML
    private Label info1;

    @FXML
    private Label lesson_label;

    @FXML
    private Button nextQuestion_button;

    @FXML
    private Label password_label;

    @FXML
    private PasswordField password_text;


    @FXML
    private Label questionInfo_label;

    @FXML
    private ToggleGroup see_type;

    @FXML
    private Label username_label;

    @FXML
    private TextField username_text;

    @FXML
    private Label uyari_label;
    
    @FXML
    private AnchorPane questionPane;
    
    @FXML
    private ImageView questionImage;
    
    DBHelper db=new DBHelper();
    PreparedStatement statement;
    ResultSet resultSet;
    String query;
    
    String studentClass,studentID;
   void userInfo() {
     	
     	try {	
     		db.connectOpen();
     		query="SELECT * FROM student where studentUsername=? and studentPassword=?";
         	statement=db.connection.prepareStatement(query);
        	statement.setString(1, username_text.getText().trim());
        	statement.setString(2, password_text.getText().trim());
        	resultSet=statement.executeQuery();
        	resultSet.next();
        	
        	if (username_text.getText().equals(resultSet.getString("studentUsername")) && password_text.getText().equals(resultSet.getString("studentPassword")))
            {  studentID=resultSet.getString("studentID");
               studentClass=resultSet.getString("studentClass");
            }
        	db.connectClose();
            statement.close();
            resultSet.close();
     	}catch (Exception e) {
     		System.out.println(e.getMessage());
 		} 
     	
     }

    

    void comboBoxLoad(ComboBox<String> cmb) {
	
	try {	
		db.connectOpen();
    	query="SELECT * FROM exam INNER JOIN result ON exam.examID=result.examID WHERE class=? AND result.studentID!=? AND startDate<=(SELECT CURRENT_DATE) AND finishDate>=(SELECT CURRENT_DATE)";
    	statement=db.connection.prepareStatement(query);
    	statement.setString(1,studentClass);
    	statement.setString(2,studentID);
    	resultSet=statement.executeQuery();
    	while (resultSet.next())
        { cmb.getItems().addAll(resultSet.getString("examName")); 
        }
    	db.connectClose();
        statement.close();
        resultSet.close();	
   
		
	}catch (Exception e) {
		System.out.println(e.getMessage());
	} 
	
    }
    String examID,totalQuestion;
     void examInfo() {
     	
     	try {	
     		db.connectOpen();
     		query="SELECT * FROM exam WHERE examName=? AND class=?";
         	statement=db.connection.prepareStatement(query);
        	statement.setString(1, exam_cBox.getSelectionModel().getSelectedItem());
        	statement.setString(2, studentClass);
        	resultSet=statement.executeQuery();
        	
        	while(resultSet.next()){  
        	   examID=resultSet.getString("examID");
               totalQuestion=resultSet.getString("totalQuestion");
            }
        	db.connectClose();
            statement.close();
            resultSet.close();
     	}catch (Exception e) {
     		System.out.println(e.getMessage());
 		} 
     	
     }
     
     ArrayList<String> questionPath = new ArrayList<String>();
     ArrayList<String> questionAnswer = new ArrayList<String>(); 
     void questionUpload() {
      	
      	try {	
      		db.connectOpen();
      		query="SELECT * FROM question WHERE examID=?";
          	statement=db.connection.prepareStatement(query);
         	statement.setString(1, examID);
         	resultSet=statement.executeQuery();
         	while(resultSet.next()){
         	    questionPath.add(resultSet.getString("questionPath"));
                questionAnswer.add(resultSet.getString("questionAnswer"));
             }
         	db.connectClose();
             statement.close();
             resultSet.close();
      	}catch (Exception e) {
      		System.out.println(e.getMessage());
  		} 
      	
      }
     
     void resultAdd(){
    	   	try {
    			db.connectOpen();
    	    	query="INSERT INTO result(studentID,examID,correctQuestion,wrongQuestion,point,loginDate) VALUES (?,?,?,?,?,(SELECT CURRENT_DATE))";
    	    	statement=db.connection.prepareStatement(query);
    	    	statement.setString(1,studentID);
    	    	statement.setString(2,examID);
    	    	statement.setString(3,String.valueOf(cQuestion));
    	    	statement.setString(4,String.valueOf(wQuestion));
    	    	statement.setString(5,String.valueOf(point));
    	    	statement.execute();
    	    	db.connectClose();
    	        statement.close();
    	        
    		}catch (Exception e) {
    			System.out.println(e.getMessage());
    		}  
    	   }
     
     void questionSee(int index) {
    	 Image myImage = new Image(getClass().getResourceAsStream(questionPath.get(index)));
    	 questionImage.setImage(myImage);
     }
  
     
    @FXML
    void examControl_button_Click(ActionEvent event) {
      if(username_text.getText()!="" & password_text.getText()!="") {
    	  userInfo();
    	  exam_cBox.getItems().clear();
      	  comboBoxLoad(exam_cBox);
      	  examGo_button.setDisable(false);
      }
      else {
    	  uyari_label.setVisible(true);
      }
    }
    int questionCount,questionCountConstant;
    
    @FXML
    void examGo_button_Click(ActionEvent event){
      if(exam_cBox.getSelectionModel().getSelectedItem()!=null) {
    	examInfo();  
    	questionPane.setDisable(false);
    	nextQuestion_button.setDisable(false);
    	questionInfo_label.setText("Kalan Soru Sayısı: "+totalQuestion);
    	questionCount=Integer.parseInt(totalQuestion);
    	questionCountConstant=Integer.parseInt(totalQuestion);
    	questionUpload();
    	questionSee(0);
    }}
    
    
    ArrayList<String> questionAnswerStudent= new ArrayList<String>();
    int questionIndex=0;
    @FXML
    void nextQuestion_button(ActionEvent event) {
    	if(questionIndex!=questionCountConstant+1) {
        	
    	    if(a_rbutton.isSelected()) {
    	    	questionAnswerStudent.add("A");
    	    	questionInfo_label.setText("Kalan Soru Sayısı: "+questionCount);
    	     }
    	    else if(b_rbutton.isSelected()) {
    	    	questionAnswerStudent.add("B");
    	   	}
    	    else if(c_rbutton.isSelected()) {
    	    	questionAnswerStudent.add("C");	
    	   	}
    	    else if(d_rbutton.isSelected()) {
    	    	questionAnswerStudent.add("D");
    	   	}
    	    else if(e_rbutton.isSelected()) {
    	    	questionAnswerStudent.add("E"); 	
    	   	}    
    	    else {
    	    	questionAnswerStudent.add(" ");	
    	    }
    	    questionIndex++;
    	    if(questionIndex!=questionCountConstant) {
    	    questionSee(questionIndex);}
    	    else {
    	    	nextQuestion_button.setDisable(true);
    	    	finishExam_button.setDisable(false);
    	    }
    	    questionCount--;
    	    questionInfo_label.setText("Kalan Soru Sayısı: "+questionCount);
    	    System.out.println(questionIndex+"--"+questionCountConstant);
    	    }
    	
    }
   int cQuestion=0,wQuestion=0,point=0;
    @FXML
    void finishExam_button_Click(ActionEvent event) {
    	cQuestion=0;wQuestion=0;point=0;
    	System.out.println(questionAnswer);
    	System.out.println(questionAnswerStudent);
    	int pointquestion=100/questionCountConstant;
    	int controlCount=0;
    	while(controlCount!=questionCountConstant) {
    		if(questionAnswer.get(controlCount).equals(questionAnswerStudent.get(controlCount))) {
    			cQuestion++;
    			point=point+pointquestion;
    		}
    		else {
    			wQuestion++;
    		}
    		controlCount++;
    	}
    	
    	System.out.println(wQuestion+"----"+cQuestion+"---"+point);
    	if(cQuestion==questionCountConstant) {point=100;
    	resultAdd();
    	}
    	else {
    	resultAdd();
    	}
    	
    }
    
    
    
    @FXML
    void initialize() {
    	
    }

}
