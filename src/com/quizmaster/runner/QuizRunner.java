package com.quizmaster.runner;

import java.util.List;
import java.util.Scanner;

import com.quizmaster.components.Answer;
import com.quizmaster.components.ComponentIterator;
import com.quizmaster.components.Question;
import com.quizmaster.components.Quiz;
import com.quizmaster.components.ScoreStrategy;

public class QuizRunner {
	
	/**
	 * Runs a given quiz. 
	 * @param quiz
	 */
	public double runQuiz(Quiz quiz, ScoreStrategy scoreStrategy) {
		//Default implementation
		Scanner scanner = new Scanner(System.in);
		ComponentIterator<Question> iterator = quiz.iterator();
		int questionIndex = 1;
		
		//Cycle through the questions
		while(iterator.hasNext()) {
			//Fetch question
			Question question = iterator.next();
			
			//Fetch the displayed answers
			List<Answer> answers = question.getDisplayedAnswers();
			
			//Print question
			System.out.println(questionIndex++ + ") " + question);
			
			//Read all the answers
			while(!question.isAnswered()) {
				char input = scanner.next().charAt(0);
				int number = input - 'a';
				
				if(number >= answers.size()) {
					System.out.println("Invalid answer.");
					continue;
				}
				
				question.answer(answers.get(number));
			}
		}
		
		return scoreStrategy.score(quiz);
	}
	
}
