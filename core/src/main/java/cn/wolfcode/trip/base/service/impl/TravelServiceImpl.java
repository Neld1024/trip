package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Travel;
import cn.wolfcode.trip.base.mapper.TravelMapper;
import cn.wolfcode.trip.base.query.QueryObject;
import cn.wolfcode.trip.base.service.ITravelService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelServiceImpl implements ITravelService {

    @Autowired
    private TravelMapper mapper;

    public void saveOrUpdate(Travel travel) {
        //如果页面没有传递id值,说明是保存,反之是更新
        if (travel.getId() != null) {
            mapper.updateByPrimaryKey(travel);
        } else {
            mapper.insert(travel);
        }
    }

    public Travel get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<Travel> listAll() {
        return mapper.selectAll();
    }

    public PageInfo query(QueryObject qo) {
        //设置开始分页
        //该代码后面的第一个查询会使用分页查询
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());

        //调用mapper中的方法执行分页查询
        List<Travel> travels = mapper.selectForList(qo);
        return new PageInfo(travels);
    }
}
