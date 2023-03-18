package com.delores.medusa.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author William
 * @date 5/4/21 3:12 PM
 * @description
 */
@Service
public class CommonService {

//    @Autowired
//    private LogMapper logMapper;

    public void print(HttpServletResponse response, Object message){
        try {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            PrintWriter writer = response.getWriter();
            writer.write(JSONObject.toJSONString(message));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Async("taskExecutor") -- 记录日志到数据库
//    public void insertLog(Log log){
//        try {
//            logMapper.insertSelective(log);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
