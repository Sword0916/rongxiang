package com.ruoyi.business.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BusBidMapper;
import com.ruoyi.business.domain.BusBid;
import com.ruoyi.business.service.IBusBidService;

/**
 * 招标文章Service业务层处理
 *
 * @author sword
 * @date 2020-08-08
 */
@Service
public class BusBidServiceImpl implements IBusBidService {
    @Autowired
    private BusBidMapper busBidMapper;

    /**
     * 查询招标文章
     *
     * @param bidId 招标文章ID
     * @return 招标文章
     */
    @Override
    public BusBid selectBusBidById(Long bidId) {
        return busBidMapper.selectBusBidById(bidId);
    }

    /**
     * 查询招标文章列表
     *
     * @param busBid 招标文章
     * @return 招标文章
     */
    @Override
    public List<BusBid> selectBusBidList(BusBid busBid) {
        return busBidMapper.selectBusBidList(busBid);
    }

    /**
     * 新增招标文章
     *
     * @param busBid 招标文章
     * @return 结果
     */
    @Override
    public int insertBusBid(BusBid busBid) {
        busBid.setCreateTime(DateUtils.getNowDate());
        return busBidMapper.insertBusBid(busBid);
    }

    /**
     * 修改招标文章
     *
     * @param busBid 招标文章
     * @return 结果
     */
    @Override
    public int updateBusBid(BusBid busBid) {
        busBid.setUpdateTime(DateUtils.getNowDate());
        return busBidMapper.updateBusBid(busBid);
    }

    /**
     * 批量删除招标文章
     *
     * @param bidIds 需要删除的招标文章ID
     * @return 结果
     */
    @Override
    public int deleteBusBidByIds(Long[] bidIds) {
        return busBidMapper.deleteBusBidByIds(bidIds);
    }

    /**
     * 删除招标文章信息
     *
     * @param bidId 招标文章ID
     * @return 结果
     */
    @Override
    public int deleteBusBidById(Long bidId) {
        return busBidMapper.deleteBusBidById(bidId);
    }
}
