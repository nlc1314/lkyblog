package com.hz.lkyblog.dao.es.eo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


@Data
@Document(indexName = "article", type = "java")
public class ArticleEO implements Serializable {

    private static final long serialVersionUID = 6320548148250372657L;

    @Id
    private Long id;

    private String name;

    private String desc;

    private String imageUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date publishTime;

    private Integer isShow;

    private Long tagId;

    private Integer isRecommend;

    private Integer type;

    private String context;
}
