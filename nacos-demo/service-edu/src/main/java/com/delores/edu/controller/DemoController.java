package com.delores.edu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author William
 * @date 5/19/21 10:08 AM
 * @description
 */
@RestController
@RequestMapping("/edu")
public class DemoController {

    @GetMapping("test")
    public String test() {
        return "test edu";
    }
}
