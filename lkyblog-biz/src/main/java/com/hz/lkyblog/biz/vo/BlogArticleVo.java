package com.hz.lkyblog.biz.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BlogArticleVo {
    private Long id;
    private String name;
    private String imageUrl;
    private String publishTime;
    private String context;
    private List<String> keyWorlds;//关键字
    private String tag;//标签
    private Long tagId;//标签Id
    private Boolean isShow;//是否上架
    private Boolean isRecommend;//是否推荐
    private Integer type;//是否推荐
}
