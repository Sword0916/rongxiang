package com.ruoyi.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 建筑篇章对象 bus_construct
 *
 * @author Sword
 * @date 2020-08-21
 */
public class BusConstruct extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 篇章id
     */
    private Long conId;

    /**
     * 篇章类型
     */
    @Excel(name = "篇章类型")
    private String conType;

    /**
     * 篇章标题
     */
    @Excel(name = "篇章标题")
    private String conTitle;

    /**
     * 篇章摘要
     */
    @Excel(name = "篇章摘要")
    private String conSummary;

    /**
     * 篇章内容
     */
    private String conContent;

    public void setConId(Long conId) {
        this.conId = conId;
    }

    public Long getConId() {
        return conId;
    }

    public void setConType(String conType) {
        this.conType = conType;
    }

    public String getConType() {
        return conType;
    }

    public void setConTitle(String conTitle) {
        this.conTitle = conTitle;
    }

    public String getConTitle() {
        return conTitle;
    }

    public void setConSummary(String conSummary) {
        this.conSummary = conSummary;
    }

    public String getConSummary() {
        return conSummary;
    }

    public void setConContent(String conContent) {
        this.conContent = conContent;
    }

    public String getConContent() {
        return conContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("conId", getConId())
                .append("conType", getConType())
                .append("conTitle", getConTitle())
                .append("conSummary", getConSummary())
                .append("conContent", getConContent())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
