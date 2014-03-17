package com.quizmaster.components;

import java.util.ArrayList;
import java.util.List;

public abstract class Component<T> {
	//The text stored by the component
	private String text;
	
	//Component's children
	private List<T> components;
	
	/*------------------------------------------------------------*/
	/**
	 * Constructs a component object with the given text
	 * @param text The text stored by the component
	 */
	public Component(String text) {
		this.text = text;
		this.components = new ArrayList<T>();
	}
	
	/**
	 * Constructs a component object with the given text and the given components
	 * @param text The text stored by the component
	 * @param components Component's children
	 */
	public Component(String text, List<T> components) {
		this.text = text;
		this.components = new ArrayList<T>(components);
	}
	
	/**
	 * Adds a component to the components list
	 * @param component The component
	 */
	public void add(T component) {
		components.add(component);
	}
	
	/**
	 * Removes a component from the components list
	 * @param component The component
	 * @return True if removed, false otherwise
	 */
	public boolean remove(T component) {
		return components.remove(component);
	}
	
	/**
	 * Retrieves the component from the given index
	 * @param index The index
	 * @return The corresponding component
	 */
	public T get(int index) {
		return components.get(index);
	}
	
	/**
	 * Creates an iterator for the current component
	 * @return An iterator for the current component
	 */
	public ComponentIterator<T> iterator() {
		return new ComponentIterator<T>(this);
	}
	
	/**
	 * Returns the number of children components
	 * @return Size of components
	 */
	public int size() {
		return components.size();
	}
}
