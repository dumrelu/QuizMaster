package com.quizmaster.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Component<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
	
	public void add(List<T> components) {
		this.components.addAll(components);
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
	
	protected List<T> getComponents() {
		return components;
	}
	
	/**
	 * Returns the number of children components
	 * @return Size of components
	 */
	public int size() {
		return components.size();
	}
	
	public String toString() {
		return text;
	}
	
	public String getText() { return text; }
	public void setText(String text) { this.text = text; }
}
