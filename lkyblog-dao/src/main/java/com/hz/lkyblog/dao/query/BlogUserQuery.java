package com.hz.lkyblog.dao.query;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BlogUserQuery {
    private Long id;

    private String name;

    private String password;

    private String email;

    private Integer roleId;

    private String mobile;

    private Integer status;

    private Integer pageIndex;

    private Integer pageSize;

    private String orderByStr;
}