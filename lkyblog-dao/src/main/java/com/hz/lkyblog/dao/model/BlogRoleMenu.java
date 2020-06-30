package com.hz.lkyblog.dao.model;

import java.util.Date;
import lombok.Data;

@Data
public class BlogRoleMenu {
    private Long id;

    private Long roleId;

    private String menuIds;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer status;
}