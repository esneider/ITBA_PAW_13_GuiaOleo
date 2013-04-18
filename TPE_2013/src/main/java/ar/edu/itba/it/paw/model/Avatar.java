package ar.edu.itba.it.paw.model;

import java.io.FileInputStream;

public class Avatar extends AbstractModel {
	
	private byte[] image;
	private String imgName;
	private FileInputStream fs;
	private int fileLength = 0;
	
	public Avatar(int id, byte[] image, String imgName) {
		super(id);
		this.image = image;
		this.imgName = imgName;
		this.fs = null;
	}
	
	public Avatar(int id, FileInputStream fs, String imgName, int fileLength) {
		super(id);
		this.fs = fs;
		this.imgName = imgName;
		this.image = null;
		this.fileLength = fileLength;
	}
	
	public Avatar(FileInputStream fs, String imgName, int fileLength) {
		this(-1, fs, imgName, fileLength);
	}
	
	public Avatar(byte[] image, String imgName) {
		this(-1, image, imgName);
	}
	
	public byte[] getImage() {
		return image;
	}

	public String getImgName() {
		return imgName;
	}

	public FileInputStream getFs() {
		return fs;
	}
	
	public int getFileLength() {
		return fileLength;
	}
	

}
