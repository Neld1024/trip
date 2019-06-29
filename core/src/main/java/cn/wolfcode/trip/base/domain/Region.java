package cn.wolfcode.trip.base.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Region {
    private Long id;

    private String name;

    private Region parent;

    //0:默认   1:推荐
    private Integer state = 0;


}