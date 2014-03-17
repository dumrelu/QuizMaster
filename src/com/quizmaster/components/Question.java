package com.quizmaster.components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question extends Component<Answer> {
	//The answers that are going to be displayed
	private List<Answer> displayedAnswers;
	
	//The chosen answers
	private Set<Answer> pickedAnswers;
	
	//Number of questions to be displayed
	private int answers;
	
	//Number of correct answers in the displayed answers
	private int correctAnswers;
	
	/*------------------------------------------------*/
	/**
	 * Constructs a question object with the given parameters
	 * @param question The text to be displayed
	 * @param answers Number of questions to be displayed
	 * @param correctAnswers Number of correct answers in the displayed answers
	 */
	public Question(String question, int answers, int correctAnswers) {
		super(question);
		this.answers = answers;
		this.correctAnswers = correctAnswers;
		this.displayedAnswers = new ArrayList<Answer>();
		this.pickedAnswers = new HashSet<Answer>();
		shuffle();
	}
	
	/**
	 * Constructs a question object with the given parameters
	 * @param question The text to be displayed
	 * @param nAnswers Number of questions to be displayed
	 * @param correctAnswers Number of correct answers in the displayed answers
	 * @param answers Initial answer pool
	 */
	public Question(String question, List<Answer> answers, int nAnswers, int correctAnswers) {
		this(question, nAnswers, correctAnswers);
		this.add(answers);
	}
	
	/**
	 * TODO:
	 */
	protected void shuffle() {
		//TODO: shuffle
	}
	
	/**
	 * TODO:
	 * @param answer
	 */
	public boolean answer(Answer answer) {
		if(!displayedAnswers.contains(answer) || pickedAnswers.size() >= answers)
			return false;
		
		return pickedAnswers.add(answer);
	}
	
	/**
	 * TODO:
	 * @param answer
	 */
	public boolean unanswer(Answer answer) {
		return pickedAnswers.remove(answer);
	}
	
	public List<Answer> getDisplayedAnswers() {
		return displayedAnswers;
	}
	
	public Set<Answer> getPickedAnswers() {
		return pickedAnswers;
	}
	
	/**
	 * Indicates if the correct number of questions has been chosen
	 * @return True if enough chosen, false otherwise
	 */
	public boolean isAnswered() {
		return (pickedAnswers.size() == answers);
	}
}
