package com.ef;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.ef.parser.data.validators.Validator;
import com.ef.parser.service.LoggerFileService;
import com.ef.parser.service.LoggerService;

@Configuration
public class ParserConfig {
	
	@Autowired
	private Environment environment;

	@Autowired
	LoggerService loggerservice;

	@Autowired
	LoggerFileService fileservice;

	public ParserConfig() {
		super();
	}

	public void configureParameters() {
		String threshold = environment.getProperty("threshold");
		String date = environment.getProperty("startDate");
		String duration = environment.getProperty("duration");
		String accesslog = environment.getProperty("accesslog");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat inputformat = new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss");
		Date logdate = null;
		try {
			logdate = inputformat.parse(date);
			date = format.format(logdate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (Validator.thresholdValidator(threshold) && Validator.dateValidator(date)
				&& Validator.durationValidator(duration)) {
			int thresholdInt = Integer.parseInt(threshold);
			if (!accesslog.isEmpty()) {
				try {
					fileservice.readLogFile(accesslog);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (duration.equals("daily")) {
				System.out.println(loggerservice.getDailyRequestedIps(date, thresholdInt));
			} else {
				System.out.println(loggerservice.getHourlyRequestedIps(date, thresholdInt));
			}
		}
	}

}
