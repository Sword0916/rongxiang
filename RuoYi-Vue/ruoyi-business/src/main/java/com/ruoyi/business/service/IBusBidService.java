package com.ruoyi.business.service;

import java.util.List;

import com.ruoyi.business.domain.BusBid;

/**
 * 招标文章Service接口
 *
 * @author sword
 * @date 2020-08-08
 */
public interface IBusBidService {
    /**
     * 查询招标文章
     *
     * @param bidId 招标文章ID
     * @return 招标文章
     */
    public BusBid selectBusBidById(Long bidId);

    /**
     * 查询招标文章列表
     *
     * @param busBid 招标文章
     * @return 招标文章集合
     */
    public List<BusBid> selectBusBidList(BusBid busBid);

    /**
     * 新增招标文章
     *
     * @param busBid 招标文章
     * @return 结果
     */
    public int insertBusBid(BusBid busBid);

    /**
     * 修改招标文章
     *
     * @param busBid 招标文章
     * @return 结果
     */
    public int updateBusBid(BusBid busBid);

    /**
     * 批量删除招标文章
     *
     * @param bidIds 需要删除的招标文章ID
     * @return 结果
     */
    public int deleteBusBidByIds(Long[] bidIds);

    /**
     * 删除招标文章信息
     *
     * @param bidId 招标文章ID
     * @return 结果
     */
    public int deleteBusBidById(Long bidId);
}
