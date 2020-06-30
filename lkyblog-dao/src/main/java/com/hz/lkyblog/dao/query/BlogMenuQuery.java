package com.hz.lkyblog.dao.query;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class BlogMenuQuery {
    private Long id;

    private String name;

    private Long parentId;

    private Integer hierarchy;

    private Integer status;

    private String target;

    private Integer pageIndex;

    private Integer pageSize;

    private String orderByStr;
}