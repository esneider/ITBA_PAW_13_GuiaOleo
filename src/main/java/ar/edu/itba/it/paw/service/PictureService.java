package ar.edu.itba.it.paw.service;

import java.io.InputStream;

import ar.edu.itba.it.paw.model.Picture;

public interface PictureService {

	public Picture insert(InputStream is, String mime);

	public Picture getPictureById(int id);

}
