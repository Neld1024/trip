package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.query.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 用户业务接口
 */
public interface ITravelService {

    void saveOrUpdate(Travel travel);

    Travel get(Long id);

    List<Travel> listAll();

    PageInfo query(QueryObject qo);

}
