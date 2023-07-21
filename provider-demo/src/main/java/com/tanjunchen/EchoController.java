package com.tanjunchen;

import com.tanjunchen.util.IPUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    @RequestMapping(value = "/echo/{message}", method = RequestMethod.GET)
    public String echo(@PathVariable String message) {
        if (message.contains("timeout")) {
            try {
                Thread.sleep(240000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (message.contains("exception")) {
            int a = 1 / 0;
        }
        return "echo() -> ip [ " + IPUtil.getIp() + " ] param [ " + message + " ] ";
    }
}
