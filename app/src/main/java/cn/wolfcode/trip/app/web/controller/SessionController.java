package cn.wolfcode.trip.app.web.controller;

import cn.wolfcode.trip.app.util.JsonResult;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private IUserService service;

    @GetMapping
    public JsonResult login(String email, String password, HttpSession session){
        JsonResult result = new JsonResult();

        try {
            User user = service.login(email, password);
            //封装用户信息
            result.setObj(user);

            //将用户信息共享到session作用域中
            session.setAttribute("USER_IN_SESSION", user);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setErrorMsg(e.getMessage());
        }


        return result;
    }
}
