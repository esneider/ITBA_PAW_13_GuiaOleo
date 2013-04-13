package ar.edu.itba.it.paw.model;

public abstract class AbstractModel {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractModel other = (AbstractModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	private int id;
	
	protected AbstractModel (int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
}
