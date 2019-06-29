package cn.wolfcode.trip.admin.web.controller;

import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 供系统内部员工来访问
 */
@Controller
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    private ITravelService service;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo")QueryObject qo){

        model.addAttribute("pageInfo",service.query(qo));

        return "travel/list";
    }
}
