package com.hz.lkyblog.dao.query;

import lombok.Data;

@Data
public class BlogLinkQuery {
    private Integer id;

    private String name;

    private String linkUrl;

    private Integer status;

    private Integer pageIndex;

    private Integer pageSize;

    private String orderByStr;
}