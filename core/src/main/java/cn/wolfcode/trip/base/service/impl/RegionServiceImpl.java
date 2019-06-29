package cn.wolfcode.trip.base.service.impl;

import cn.wolfcode.trip.base.domain.Region;
import cn.wolfcode.trip.base.mapper.RegionMapper;
import cn.wolfcode.trip.base.service.IRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements IRegionService {

    @Autowired
    private RegionMapper mapper;

    public void saveOrUpdate(Region region) {
        if (region.getId() != null) {
            mapper.updateByPrimaryKey(region);
        } else {
            mapper.insert(region);
        }
    }


    public Region get(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<Region> listAll() {
        return mapper.selectAll();
    }

    public List<Region> getRegionsMapByParentId(Long parentId) {
        return mapper.selectRegionsByParentId(parentId);
    }

    public void updateState(Long id, int state) {
        mapper.updateState(id,state);
    }

}
