package com.hz.lkyblog.dao.model;

import java.util.Date;
import lombok.Data;

@Data
public class BlogLink {
    private Integer id;

    private String name;

    private String linkUrl;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModified;
}