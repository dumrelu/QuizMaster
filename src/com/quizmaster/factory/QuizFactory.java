package com.quizmaster.factory;

import java.io.Serializable;

import com.quizmaster.components.Quiz;

public interface QuizFactory extends Serializable {

	public Quiz createQuiz(String quizName);
	
}
