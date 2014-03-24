package com.quizmaster.components.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.quizmaster.components.Answer;
import com.quizmaster.components.ComponentIterator;
import com.quizmaster.components.Question;

public class QuestionTest {
	List<Answer> answerPool = new ArrayList<Answer>();
	List<Answer> correct = new ArrayList<Answer>();
	List<Answer> wrong = new ArrayList<Answer>();
	Question question;
	
	@Before
	public void setUp() throws Exception {
		//Generate random questions
		for(int i = 0; i < 2; i++)
			correct.add(new Answer("CorrectAnswer" + i, true));
		
		for(int i = 0; i < 2; i++)
			wrong.add(new Answer("FalseAnswer" + i, false));
		
		answerPool.addAll(correct);
		answerPool.addAll(wrong);
		
		//Create a question
		question = new Question("Is this a test question?", answerPool, 4, 2);
	}

	@Test
	public void correctAnswer() {
		question.shuffle();
		
		question.answer(correct.get(0));
		question.answer(correct.get(1));
		
		assertTrue("isAnswered: ", question.isAnswered());
		assertTrue("isCorrect: ", question.isCorrect());
	}
	
	@Test
	public void wrongAnswer() {
		question.shuffle();
		
		question.answer(correct.get(0));
		question.answer(wrong.get(0));
		
		assertTrue("isAnswered: ", question.isAnswered());
		assertTrue("!isCorrect: ", !question.isCorrect());
	}
	
	@Test
	public void unasnwer() {
		question.shuffle();
		
		question.answer(correct.get(0));
		question.answer(wrong.get(0));
		question.unanswer(wrong.get(0));
		question.answer(correct.get(1));
		
		assertTrue("isAnswered: ", question.isAnswered());
		assertTrue("isCorrect: ", question.isCorrect());
	}
	
	@Test
	public void iterator() {
		ComponentIterator<Answer> iterator = question.iterator();
		int i;
		
		for(i = 0; iterator.hasNext(); i++)
			iterator.next();
		
		assertTrue("iterator", i == 4);
	}
}
