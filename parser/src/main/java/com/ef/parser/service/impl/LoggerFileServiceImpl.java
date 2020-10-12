package com.ef.parser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ef.parser.data.conf.Configuration;
import com.ef.parser.data.file.threads.LogReader;
import com.ef.parser.data.repository.LogInfoRepository;
import com.ef.parser.service.LoggerFileService;

@Service
public class LoggerFileServiceImpl implements LoggerFileService {

	@Autowired
	LogInfoRepository loginforepository;
	@Override
	public void readLogFile(String path) throws Exception {

		LogReader reader = new LogReader(path,loginforepository);
		try {
			reader.run();
			
			while(Configuration.IS_READER_RUNNING) {
				System.out.println("Still reading....");
			}
			
			System.out.println("done reading file");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
