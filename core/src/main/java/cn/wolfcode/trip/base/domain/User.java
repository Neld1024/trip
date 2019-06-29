package cn.wolfcode.trip.base.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //Field
    private Long id;

    private String email;

    private String nickName;

    private String password;

    private String place;

    private String headImgUrl;

    private Integer gender = 0;

    private String coverImgUrl;

    private String sign;

    //Property
    public String getGenderName() {//属性名是由get或者set方法决定
        if (gender == 0) {
            return "女";
        }else if(gender == 1){
            return "男";
        }else{
            return "未知";
        }
    }


}