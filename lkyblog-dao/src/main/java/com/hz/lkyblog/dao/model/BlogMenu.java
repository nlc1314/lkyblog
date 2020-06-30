package com.hz.lkyblog.dao.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BlogMenu {
    private Long id;

    private String name;

    private Long parentId;

    private Integer hierarchy;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModified;

    private String target;
}