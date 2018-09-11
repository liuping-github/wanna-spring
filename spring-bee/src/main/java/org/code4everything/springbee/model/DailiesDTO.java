package org.code4everything.springbee.model;

import com.zhazhapan.util.annotation.FieldChecking;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

/**
 * @author pantao
 * @since 2018/9/11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "dailies", description = "日程详情")
public class DailiesDTO {

    @FieldChecking
    @ApiModelProperty("开始时间")
    private Time startTime;

    @FieldChecking
    @ApiModelProperty("结束时间")
    private Time endTime;

    @FieldChecking
    @ApiModelProperty("详情内容")
    private String content;
}