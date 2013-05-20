package ar.edu.itba.it.paw.service.interfaces;

import java.io.InputStream;

import ar.edu.itba.it.paw.domain.Picture;

public interface PictureService {

	public Picture insert(InputStream is, String mime);

	public Picture getPictureById(int id);

}
