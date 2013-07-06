package ar.edu.itba.it.paw.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Persistent entity base class.
 * <p>
 * This class is to be extended by classes that should be stored in a persistent location, and do not have a name associated with them. What
 * this class adds, is a unique generated ID.
 * </p>
 */
@MappedSuperclass
public class PersistentEntity {

	private static final long serialVersionUID = -5584265661434823148L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	protected Integer id;
	
	public PersistentEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public boolean isNew() {
		return (getId() == null);
	}
}