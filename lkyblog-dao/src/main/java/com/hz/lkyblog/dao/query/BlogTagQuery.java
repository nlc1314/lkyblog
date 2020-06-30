package com.hz.lkyblog.dao.query;

import lombok.Data;

@Data
public class BlogTagQuery {
    private Long id;

    private String name;

    private Integer status;

    private Integer isShow;

    private Integer pageIndex;

    private Integer pageSize;

    private String orderByStr;
}