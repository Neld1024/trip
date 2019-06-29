package cn.wolfcode.trip.base;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)//将测试类也交给Spring容器管理
@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceImplTest {

    @Autowired
    private IUserService service;


    @Test
    public void saveOrUpdate() throws Exception {
//        User u = new User(null, "xxxx", "xxxx", "xxxx", "广州", null, 1, null, "嘻嘻嘻");
//        service.saveOrUpdate(u);
    }

    @Test
    public void delete() throws Exception {
        service.delete(46L);
    }

    @Test
    public void get() throws Exception {
        User user = service.get(45L);
        System.out.println(user);
    }

    @Test
    public void listAll() throws Exception {
        List<User> users = service.listAll();
        System.out.println(users);
    }

}