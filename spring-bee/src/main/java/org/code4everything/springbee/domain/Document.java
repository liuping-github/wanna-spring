package org.code4everything.springbee.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author pantao
 * @since 2018/9/13
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "document", description = "文件")
public class Document implements Serializable {

    @Id
    @ApiModelProperty("文件编号")
    private String id;

    @ApiModelProperty("文件本地路径（敏感数据）")
    private String localPath;

    @ApiModelProperty("访问链接")
    private String accessUrl;

    @ApiModelProperty("文件后缀")
    private String suffix;

    @ApiModelProperty("上传时间")
    private Timestamp createTime;

    @ApiModelProperty("文件大小")
    private Long size;
}