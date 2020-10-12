package com.ef.parser.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.ef.parser.data.entity.BlockedIP;
import com.ef.parser.data.repository.BlockedIPRepository;
import com.ef.parser.data.repository.LogInfoRepository;
import com.ef.parser.service.LoggerService;

@PropertySource("classpath:application.properties")
@Service
public class LoggerServiceImpl implements LoggerService {

	@Value("${hourly.limit}")
	private int hourlyLimit;

	@Value("${daily.limit}")
	private int dailyLimit;
	
	@Value("${hourly.limit.block.comment}")
	private String hourlylimitcomment;
	
	@Value("${daily.limit.block.comment}")
	private String dailylimitcomment;
	
	@Autowired
	LogInfoRepository logRepository;
	
	@Autowired
	BlockedIPRepository blockedIpRepository;

	@Override
	public List<String> getHourlyRequestedIps(String startdate, int threshold) {
		return logRepository.getRequestedHourlyIP(startdate, threshold);
	}

	@Override
	public List<String> getDailyRequestedIps(String startdate, int threshold) {
		return logRepository.getRequestedDailyIP(startdate, threshold);
	}

	@Override
	public void checkHourlyLimitMovetoBlockedList(String ip,String startdate) {
		List<Object[]> blockedList= logRepository.findHourlyRequestedIpWithLimitToBlock(ip, startdate, hourlyLimit);
		blockedList.stream().forEach(obj ->{			
			BlockedIP blockedip = new BlockedIP();
			blockedip.setStartdate((Date) obj[0]);
			blockedip.setIp((String)obj[1]);
			blockedip.setCount((int)obj[2]);
			blockedip.setComment(hourlylimitcomment);
			blockedIpRepository.save(blockedip);
		});
	}

	@Override
	public void checkDailyLimitMovetoBlockedList(String ip,String startdate) {
		List<Object[]> blockedList= logRepository.findDailyRequestedIpWithLimitToBlock(ip, startdate, hourlyLimit);
		
	}
	
	

}
