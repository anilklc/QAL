package Models;

public class tableViewAskQuestion {
	
	String questionID;
	String questionName;
	String question;
	String answer;
	String studentClass;
	String lessonName;
	String studentName;
	String teacherName;
	String questionDate;
	String answerDate;
	
	 public tableViewAskQuestion(String questionID,String questionName,String question,String answer,String studentClass,String lessonName,String studentName,String teacherName,String questionDate,String answerDate) {
		 this.questionID=questionID;
		 this.questionName=questionName;
		 this.question=question;
		 this.answer=answer;
		 this.studentClass=studentClass;
		 this.lessonName=lessonName;
		 this.studentName=studentName;
		 this.teacherName=teacherName;
		 this.questionDate=questionDate;
		 this.answerDate=answerDate;
	}

	public String getQuestionID() {
		return questionID;
	}

	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
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

	public String getQuestionDate() {
		return questionDate;
	}

	public void setQuestionDate(String questionDate) {
		this.questionDate = questionDate;
	}

	public String getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}
	 

}
