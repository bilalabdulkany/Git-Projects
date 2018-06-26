package com.ef.parser.data.file.threads;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ef.parser.data.conf.Configuration;
import com.ef.parser.data.entity.LogData;
import com.ef.parser.data.repository.LogInfoRepository;


public class LogReader implements Runnable {

	private	LogInfoRepository loginforepository;

	private String path;

	@Override
	public void run() {
		BufferedReader br = null;
		String line = null;
		String rowline[] = new String[4];
		LogData logdto = null;
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		//SimpleDateFormat logformat=new SimpleDateFormat("yyyy-MM-dd.HH:mm:ss.SSS");
		try {
			Configuration.IS_READER_RUNNING = true;
			br = new BufferedReader(new FileReader(this.path));

			while ((line = br.readLine()) != null) {
				rowline = line.split("\\|");
				logdto = new LogData();
				Date newTimestamp =format.parse(rowline[0]);
				logdto.setStartDate(new Timestamp(newTimestamp.getTime()));
				logdto.setIp(rowline[1]);
				logdto.setHttprequest(rowline[2]);
				logdto.setHttpcode(Integer.parseInt(rowline[3]));
				logdto.setUseragent(rowline[4]);
				System.out.println("Saving: " + rowline);
				loginforepository.save(logdto);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null)
				try {
					Configuration.IS_READER_RUNNING = false;
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
	}

	public LogReader(String path,LogInfoRepository loginforepository) {
		this.path = path;
		this.loginforepository=loginforepository;
	}
	
		
}
