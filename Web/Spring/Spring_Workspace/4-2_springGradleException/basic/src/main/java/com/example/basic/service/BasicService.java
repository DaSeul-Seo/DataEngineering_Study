package com.example.basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.basic.database.dao.BasicDao;

@Service
public class BasicService {
    @Autowired
    private BasicDao basicDao;

    // throws Exception : 던져줘야 catch를 한다.
    // 정상 테스트
    public String test() throws Exception {
        return basicDao.test();
    }
    
    // throws Exception : 던져줘야 catch를 한다.
    // DAO 오류 테스트
    public String daoException() throws Exception{
        return basicDao.testException();
    }

    // Service 오류 테스트
    public String testException() throws Exception{
        throw new Exception("[BasicService][testException] 오류");
    }
}
