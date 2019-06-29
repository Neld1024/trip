package cn.wolfcode.trip.app.web.controller;

import cn.wolfcode.trip.app.util.JsonResult;
import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 访问用户的接口
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping
    public JsonResult register(User user){
        JsonResult result = new JsonResult();

        try {
            service.saveOrUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setErrorMsg(e.getMessage());
        }
        return result;
    }

    @PutMapping("/{id}")
    public JsonResult update(User user){
        JsonResult result = new JsonResult();
        service.saveOrUpdate(user);
        result.setObj(user);
        return result;
    }
}
