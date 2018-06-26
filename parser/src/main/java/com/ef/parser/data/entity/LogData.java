package com.ef.parser.data.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logdata")
public class LogData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "startdate")
	private Timestamp startDate;
	@Column(name = "ip")
	private String ip;
	@Column(name = "httprequest")
	private String httprequest;
	@Column(name = "httpcode")
	private int httpcode;
	@Column(name = "useragent")
	private String useragent;

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHttprequest() {
		return httprequest;
	}

	public void setHttprequest(String httprequest) {
		this.httprequest = httprequest;
	}

	public int getHttpcode() {
		return httpcode;
	}

	public void setHttpcode(int httpcode) {
		this.httpcode = httpcode;
	}

	public String getUseragent() {
		return useragent;
	}

	public void setUseragent(String useragent) {
		this.useragent = useragent;
	}

}
