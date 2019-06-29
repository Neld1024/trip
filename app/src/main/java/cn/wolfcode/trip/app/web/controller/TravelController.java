package cn.wolfcode.trip.app.web.controller;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.TravelQueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 访问用户的接口
 */
@RestController
@RequestMapping("/travels")
public class TravelController {

    @Autowired
    private ITravelService service;

    @GetMapping
    public PageInfo list(HttpSession session, Model model, TravelQueryObject queryObject) {
        User user_in_session = (User) session.getAttribute("USER_IN_SESSION");

        queryObject.setAuthorId(user_in_session.getId());

        return service.query(queryObject);
    }
}
