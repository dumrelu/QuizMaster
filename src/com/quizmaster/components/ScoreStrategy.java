package com.quizmaster.components;

import java.io.Serializable;

public class ScoreStrategy implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Calculates the score for the given quiz.
	 * @param quiz The quiz
	 * @return The score
	 */
	public double score(Quiz quiz) {	
		//Default implementation for the strategy, 1 point for each correctly 
		//answered question
		double score = 0;
		ComponentIterator<Question> iterator = quiz.iterator();
		Question question = null;
		
		for(; iterator.hasNext(); ) {
			question = iterator.next();
			if(question.isCorrect())
				score++;
		}
			
		return score;
	}
	
}
