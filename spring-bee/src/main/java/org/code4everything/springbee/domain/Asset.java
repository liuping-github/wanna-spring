package org.code4everything.springbee.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.code4everything.boot.base.bean.BaseDomain;
import org.code4everything.boot.base.encoder.Sealed;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author pantao
 * @since 2018/9/9
 */
@Data
@ToString
@Document
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "资产（可流动资金）")
public class Asset implements BaseDomain {

    private static final long serialVersionUID = 5328542047062705414L;

    @Id
    @ApiModelProperty("资产编号")
    private String id;

    @Sealed
    @ApiModelProperty("用户编号")
    private String userId;

    @ApiModelProperty("可用余额，含小数")
    private Long balance;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @Override
    public Serializable primaryKey() {
        return id;
    }
}
