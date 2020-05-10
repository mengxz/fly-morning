package com.bluesky.tech.spring.controller;

import com.bluesky.tech.spring.Service.IShopService;
import com.bluesky.tech.spring.handle.AbstractHandler;
import com.bluesky.tech.spring.handle.CommonHandler;
import com.bluesky.tech.spring.handle.TestHandler;
import com.bluesky.tech.spring.handle.enums.HandleEnum;
import com.bluesky.tech.spring.handle.vo.RequestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("api/handle")
@Slf4j
public class HandleTestController {
    @Autowired
    private IShopService shopService;

    @Autowired
    private TestHandler testHandle;

    @Autowired
    private CommonHandler commonHandle;

    @Resource
    private ApplicationContext applicationContext;

    @GetMapping(value = "/currentTimestamp")
    public long currentTimestamp() {
        log.info("调用getCurrentTimestamp接口");
        Date curr = new Date();
        return curr.getTime();
    }

    //http://localhost:8080/api/handle/10
    @GetMapping(value = "/{userId}")
    public String handle(@PathVariable int userId) {
        log.info("handle userId:{}",userId);
        String handleName = HandleEnum.fromCode(userId).getReceiveHandler();
        AbstractHandler handler = (AbstractHandler) applicationContext.getBean(handleName);
        RequestVo vo = new RequestVo(userId,"name_"+userId,10);
        handler.handle(vo);
        return "name_"+userId;
    }
}
