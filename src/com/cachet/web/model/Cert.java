package com.cachet.web.model;

import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "CERT", indexes = {
        @Index(columnList = "CERT_ID", unique = true),
        @Index(columnList = "CERT_NAME"),
        @Index(columnList = "CERT_TYPE"),
        @Index(columnList = "CERT_URL1"),
        @Index(columnList = "CERT_URL2"),
        @Index(columnList = "CREATE_TIME"),
        @Index(columnList = "SCBZ")
})
public class Cert implements java.io.Serializable {

	private static final long serialVersionUID = 3002813204122159076L;
	// Fields
	/**
     * 主键
     */
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="cert_id", unique = true)
	private Integer certId;
	
	/**
     * 证件名称
     */
	@Column(name="cert_name")
	private String certName;
	
	/**
     * 证件类型
     */
	@Column(name="cert_type")
	private Integer certType;
	
	/**
     * 证件存放位置1
     */
	@Column(name="cert_url1")
	private String certUrl1;
	
	/**
     * 证件存放位置2
     */
	@Column(name="cert_url2")
	private String certUrl2;
	
	 /**
     * 创建时间
     */
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name="create_time")
	private Date createTime;
	
	 /**
     * 删除标识
     */
	@Column(name="scbz")
	private String scbz;

	public Integer getCertId() {
		return this.certId;
	}

	public void setCertId(Integer certId) {
		this.certId = certId;
	}

	public String getCertName() {
		return this.certName;
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

	public String getCertUrl1() {
		return this.certUrl1;
	}

	public void setCertUrl1(String certUrl1) {
		this.certUrl1 = certUrl1;
	}

	public String getCertUrl2() {
		return this.certUrl2;
	}

	public void setCertUrl2(String certUrl2) {
		this.certUrl2 = certUrl2;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getScbz() {
		return this.scbz;
	}

	public void setScbz(String scbz) {
		this.scbz = scbz;
	}

}