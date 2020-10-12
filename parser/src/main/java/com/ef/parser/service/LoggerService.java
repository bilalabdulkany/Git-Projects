package com.ef.parser.service;

import java.util.List;

public interface LoggerService {

	public List<String> getHourlyRequestedIps(String startdate,int threshold);
	public List<String> getDailyRequestedIps(String startdate,int threshold);
	public void checkHourlyLimitMovetoBlockedList(String ip,String startdate);
	public void checkDailyLimitMovetoBlockedList(String ip,String startdate);
	
}
