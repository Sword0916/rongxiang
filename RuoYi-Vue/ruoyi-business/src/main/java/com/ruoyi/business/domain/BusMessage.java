package com.ruoyi.business.domain;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 留言对象 bus_message
 *
 * @author sword
 * @date 2020-08-06
 */
public class BusMessage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 留言id
     */
    private Long messageId;

    /**
     * 文章id
     */
    @Excel(name = "文章id")
    private Long bidId;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String messageName;

    /**
     * 手机
     */
    @Excel(name = "手机")
    private String messagePhone;

    /**
     * 报价
     */
    @Excel(name = "报价")
    private BigDecimal messagePrice;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private String messageAddress;

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessagePhone(String messagePhone) {
        this.messagePhone = messagePhone;
    }

    public String getMessagePhone() {
        return messagePhone;
    }

    public void setMessagePrice(BigDecimal messagePrice) {
        this.messagePrice = messagePrice;
    }

    public BigDecimal getMessagePrice() {
        return messagePrice;
    }

    public void setMessageAddress(String messageAddress) {
        this.messageAddress = messageAddress;
    }

    public String getMessageAddress() {
        return messageAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("messageId", getMessageId())
                .append("bidId", getBidId())
                .append("messageName", getMessageName())
                .append("messagePhone", getMessagePhone())
                .append("messagePrice", getMessagePrice())
                .append("messageAddress", getMessageAddress())
                .append("remark", getRemark())
                .append("createTime", getCreateTime())
                .toString();
    }
}
