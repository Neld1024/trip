package cn.wolfcode.trip.base.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 封装页面上传递过来的参数
 */
@Getter@Setter
public class QueryObject {
    private int currentPage = 1;
    private int pageSize = 5;

    private String keyword;
}
