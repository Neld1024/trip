package cn.wolfcode.trip.app.util;

import lombok.Getter;
import lombok.Setter;

/**
 * 封装响应的结果数据
 */

@Getter
@Setter
public class JsonResult {
    private boolean success = true;

    private String errorMsg;

    private Object obj;
}
