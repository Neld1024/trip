package cn.wolfcode.trip.admin.web.controller;

import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 供系统内部员工来访问
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService service;

    //SpringMVC会自动将封装到对象中的数据进行共享
    //共享数据的名称默认为类型的首字母小写:queryObject
    //可以在参数前使用@ModelAttribute()修改共享数据的名称

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")QueryObject qo){

        model.addAttribute("pageInfo",service.query(qo));

        return "user/list";
    }
/*
    @RequestMapping("/list")
    public String list(Model model){

        List<User> users = service.listAll();
        //将查询到的数据共享给页面
        model.addAttribute("list", users);

        return "user/list";
    }
*/
}
