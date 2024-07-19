package com.wang.log.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface InventoryLogMapper {

    @Insert("INSERT INTO demo.log_message_47009 (message,created,request_id) VALUES (#{logMessage},#{time},#{traceID}) ")
    void insertLog(String logMessage, LocalDateTime time,String traceID);
}
