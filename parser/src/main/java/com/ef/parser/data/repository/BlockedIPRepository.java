package com.ef.parser.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ef.parser.data.entity.BlockedIP;

@Repository
public interface BlockedIPRepository extends JpaRepository<BlockedIP, String> {

}
