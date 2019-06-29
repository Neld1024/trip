package cn.wolfcode.trip.base.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class Travel {
    private Long id;

    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date travelTime;

    private String perExpends;

    private Integer days;

    private Integer person;

    private User author;

    private Date createTime;

    private Date releaseTime;

    private Boolean isPublic;

    private Region place;

    private String coverUrl;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date lastUpdateTime;

    //1:待审核 0:已拒绝  2:已发布
    private Integer state = 1;


}