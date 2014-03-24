package com.quizmaster.components.tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.quizmaster.components.Answer;
import com.quizmaster.components.ComponentIterator;
import com.quizmaster.components.Question;
import com.quizmaster.components.Quiz;
import com.quizmaster.components.ScoreStrategy;

public class QuizTest {
	Quiz quiz;
	List<Answer> answers1, answers2;

	@Before
	public void setUp() throws Exception {
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

	@Test
	public void correctTest() {
		ComponentIterator<Question> iterator = quiz.iterator();
		iterator.next().answer(answers1.get(0));
		iterator.next().answer(answers2.get(0));
		ScoreStrategy ss = new ScoreStrategy();
		
		assertTrue("Correct Test: " + ss.score(quiz), ss.score(quiz) == 2.0);
	}

}
