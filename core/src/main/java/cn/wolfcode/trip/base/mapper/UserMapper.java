package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.User;
import cn.wolfcode.trip.base.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    /**
     * 分页查询
     * @return
     */
    List<User> selectForList(QueryObject qo);

    User selectByEmail(String email);

    User selectByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}