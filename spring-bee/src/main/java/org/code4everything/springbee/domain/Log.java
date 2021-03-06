package org.code4everything.springbee.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.code4everything.boot.base.bean.BaseDomain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author pantao
 * @since 2018/9/10
 */
@Data
@ToString
@Document
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "日志")
public class Log implements BaseDomain {

    private static final long serialVersionUID = -9056492805237354620L;

    @Id
    @ApiModelProperty("日志编号")
    private String id;

    @ApiModelProperty("用户编号")
    private String userId;

    @ApiModelProperty("日志描述")
    private String description;

    @ApiModelProperty("远程IP")
    private String ip;

    @ApiModelProperty("类名")
    private String className;

    @ApiModelProperty("方法名")
    private String methodName;

    @ApiModelProperty("生成时间")
    private Long createTime;

    @ApiModelProperty("参数集合")
    private String args;

    @ApiModelProperty("异常类")
    private String exceptionClass;

    @ApiModelProperty("异常详情")
    private String exceptionDetail;

    @ApiModelProperty("执行时长")
    private Long executedTime;

    @Override
    public Serializable primaryKey() {
        return id;
    }
}
