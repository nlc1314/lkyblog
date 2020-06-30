package com.hz.lkyblog.dao.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class BlogArticleWithBLOBs extends BlogArticle {
    private String context;
}