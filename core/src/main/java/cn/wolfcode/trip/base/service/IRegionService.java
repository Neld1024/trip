package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Region;

import java.util.List;

public interface IRegionService {

    void saveOrUpdate(Region region);

    Region get(Long id);

    List<Region> listAll();

    List<Region> getRegionsMapByParentId(Long parentId);

    void updateState(Long id, int state);
}
