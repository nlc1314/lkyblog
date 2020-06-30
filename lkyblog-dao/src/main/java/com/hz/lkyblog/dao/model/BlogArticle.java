package com.hz.lkyblog.dao.model;

import java.util.Date;
import lombok.Data;

@Data
public class BlogArticle {
    private Long id;

    private String name;

    private String desc;

    private String imageUrl;

    private Date createTime;

    private Date publishTime;

    private Integer isShow;

    private Long tagId;

    private Integer isRecommend;

    private Integer type;
}