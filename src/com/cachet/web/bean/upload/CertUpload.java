package com.cachet.web.bean.upload;

import com.cachet.web.bean.FileBean;
import com.cachet.web.model.Cert;

public class CertUpload extends Cert{

	private static final long serialVersionUID = 8155942561770863133L;
	
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
