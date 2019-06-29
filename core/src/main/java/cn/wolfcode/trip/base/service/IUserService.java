package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 用户业务接口
 */
public interface IUserService {

    void saveOrUpdate(User user);

    void delete(Long id);

    User get(Long id);

    List<User> listAll();

    PageInfo query(QueryObject qo);

    User login(String email, String password);
}
