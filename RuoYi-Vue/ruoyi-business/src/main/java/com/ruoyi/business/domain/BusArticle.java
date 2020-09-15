package com.ruoyi.business.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文章对象 bus_article
 *
 * @author swrod
 * @date 2020-08-05
 */
public class BusArticle extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 文章类型
     */
    @Excel(name = "文章类型")
    private String articleType;

    /**
     * 文章标题
     */
    @Excel(name = "文章标题")
    private String articleTitle;

    /**
     * 文章摘要
     */
    @Excel(name = "文章摘要")
    private String articleSummary;

    /**
     * 文章内容
     */
    private String articleContent;

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleContent() {
        return articleContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("articleId", getArticleId())
                .append("articleType", getArticleType())
                .append("articleTitle", getArticleTitle())
                .append("articleSummary", getArticleSummary())
                .append("articleContent", getArticleContent())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
