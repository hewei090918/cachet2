package com.cachet.web.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileBean implements java.io.Serializable{
	
	private static final long serialVersionUID = 2919523305619013580L;
	
	private String fileName;
	private File file;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	public InputStream getInputStream() throws IOException{
		return new FileInputStream(this.file);
	}
	
	
	

}
