package Models;

public class tableViewResult {
  String studentName;
  String teacherName;
  String examName;
  String lessonName;
  String correctQuestion;
  String wrongQuestion;
  String point;
  String loginDate;
  
  public tableViewResult(String studentName,String teacherUsername,String examName,String lessonName,String correctQuestion,String wrongQuestion,String point,String loginDate) {
	  this.studentName=studentName;
	  this.teacherName=teacherUsername;
	  this.examName=examName;
	  this.lessonName=lessonName; 
	  this.correctQuestion=correctQuestion; 
	  this.wrongQuestion=wrongQuestion;
	  this.point=point; 
	  this.loginDate=loginDate; 
}

public String getStudentName() {
	return studentName;
}

public void setStudentName(String studentName) {
	this.studentName = studentName;
}

public String getTeacherName() {
	return teacherName;
}

public void setTeacherName(String teacherName) {
	this.teacherName = teacherName;
}

public String getExamName() {
	return examName;
}

public void setExamName(String examName) {
	this.examName = examName;
}

public String getLessonName() {
	return lessonName;
}

public void setLessonName(String lessonName) {
	this.lessonName = lessonName;
}

public String getCorrectQuestion() {
	return correctQuestion;
}

public void setCorrectQuestion(String correctQuestion) {
	this.correctQuestion = correctQuestion;
}

public String getWrongQuestion() {
	return wrongQuestion;
}

public void setWrongQuestion(String wrongQuestion) {
	this.wrongQuestion = wrongQuestion;
}

public String getPoint() {
	return point;
}

public void setPoint(String point) {
	this.point = point;
}

public String getLoginDate() {
	return loginDate;
}

public void setLoginDate(String loginDate) {
	this.loginDate = loginDate;
}

  
}
