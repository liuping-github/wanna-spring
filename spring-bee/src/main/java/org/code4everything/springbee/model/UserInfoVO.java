package org.code4everything.springbee.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.code4everything.boot.base.bean.BaseBean;

import java.io.Serializable;

/**
 * @author pantao
 * @since 2018/9/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "用户信息")
public class UserInfoVO implements BaseBean, Serializable {

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("个人简介")
    private String bio;

    @ApiModelProperty("性别")
    private String gender;
}
