package com.hz.lkyblog.dao.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BlogUser {
    private Long id;

    private String name;

    private String password;

    private String email;

    private Integer roleId;

    private String mobile;

    private Integer status;
}