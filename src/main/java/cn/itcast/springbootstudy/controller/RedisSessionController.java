package cn.itcast.springbootstudy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class RedisSessionController {/*127.0.0.1与localhost都指的是本机，但其Session是不相同的*/

    @RequestMapping(value = "/uid",method = RequestMethod.GET)
    public @ResponseBody String uid(HttpSession session){
        UUID uid =(UUID) session.getAttribute("uid");
        if (uid==null){
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid",uid);
        return session.getId();
    }
}
