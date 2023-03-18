package com.delores.medusa.controller;

import com.delores.medusa.model.User;
import com.delores.medusa.model.enums.StatusCode;
import com.delores.medusa.model.response.BaseResponse;
import com.delores.medusa.model.response.PageResponse;
import com.delores.medusa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author William
 * @date 4/16/21 6:40 PM
 * @description
 */
@RestController
@RequestMapping("/jpa")
public class JpaController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public BaseResponse<User> test1() {
//        User user = userService.getUser(2L);
//        BaseResponse<User> response;
//        if (user != null) {
//            return new BaseResponse<>(StatusCode.SUCCESS, user);
//        } else {
//            return new BaseResponse<>(StatusCode.FAIL);
//        }
        Optional<User> user = userService.getUser(2L);
        return user.map(value -> new BaseResponse<>(StatusCode.SUCCESS, value)).orElseGet(() -> new BaseResponse<>(StatusCode.FAIL));
    }

    @GetMapping("/page")
    public PageResponse<User> testPage() {
        // 页码从0开始
        Page<User> allWithPage = userService.findAllWithPage(PageRequest.of(0, 3));
//        allWithPage.map()
        PageResponse<User> response;
        if (allWithPage != null) {
            response = new PageResponse<>();
            response.setPageStats(allWithPage, true);
        } else {
            response = new PageResponse<>(StatusCode.FAIL);
        }
        return response;
    }
}
