package com.ruoyi.business.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BusArticleMapper;
import com.ruoyi.business.domain.BusArticle;
import com.ruoyi.business.service.IBusArticleService;

/**
 * 文章Service业务层处理
 *
 * @author swrod
 * @date 2020-08-05
 */
@Service
public class BusArticleServiceImpl implements IBusArticleService {
    @Autowired
    private BusArticleMapper busArticleMapper;

    /**
     * 查询文章
     *
     * @param articleId 文章ID
     * @return 文章
     */
    @Override
    public BusArticle selectBusArticleById(Long articleId) {
        return busArticleMapper.selectBusArticleById(articleId);
    }

    /**
     * 查询文章列表
     *
     * @param busArticle 文章
     * @return 文章
     */
    @Override
    public List<BusArticle> selectBusArticleList(BusArticle busArticle) {
        return busArticleMapper.selectBusArticleList(busArticle);
    }

    /**
     * 新增文章
     *
     * @param busArticle 文章
     * @return 结果
     */
    @Override
    public int insertBusArticle(BusArticle busArticle) {
        busArticle.setCreateTime(DateUtils.getNowDate());
        return busArticleMapper.insertBusArticle(busArticle);
    }

    /**
     * 修改文章
     *
     * @param busArticle 文章
     * @return 结果
     */
    @Override
    public int updateBusArticle(BusArticle busArticle) {
        busArticle.setUpdateTime(DateUtils.getNowDate());
        return busArticleMapper.updateBusArticle(busArticle);
    }

    /**
     * 批量删除文章
     *
     * @param articleIds 需要删除的文章ID
     * @return 结果
     */
    @Override
    public int deleteBusArticleByIds(Long[] articleIds) {
        return busArticleMapper.deleteBusArticleByIds(articleIds);
    }

    /**
     * 删除文章信息
     *
     * @param articleId 文章ID
     * @return 结果
     */
    @Override
    public int deleteBusArticleById(Long articleId) {
        return busArticleMapper.deleteBusArticleById(articleId);
    }
}
