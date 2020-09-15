package com.ruoyi.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 招标文章对象 bus_bid
 *
 * @author sword
 * @date 2020-08-08
 */
public class BusBid extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 招标id
     */
    private Long bidId;

    /**
     * 招标类型
     */
    @Excel(name = "招标类型")
    private String bidType;

    /**
     * 招标标题
     */
    @Excel(name = "招标标题")
    private String bidTitle;

    /**
     * 招标摘要
     */
    @Excel(name = "招标摘要")
    private String bidSummary;

    /**
     * 招标内容
     */
    @Excel(name = "招标内容")
    private String bidContent;

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidType(String bidType) {
        this.bidType = bidType;
    }

    public String getBidType() {
        return bidType;
    }

    public void setBidTitle(String bidTitle) {
        this.bidTitle = bidTitle;
    }

    public String getBidTitle() {
        return bidTitle;
    }

    public void setBidSummary(String bidSummary) {
        this.bidSummary = bidSummary;
    }

    public String getBidSummary() {
        return bidSummary;
    }

    public void setBidContent(String bidContent) {
        this.bidContent = bidContent;
    }

    public String getBidContent() {
        return bidContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("bidId", getBidId())
                .append("bidType", getBidType())
                .append("bidTitle", getBidTitle())
                .append("bidSummary", getBidSummary())
                .append("bidContent", getBidContent())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
