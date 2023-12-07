package com.example.basic.database.dao;

import org.springframework.stereotype.Service;

@Service
public class BasicDao {
    // throws Exception : 던져줘야 catch를 한다.
    public String test() throws Exception {
        return "[BasicDao][test] 정상";
    }
    
    // throws Exception : 던져줘야 catch를 한다.
    public String testException() throws Exception{
        throw new Exception("[BasicDao][testException] 오류");
    }
}
