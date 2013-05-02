package ar.edu.itba.it.paw.dao.interfaces;

import ar.edu.itba.it.paw.model.Picture;

public interface PictureDAO {

	public void insert(Picture picture);
	
	public Picture getPictureById(int id);
	
}
