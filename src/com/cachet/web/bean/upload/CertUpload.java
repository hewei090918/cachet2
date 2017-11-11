package com.cachet.web.bean.upload;

import com.cachet.web.bean.FileBean;

public class CertUpload {

	private Integer certId;
	
	private String certName;
	
	private Integer certType;
	//身份证正面
	private FileBean idCardFrontFile;
	//身份证反面
    private FileBean idCardBackFile;
    //警官证正面
    private FileBean policeFrontFile;
    //警官证反面
    private FileBean policeBackFile;
    //电子签名
    private FileBean signatureFile;
    //电子公章
    private FileBean cachetFile;

	public Integer getCertId() {
		return certId;
	}

	public void setCertId(Integer certId) {
		this.certId = certId;
	}

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public Integer getCertType() {
		return certType;
	}

	public void setCertType(Integer certType) {
		this.certType = certType;
	}

	public FileBean getIdCardFrontFile() {
		return idCardFrontFile;
	}

	public void setIdCardFrontFile(FileBean idCardFrontFile) {
		this.idCardFrontFile = idCardFrontFile;
	}

	public FileBean getIdCardBackFile() {
		return idCardBackFile;
	}

	public void setIdCardBackFile(FileBean idCardBackFile) {
		this.idCardBackFile = idCardBackFile;
	}

	public FileBean getPoliceFrontFile() {
		return policeFrontFile;
	}

	public void setPoliceFrontFile(FileBean policeFrontFile) {
		this.policeFrontFile = policeFrontFile;
	}

	public FileBean getPoliceBackFile() {
		return policeBackFile;
	}

	public void setPoliceBackFile(FileBean policeBackFile) {
		this.policeBackFile = policeBackFile;
	}

	public FileBean getSignatureFile() {
		return signatureFile;
	}

	public void setSignatureFile(FileBean signatureFile) {
		this.signatureFile = signatureFile;
	}

	public FileBean getCachetFile() {
		return cachetFile;
	}

	public void setCachetFile(FileBean cachetFile) {
		this.cachetFile = cachetFile;
	}
    
}
