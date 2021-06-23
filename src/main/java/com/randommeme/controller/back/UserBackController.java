package com.randommeme.controller.back;

import com.randommeme.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户表  控制器
 *
 * @author lsivt
 * @date 2021-06-23 10:41:53
 */
@Slf4j
@RestController
@RequestMapping("/admin/user")
public class UserBackController {

    @Autowired
    private IUserService userService;
}
