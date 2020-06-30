package com.hz.lkyblog.biz.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageVo<T> {
    private List<T> list;
    private Long count;
}
