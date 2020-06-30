package com.hz.lkyblog.biz.vo;

import com.hz.lkyblog.dao.model.BlogUser;
import com.hz.lkyblog.utils.enums.AdminStatusEnum;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminVo implements Serializable {
    private Long id;
    private String name;
    private Integer roleId;
    private String mobile;
    private String email;
    private Integer status;
    private String statusName;

    public AdminVo(BlogUser blogUser) {
        this.id = blogUser.getId();
        this.name = blogUser.getName();
        this.mobile = blogUser.getMobile();
        this.email = blogUser.getEmail();
        this.roleId = blogUser.getRoleId();
        this.status = blogUser.getStatus();
        this.statusName = AdminStatusEnum.getMsg(blogUser.getStatus());
    }
}
