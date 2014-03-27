package com.quizmaster.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question extends Component<Answer> {
	private static final long serialVersionUID = 1L;
	
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
	protected Question(String question, int answers, int correctAnswers) {
		super(question);
		this.answers = answers;
		this.correctAnswers = correctAnswers;
		this.displayedAnswers = new ArrayList<Answer>();
		this.pickedAnswers = new HashSet<Answer>();
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
		shuffle();
	}
	
	/**
	 * Resets the pickedAnswers and displayedAnswers. Reconstructs the displayedAnswers by picking random correct 
	 * answers from the answers pool and completing it with wrong ones.
	 */
	public void shuffle() {
		//Clear previous data
		pickedAnswers.clear();
		displayedAnswers.clear();
		
		List<Answer> correct = new ArrayList<Answer>(), wrong = new ArrayList<Answer>();
		List<Answer> answers = this.getComponents();
		
		for(Answer answer : answers)
			if(answer.isCorrect())
				correct.add(answer);
			else
				wrong.add(answer);
		
		//Shuffle lists
		Collections.shuffle(correct);
		Collections.shuffle(wrong);
		
		//Add the corresponding number of correct answer to the displayed questions
		int i;
		for(i = 0; i < correctAnswers && correct.size() != 0; i++, correct.remove(0))
			displayedAnswers.add(correct.get(0));
		
		//Complete the rest with wrong answers
		for(;i < this.answers && wrong.size() != 0; i++, wrong.remove(0))
			displayedAnswers.add(wrong.get(0));
		
		//Shuffle again
		Collections.shuffle(displayedAnswers);
	}
	
	/**
	 * Adds the answer in the pickedAnswers unique sets if the answer is contained in the displayAnswers list 
	 * and the maximum number of answers was not exceeded
	 * @param answer The chosen answer
	 * @return True if added to pickedAnswers set, false otherwise
	 */
	public boolean answer(Answer answer) {
		if(!displayedAnswers.contains(answer) || pickedAnswers.size() >= answers)
			return false;
		
		return pickedAnswers.add(answer);
	}
	
	/**
	 * Removes an answer from the pickedAnswers set
	 * @param answer The chosen answer
	 * @return True if the answer was removed from the pickedAnswers set, false otherwise
	 */
	public boolean unanswer(Answer answer) {
		return pickedAnswers.remove(answer);
	}
	
	/**
	 * @return The answers that are going to be displayed
	 */
	public List<Answer> getDisplayedAnswers() {
		return displayedAnswers;
	}
	
	/**
	 * @return The chosen answers
	 */
	public Set<Answer> getPickedAnswers() {
		return pickedAnswers;
	}
	
	/**
	 * Indicates if the correct number of questions has been chosen
	 * @return True if enough chosen, false otherwise
	 */
	public boolean isAnswered() {
		return (pickedAnswers.size() == correctAnswers);
	}
	
	/**
	 * Indicates if the chosen answers are the correct ones or not
	 * @return True if the question was answered correctly, false otherwise
	 */
	public boolean isCorrect() {
		if(!isAnswered())
			return false;
		
		for(Answer answer : pickedAnswers)
			if(!answer.isCorrect()) 
				return false;
		
		return true;
	}
	
	public String toString() {
		String result = getText() + "(" + correctAnswers + " answers)" + "\n";
		char index = 'a';
		
		for(Answer answer : displayedAnswers)
			result += "\t" + index++ + ") " + answer + "\n";
		
		return result;
	}
}
