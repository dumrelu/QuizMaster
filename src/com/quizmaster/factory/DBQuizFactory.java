package com.quizmaster.factory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.quizmaster.components.Answer;
import com.quizmaster.components.Question;
import com.quizmaster.components.Quiz;

public class DBQuizFactory extends UnicastRemoteObject implements QuizFactory {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	
	public DBQuizFactory(String username, String password) throws Exception {
		super();
		
		Class.forName("com.mysql.jdbc.Driver");
		
		connection = DriverManager.getConnection("jdbc:mysql://localhost/QuizMaster", username, password);
	}
	
	public Quiz createQuiz(String quizName) throws RemoteException {
		try {
			//Prepare query
			String query = "SELECT * FROM Quizzes WHERE quizName=?";
			PreparedStatement ps = connection.prepareStatement(query);
			
			//Set arguments
			ps.setString(1, quizName);
			
			//Execute query
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return new Quiz(quizName, createQuestionList(quizName));
			}
			
			return null;
		} catch(SQLException e) {
			return null;
		}
	}
	
	public String[] getAvailableQuizzes() throws RemoteException {
		ArrayList<String> result = new ArrayList<String>();
		
		try {
			//Prepare query
			String query = "SELECT quizName FROM Quizzes";
			PreparedStatement ps = connection.prepareStatement(query);
			
			//Execute query
			ResultSet rs = ps.executeQuery();
			
			//Construct the result
			while(rs.next()) {
				result.add(rs.getString("quizName"));
			}
		} catch(SQLException e) {
			//Nothing
		}
		String[] r = new String[result.size()];
		
		result.toArray(r);
		
		return r;
	}
	
	private List<Question> createQuestionList(String quizName) throws SQLException {
		//Prepare query
		String query = "SELECT question_id FROM QuestionList WHERE quiz_id=?";
		PreparedStatement ps = connection.prepareStatement(query);
		
		//Set arguments
		ps.setString(1, quizName);
		
		//Execute query
		ResultSet rs = ps.executeQuery();
		
		//Create the list
		List<Question> questions = new ArrayList<Question>();
		
		//Populate list
		while(rs.next()) {
			int question_id = rs.getInt("question_id");
			
			questions.add(createQuestion(question_id));
		}
		
		return questions;
	}
	
	private Question createQuestion(int id) throws SQLException {
		//Prepare query
		String query = "SELECT * FROM Questions WHERE id=?;";
		PreparedStatement ps = connection.prepareStatement(query);
		
		//Set arguments
		ps.setInt(1, id);
		
		//Execute query
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			String text = rs.getString("question");
			int answers = rs.getInt("answers");
			int correct_answers = rs.getInt("correct_answers");
			List<Answer> answer_list = createAnswerList(id, true);	//Add correct answers
			answer_list.addAll(createAnswerList(id, false));
			
			return new Question(text, answer_list, answers, correct_answers);
		}
		
		return null;
	}
	
	private List<Answer> createAnswerList(int question_id, boolean correct) throws SQLException {
		//Prepare query
		String query = "SELECT answer_id FROM " + (correct ? "CorrectAnswers" : "WrongAnswers") +" WHERE question_id=?;";
		PreparedStatement ps = connection.prepareStatement(query);
		
		//Set arguments
		ps.setInt(1, question_id);
		
		//Execute query
		ResultSet rs = ps.executeQuery();
		
		//Create the list
		List<Answer> answers = new ArrayList<Answer>();
		
		//Populate list
		while(rs.next()) {
			int answer_id = rs.getInt("answer_id");
			
			answers.add(createAnswer(answer_id, correct));
		}
		
		return answers;
	}
	
	/**
	 * Creates an answer with the information retrieved from the db.
	 * @param id Answers table id
	 * @param correct Indicates if the answers is correct or not(depending from which table the id was retrieved 
	 * CorrectAnswers or WrongAsnwers)
	 * @return An answer object if the id is correct, null otherwise
	 * @throws SQLException
	 */
	private Answer createAnswer(int id, boolean correct) throws SQLException {
		//Prepare query
		String query = "SELECT * FROM Answers WHERE id=?";
		PreparedStatement ps = connection.prepareStatement(query);
		
		//Set arguments
		ps.setInt(1, id);
		
		//Execute query
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			String text = rs.getString("answer");
			String explanation = rs.getString("explanation");
			
			Answer answer = new Answer(text, correct);
			
			if(explanation != null)
				answer.add(explanation);
			
			return answer;
		}
		
		return null;
	}
}
