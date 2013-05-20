package ar.edu.itba.it.paw.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.dao.interfaces.PictureDAO;
import ar.edu.itba.it.paw.model.Picture;
import ar.edu.itba.it.paw.service.interfaces.PictureService;



@Service
public class PictureServiceImpl implements PictureService {

	private PictureDAO DAO;

	@Autowired
	private PictureServiceImpl(PictureDAO pictureDAO) {
		this.DAO = pictureDAO;
	}

	public Picture insert(InputStream is, String mime) {
		Picture pic = new Picture(is, mime);
		DAO.insert(pic);
		return pic;
	}

	public Picture getPictureById(int id) {
		return DAO.getPictureById(id);
	}

}
