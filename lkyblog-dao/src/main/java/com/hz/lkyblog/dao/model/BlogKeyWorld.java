package com.hz.lkyblog.dao.model;

import java.util.Date;
import lombok.Data;

@Data
public class BlogKeyWorld {
    private Long id;

    private String name;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModified;
}