package com.quizmaster.components;

import java.util.List;

public class Quiz extends Component<Question> {
	
	/**
	 * Constructs a quiz object with the given name and given questions
	 * @param quizName Quiz name
	 * @param questions The questions
	 */
	public Quiz(String quizName, List<Question> questions) {
		super(quizName, questions);
	}
	
	public void reset() {
		for(Question question : getComponents())
			question.shuffle();
	}
}
