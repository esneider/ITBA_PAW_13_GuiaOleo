package ar.edu.itba.it.paw.manager;

import java.io.InputStream;

import ar.edu.itba.it.paw.dao.JDBCPictureDAO;
import ar.edu.itba.it.paw.dao.interfaces.PictureDAO;
import ar.edu.itba.it.paw.model.Picture;

public class PictureManager {

	private static PictureManager self;
	private PictureDAO pictureDAO;

	public synchronized static PictureManager getInstance() {

		if (self == null) {
			self = new PictureManager(new JDBCPictureDAO());
		}

		return self;
	}

	private PictureManager(PictureDAO pictureDAO) {

		this.pictureDAO = pictureDAO;
	}
	
	public Picture insert(InputStream is, String mime) {

		Picture pic = new Picture(is, mime);
		pictureDAO.insert(pic);
		return pic;
	}
	
	public Picture getPictureById(int id) {

		return pictureDAO.getPictureById(id);
	}
	
}
