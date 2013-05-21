package ar.edu.itba.it.paw.domain.picture;

import java.io.InputStream;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.cfg.Environment;

import ar.edu.itba.it.paw.domain.AbstractModel;

@Entity
public class Picture extends AbstractModel {

	private String mime;

	@Transient
	private InputStream is;

	@Basic
	private byte[] img;

	public Picture() {
	}

	public Picture(InputStream is, String mime) {

		this.is = is;
		this.mime = mime;
	}

	public Picture(byte[] img, String mime) {

		this.img = img;
		this.mime = mime;
	}

	public String getMime() {
		return mime;
	}

	public InputStream getInputStream() {
		return is;
	}

	public byte[] getBytes() {
		return this.img;
	}

	public void insert(Picture picture) {
		// TODO IMPLEMENTAR
	};

}
