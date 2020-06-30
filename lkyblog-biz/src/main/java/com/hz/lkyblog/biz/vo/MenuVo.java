package com.hz.lkyblog.biz.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MenuVo {
    private Long id;
    private Long parentId;
    private String name;
    private Integer level;
    private String target;
    private List<MenuVo> childList;
}
