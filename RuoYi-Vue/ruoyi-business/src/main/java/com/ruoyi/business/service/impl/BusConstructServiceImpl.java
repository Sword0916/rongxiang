package com.ruoyi.business.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BusConstructMapper;
import com.ruoyi.business.domain.BusConstruct;
import com.ruoyi.business.service.IBusConstructService;

/**
 * 建筑篇章Service业务层处理
 *
 * @author Sword
 * @date 2020-08-21
 */
@Service
public class BusConstructServiceImpl implements IBusConstructService {
    @Autowired
    private BusConstructMapper busConstructMapper;

    /**
     * 查询建筑篇章
     *
     * @param conId 建筑篇章ID
     * @return 建筑篇章
     */
    @Override
    public BusConstruct selectBusConstructById(Long conId) {
        return busConstructMapper.selectBusConstructById(conId);
    }

    /**
     * 查询建筑篇章列表
     *
     * @param busConstruct 建筑篇章
     * @return 建筑篇章
     */
    @Override
    public List<BusConstruct> selectBusConstructList(BusConstruct busConstruct) {
        return busConstructMapper.selectBusConstructList(busConstruct);
    }

    /**
     * 新增建筑篇章
     *
     * @param busConstruct 建筑篇章
     * @return 结果
     */
    @Override
    public int insertBusConstruct(BusConstruct busConstruct) {
        busConstruct.setCreateTime(DateUtils.getNowDate());
        return busConstructMapper.insertBusConstruct(busConstruct);
    }

    /**
     * 修改建筑篇章
     *
     * @param busConstruct 建筑篇章
     * @return 结果
     */
    @Override
    public int updateBusConstruct(BusConstruct busConstruct) {
        busConstruct.setUpdateTime(DateUtils.getNowDate());
        return busConstructMapper.updateBusConstruct(busConstruct);
    }

    /**
     * 批量删除建筑篇章
     *
     * @param conIds 需要删除的建筑篇章ID
     * @return 结果
     */
    @Override
    public int deleteBusConstructByIds(Long[] conIds) {
        return busConstructMapper.deleteBusConstructByIds(conIds);
    }

    /**
     * 删除建筑篇章信息
     *
     * @param conId 建筑篇章ID
     * @return 结果
     */
    @Override
    public int deleteBusConstructById(Long conId) {
        return busConstructMapper.deleteBusConstructById(conId);
    }
}
