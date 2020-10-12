package com.ef.parser.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ef.parser.data.entity.LogData;

@Repository
public interface LogInfoRepository extends JpaRepository<LogData, Long> {

	@Query(value = "select ip from logdata where startdate >=?1 and startdate < date_add(?1,interval 1 hour)  group by ip having count(ip) > ?2", nativeQuery = true)
	List<String> getRequestedHourlyIP(String starttime, int limit);

	@Query(value = "select ip from logdata where startdate >=?1 and startdate < date_add(?1,interval 1 day)  group by ip having count(ip) > ?2", nativeQuery = true)
	List<String> getRequestedDailyIP(String starttime, int limit);
	
	@Query(value="SELECT startdate,IP,count(ip) as count FROM LOGDATA " + 
			"WHERE IP=?1 AND STARTDATE >=?2 AND STARTDATE < date_add(?2,interval 1 hour) " + 
			"GROUP BY IP " + 
			"HAVING COUNT(IP) > ?3", nativeQuery = true)
	List<Object[]> findHourlyRequestedIpWithLimitToBlock(String ip,String startdate,int limit);
	
	@Query(value="SELECT startdate,IP,count(ip) as count FROM LOGDATA " + 
			"WHERE IP =?1 AND STARTDATE >=?2 AND STARTDATE < date_add(?2,interval 1 day) " + 
			"GROUP BY IP " + 
			"HAVING COUNT(IP) > ?3", nativeQuery = true)
	List<Object[]> findDailyRequestedIpWithLimitToBlock(String ip,String startdate,int limit);
	
	
}
