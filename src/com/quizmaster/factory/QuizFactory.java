package com.quizmaster.factory;

import com.quizmaster.components.Quiz;

public interface QuizFactory {

	public Quiz createQuiz(String quizName);
	
}
