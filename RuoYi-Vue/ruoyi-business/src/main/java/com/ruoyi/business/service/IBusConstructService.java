package com.ruoyi.business.service;

import java.util.List;

import com.ruoyi.business.domain.BusConstruct;

/**
 * 建筑篇章Service接口
 *
 * @author Sword
 * @date 2020-08-21
 */
public interface IBusConstructService {
    /**
     * 查询建筑篇章
     *
     * @param conId 建筑篇章ID
     * @return 建筑篇章
     */
    public BusConstruct selectBusConstructById(Long conId);

    /**
     * 查询建筑篇章列表
     *
     * @param busConstruct 建筑篇章
     * @return 建筑篇章集合
     */
    public List<BusConstruct> selectBusConstructList(BusConstruct busConstruct);

    /**
     * 新增建筑篇章
     *
     * @param busConstruct 建筑篇章
     * @return 结果
     */
    public int insertBusConstruct(BusConstruct busConstruct);

    /**
     * 修改建筑篇章
     *
     * @param busConstruct 建筑篇章
     * @return 结果
     */
    public int updateBusConstruct(BusConstruct busConstruct);

    /**
     * 批量删除建筑篇章
     *
     * @param conIds 需要删除的建筑篇章ID
     * @return 结果
     */
    public int deleteBusConstructByIds(Long[] conIds);

    /**
     * 删除建筑篇章信息
     *
     * @param conId 建筑篇章ID
     * @return 结果
     */
    public int deleteBusConstructById(Long conId);
}
