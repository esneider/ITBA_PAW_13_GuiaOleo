package ar.edu.itba.it.paw.model;

import java.io.InputStream;

public class Picture extends AbstractModel {

	private String mime;
	private InputStream is;
	private byte[] img;
	
	public Picture(InputStream is, String mime) {
		super();
		this.is = is;
		this.mime = mime;
	}
	
	public Picture(byte[] img, String mime) {
		super();
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
