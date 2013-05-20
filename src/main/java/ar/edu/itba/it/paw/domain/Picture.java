package ar.edu.itba.it.paw.domain;

import java.io.InputStream;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Transient;


@Entity
public class Picture extends AbstractModel {

	private String mime;

	@Transient
	private InputStream is;

	@Lob
	private byte[] img;

	public Picture() {}

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
	

}
