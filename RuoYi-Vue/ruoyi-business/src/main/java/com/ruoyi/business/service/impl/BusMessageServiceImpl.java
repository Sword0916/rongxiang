package com.ruoyi.business.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BusMessageMapper;
import com.ruoyi.business.domain.BusMessage;
import com.ruoyi.business.service.IBusMessageService;

/**
 * 留言Service业务层处理
 *
 * @author sword
 * @date 2020-08-06
 */
@Service
public class BusMessageServiceImpl implements IBusMessageService {
    @Autowired
    private BusMessageMapper busMessageMapper;

    /**
     * 查询留言
     *
     * @param messageId 留言ID
     * @return 留言
     */
    @Override
    public BusMessage selectBusMessageById(Long messageId) {
        return busMessageMapper.selectBusMessageById(messageId);
    }

    /**
     * 查询留言列表
     *
     * @param busMessage 留言
     * @return 留言
     */
    @Override
    public List<BusMessage> selectBusMessageList(BusMessage busMessage) {
        return busMessageMapper.selectBusMessageList(busMessage);
    }

    /**
     * 新增留言
     *
     * @param busMessage 留言
     * @return 结果
     */
    @Override
    public int insertBusMessage(BusMessage busMessage) {
        busMessage.setCreateTime(DateUtils.getNowDate());
        return busMessageMapper.insertBusMessage(busMessage);
    }

    /**
     * 修改留言
     *
     * @param busMessage 留言
     * @return 结果
     */
    @Override
    public int updateBusMessage(BusMessage busMessage) {
        return busMessageMapper.updateBusMessage(busMessage);
    }

    /**
     * 批量删除留言
     *
     * @param messageIds 需要删除的留言ID
     * @return 结果
     */
    @Override
    public int deleteBusMessageByIds(Long[] messageIds) {
        return busMessageMapper.deleteBusMessageByIds(messageIds);
    }

    /**
     * 删除留言信息
     *
     * @param messageId 留言ID
     * @return 结果
     */
    @Override
    public int deleteBusMessageById(Long messageId) {
        return busMessageMapper.deleteBusMessageById(messageId);
    }
}
