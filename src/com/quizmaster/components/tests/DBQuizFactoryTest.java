package com.quizmaster.components.tests;

import com.quizmaster.components.Quiz;
import com.quizmaster.components.ScoreStrategy;
import com.quizmaster.factory.DBQuizFactory;
import com.quizmaster.runner.QuizRunner;

public class DBQuizFactoryTest {
	
	public static void main(String args[]) throws Exception {
		DBQuizFactory factory = new DBQuizFactory("qm", "quizmaster");
		
		/*Answer answer = factory.createAnswer(1, true);
		
		System.out.println(answer);*/
		
		/*List<Answer> answers = factory.createAnswerList(1, false);
		
		for(Answer answer : answers) 
			System.out.println(answer);*/
		
		/*Question question = factory.createQuestion(1);
		
		System.out.println(question);*/
		
		/*List<Question> questions = factory.createQuestionList("Test1");
		
		for(Question question : questions) 
			System.out.println(question);*/
		
		//Print all the available quizzes
		String[] quizzes = factory.getAvailableQuizzes();
		System.out.print("Available quizzes: ");
		for(String q : quizzes) 
			System.out.print(q + " ");
		System.out.println();
		
		Quiz quiz = factory.createQuiz("Test1");
		QuizRunner quizRunner = new QuizRunner();
		ScoreStrategy strategy = new ScoreStrategy();
		
		double score = quizRunner.runQuiz(quiz, strategy);
		
		System.out.println("Score: " + score);
	}
}
