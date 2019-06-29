package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.mapper.UserMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper mapper;

    public void saveOrUpdate(User user) {
        //如果页面没有传递id值,说明是保存,反之是更新
        if (user.getId() != null) {
            mapper.updateByPrimaryKey(user);
        } else {
            //校验邮箱是否存在
            //根据邮箱到数据库中查询数据
            //如果返回的数据不为null,说明邮箱存在,反之不存在
            User u = mapper.selectByEmail(user.getEmail());
            if(u != null){
                throw new RuntimeException("亲,该邮箱已存在");
            }
            mapper.insert(user);
        }
    }

    public void delete(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    public User get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<User> listAll() {
        return mapper.selectAll();
    }

    public PageInfo query(QueryObject qo) {
        //设置开始分页
        //该代码后面的第一个查询会使用分页查询
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());

        //调用mapper中的方法执行分页查询
        List<User> users = mapper.selectForList(qo);
        return new PageInfo(users);
    }

    public User login(String email, String password) {
        User u = mapper.selectByEmailAndPassword(email,password);
        if(u == null){
            throw new RuntimeException("亲,账号或者密码错误");
        }

        return u;
    }
}
