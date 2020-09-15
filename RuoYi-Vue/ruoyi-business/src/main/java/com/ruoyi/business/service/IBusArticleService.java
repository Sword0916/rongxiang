package com.ruoyi.business.service;

import java.util.List;

import com.ruoyi.business.domain.BusArticle;

/**
 * 文章Service接口
 *
 * @author swrod
 * @date 2020-08-05
 */
public interface IBusArticleService {
    /**
     * 查询文章
     *
     * @param articleId 文章ID
     * @return 文章
     */
    public BusArticle selectBusArticleById(Long articleId);

    /**
     * 查询文章列表
     *
     * @param busArticle 文章
     * @return 文章集合
     */
    public List<BusArticle> selectBusArticleList(BusArticle busArticle);

    /**
     * 新增文章
     *
     * @param busArticle 文章
     * @return 结果
     */
    public int insertBusArticle(BusArticle busArticle);

    /**
     * 修改文章
     *
     * @param busArticle 文章
     * @return 结果
     */
    public int updateBusArticle(BusArticle busArticle);

    /**
     * 批量删除文章
     *
     * @param articleIds 需要删除的文章ID
     * @return 结果
     */
    public int deleteBusArticleByIds(Long[] articleIds);

    /**
     * 删除文章信息
     *
     * @param articleId 文章ID
     * @return 结果
     */
    public int deleteBusArticleById(Long articleId);
}
