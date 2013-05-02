package ar.edu.itba.it.paw.model;

import java.io.InputStream;

public class Picture extends AbstractModel {

	private String mime;
	private InputStream is;
	private byte[] img;
	
	public Picture(int id, InputStream is, String mime) {
		
		super(id);
		this.is = is;
		this.mime = mime;
	}
	
	public Picture(int id, byte[] img, String mime) {
		super(id);
		this.img = img;
		this.mime = mime;
	}

	public Picture(InputStream is, String mime) {

		this(NO_ID, is, mime);
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
