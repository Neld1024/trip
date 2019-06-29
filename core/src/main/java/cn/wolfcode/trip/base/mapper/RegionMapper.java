package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.Region;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionMapper {

    int insert(Region record);

    Region selectByPrimaryKey(Long id);

    List<Region> selectAll();

    int updateByPrimaryKey(Region record);

    List<Region> selectRegionsByParentId(@Param("parentId") Long parentId);

    void updateState(@Param("id") Long id, @Param("state") int state);
}