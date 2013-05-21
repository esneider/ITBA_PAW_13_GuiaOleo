package ar.edu.itba.it.paw.domain;


public interface PictureRepo {
	
	/**
	 * Obtains a single Picture by its id
	 */
	public Picture getPictureById(int id);

}
