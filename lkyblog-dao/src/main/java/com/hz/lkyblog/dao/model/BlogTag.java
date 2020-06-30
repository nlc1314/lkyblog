package com.hz.lkyblog.dao.model;

import java.util.Date;
import lombok.Data;

@Data
public class BlogTag {
    private Long id;

    private String name;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer status;

    private Integer isShow;
}