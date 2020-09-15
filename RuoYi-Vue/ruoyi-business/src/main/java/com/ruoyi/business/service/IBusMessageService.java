package com.ruoyi.business.service;

import java.util.List;

import com.ruoyi.business.domain.BusMessage;

/**
 * 留言Service接口
 *
 * @author sword
 * @date 2020-08-06
 */
public interface IBusMessageService {
    /**
     * 查询留言
     *
     * @param messageId 留言ID
     * @return 留言
     */
    public BusMessage selectBusMessageById(Long messageId);

    /**
     * 查询留言列表
     *
     * @param busMessage 留言
     * @return 留言集合
     */
    public List<BusMessage> selectBusMessageList(BusMessage busMessage);

    /**
     * 新增留言
     *
     * @param busMessage 留言
     * @return 结果
     */
    public int insertBusMessage(BusMessage busMessage);

    /**
     * 修改留言
     *
     * @param busMessage 留言
     * @return 结果
     */
    public int updateBusMessage(BusMessage busMessage);

    /**
     * 批量删除留言
     *
     * @param messageIds 需要删除的留言ID
     * @return 结果
     */
    public int deleteBusMessageByIds(Long[] messageIds);

    /**
     * 删除留言信息
     *
     * @param messageId 留言ID
     * @return 结果
     */
    public int deleteBusMessageById(Long messageId);
}
