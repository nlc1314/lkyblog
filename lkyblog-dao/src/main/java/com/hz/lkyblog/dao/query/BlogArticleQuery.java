package com.hz.lkyblog.dao.query;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BlogArticleQuery {
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

    private Integer pageIndex;

    private Integer pageSize;

    private String orderByStr;
}