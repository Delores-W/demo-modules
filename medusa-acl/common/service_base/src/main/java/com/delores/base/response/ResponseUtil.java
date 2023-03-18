package com.delores.base.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author William
 * @date 5/13/21 2:00 PM
 * @description
 */
public class ResponseUtil {

    public static <T> void out(HttpServletResponse response, BaseResponse<T> baseResponse) {
        // response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(new ObjectMapper().writeValueAsString(baseResponse));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
