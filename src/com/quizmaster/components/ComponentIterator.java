package com.quizmaster.components;

public class ComponentIterator<T> {
	//The component that is going to be iterated
	private Component<T> component;
	
	//Current index
	private int index;
	
	/*--------------------------------------------------------------*/
	/**
	 * Constructs and iterator object for the given component with the starting index of 0
	 * @param component The component
	 */
	public ComponentIterator(Component<T> component) { 
		this.component = component;
		this.index = 0;
	}
	
	/**
	 * Sets the index to 0
	 */
	public void first() {
		this.index = 0;
	}
	
	/**
	 * Indicates if there is at least one more component in the iterator
	 * @return True is it has next, false otherwise
	 */
	public boolean hasNext() {
		return (index < component.size());
	}
	
	/**
	 * Returns the next component in the list and advances to the next one
	 * @return The corresponding component or null if the limit was reached
	 */
	public T next() {
		if(index >= component.size())
			return null;
		
		return component.get(index++);
	}
}
