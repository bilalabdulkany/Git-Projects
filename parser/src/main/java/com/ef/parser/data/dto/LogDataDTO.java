package com.ef.parser.data.dto;

public class LogDataDTO {
	
	
	private String startDate;	
	private String ip;	
	private String httprequest;	
	private int httpcode;
	private String useragent;
	
	
	
	public LogDataDTO(String startDate, String ip, String httprequest, int httpcode, String useragent) {
		super();
		this.startDate = startDate;
		this.ip = ip;
		this.httprequest = httprequest;
		this.httpcode = httpcode;
		this.useragent = useragent;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
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
