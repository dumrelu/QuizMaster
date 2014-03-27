package com.quizmaster.factory;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import com.quizmaster.components.Quiz;

public interface QuizFactory extends Serializable, Remote {

	public Quiz createQuiz(String quizName) throws RemoteException;
	
}
