package com.quizmaster.components.tests;

import java.util.ArrayList;
import java.util.List;

import com.quizmaster.components.Answer;
import com.quizmaster.components.Question;
import com.quizmaster.components.Quiz;
import com.quizmaster.components.ScoreStrategy;
import com.quizmaster.runner.QuizRunner;

public class QuizRunnerTest {
	
	private static Quiz quiz;
	private static List<Answer> answers1, answers2;

	public static void setUp() {
		//Set up answers
		answers1 = new ArrayList<Answer>();
		answers2 = new ArrayList<Answer>();
		
		for(int i = 0; i < 4; i++) {
			boolean correct = (i == 0) ? true : false;
			answers1.add(new Answer( ( (correct) ? "Correct" :"Wrong") +  "Answer" + i, correct));
			answers2.add(new Answer( ( (correct) ? "Correct" :"Wrong") +  "Answer" + i, correct));
		}
		
		//Set up questions
		List<Question> questions = new ArrayList<Question>();
		questions.add(new Question("Question1?", answers1, 4, 1));
		questions.add(new Question("Question2?", answers2, 4, 1));
		
		//Set up quiz
		quiz = new Quiz("TestQuiz", questions);
	}
	
	public static void main(String args[]) {
		setUp();
		QuizRunner defaultQuizRunner = new QuizRunner();
		double result = defaultQuizRunner.runQuiz(quiz, new ScoreStrategy());
		System.out.println("Result: " + result);
	}
	
}
