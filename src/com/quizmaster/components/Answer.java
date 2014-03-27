package com.quizmaster.components;

public class Answer extends Component<String> {
	private static final long serialVersionUID = 1L;
	
	//Indicates if the answer is correct or not
	private boolean correct;
	
	/*--------------------------------------------------------------------*/
	/**
	 * Constructs an answer object with the given text.
	 * @param answer The text to be displayed
	 * @param correct Indicates if the answer is correct or not
	 */
	public Answer(String answer, boolean correct) {
		super(answer);
		this.correct = correct;
	}
	
	/**
	 * Indicates if the answer is correct or not
	 * @return True if correct, false otherwise
	 */
	public boolean isCorrect() {
		return correct;
	}
}
