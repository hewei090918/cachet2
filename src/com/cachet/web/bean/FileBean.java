package com.cachet.web.bean;

public class FileBean implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2919523305619013580L;
	
	private String fileName;
	private byte[] file;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	
	

}
