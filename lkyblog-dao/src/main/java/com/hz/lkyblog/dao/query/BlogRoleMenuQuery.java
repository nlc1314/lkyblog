package com.hz.lkyblog.dao.query;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BlogRoleMenuQuery {
    private Long id;

    private Long roleId;

    private String menuIds;

    private Integer status;

    private Integer pageIndex;

    private Integer pageSize;

    private String orderByStr;
}