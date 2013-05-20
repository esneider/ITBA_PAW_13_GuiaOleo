package ar.edu.itba.it.paw.domain.interfaces;

import ar.edu.itba.it.paw.domain.Picture;

public interface PictureRepo {
	
	/**
	 * Obtains a single Picture by its id
	 */
	public Picture getPictureById(int id);

}
