package com.ef.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.stream.Stream;

import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ef.Parser;
import com.ef.parser.service.LoggerService;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@ContextConfiguration(classes = {Parser.class})
public class LogDataTest {

	@Autowired
	LoggerService service;
	
	@Test
	public void testDataHourlyLimit() {		
		List<String> ips =service.getHourlyRequestedIps("2017-01-01 00:00:00.000",100);
		Stream.of(ips).distinct().forEach(System.out::println);
		assertNotNull(ips);
	}
	
	@Test
	public void testBlockedIpHourlyLimit() {
		service.checkHourlyLimitMovetoBlockedList("192.168.169.194", "2017-01-01 00:00:00");
	}
	
}
