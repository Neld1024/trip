package cn.wolfcode.trip.base.mapper;

import cn.wolfcode.trip.base.domain.TravelContent;

public interface TravelContentMapper {
    int insert(TravelContent record);

    TravelContent selectByPrimaryKey(Long id);

    int updateByPrimaryKey(TravelContent record);
}