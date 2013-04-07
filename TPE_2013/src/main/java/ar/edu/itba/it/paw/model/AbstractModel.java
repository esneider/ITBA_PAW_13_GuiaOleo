package ar.edu.itba.it.paw.model;

public abstract class AbstractModel {

	private int id;
	
	protected AbstractModel (int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}
