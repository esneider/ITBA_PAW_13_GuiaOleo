package ar.edu.itba.it.paw.domain.picture;

import javax.persistence.Basic;
import javax.persistence.Entity;

import ar.edu.itba.it.paw.domain.AbstractModel;

@Entity
public class Picture extends AbstractModel {

	private String mime;

	@Basic
	private byte[] img;

	public Picture() {
	}

	public Picture(byte[] img, String mime) {
		this.img = img;
		this.mime = mime;
	}

	public String getMime() {
		return mime;
	}


	public byte[] getBytes() {
		return this.img;
	}

}