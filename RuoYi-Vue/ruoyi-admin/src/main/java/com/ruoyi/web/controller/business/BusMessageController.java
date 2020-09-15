package com.ruoyi.web.controller.business;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.business.domain.BusMessage;
import com.ruoyi.business.service.IBusMessageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 投标Controller
 *
 * @author sword
 * @date 2020-08-06
 */
@RestController
@RequestMapping("/business/message")
public class BusMessageController extends BaseController {
    @Autowired
    private IBusMessageService busMessageService;

    /**
     * 查询投标列表
     */
    @PreAuthorize("@ss.hasPermi('business:message:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusMessage busMessage) {
        startPage();
        List<BusMessage> list = busMessageService.selectBusMessageList(busMessage);
        return getDataTable(list);
    }

    /**
     * 导出投标列表
     */
    @PreAuthorize("@ss.hasPermi('business:message:export')")
    @Log(title = "投标", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusMessage busMessage) {
        List<BusMessage> list = busMessageService.selectBusMessageList(busMessage);
        ExcelUtil<BusMessage> util = new ExcelUtil<BusMessage>(BusMessage.class);
        return util.exportExcel(list, "message");
    }

    /**
     * 获取投标详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:message:query')")
    @GetMapping(value = "/{messageId}")
    public AjaxResult getInfo(@PathVariable("messageId") Long messageId) {
        return AjaxResult.success(busMessageService.selectBusMessageById(messageId));
    }

    /**
     * 新增投标
     */
    @PreAuthorize("@ss.hasPermi('business:message:add')")
    @Log(title = "投标", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusMessage busMessage) {
        return toAjax(busMessageService.insertBusMessage(busMessage));
    }

    /**
     * 修改投标
     */
    @PreAuthorize("@ss.hasPermi('business:message:edit')")
    @Log(title = "投标", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusMessage busMessage) {
        return toAjax(busMessageService.updateBusMessage(busMessage));
    }

    /**
     * 删除投标
     */
    @PreAuthorize("@ss.hasPermi('business:message:remove')")
    @Log(title = "投标", businessType = BusinessType.DELETE)
    @DeleteMapping("/{messageIds}")
    public AjaxResult remove(@PathVariable Long[] messageIds) {
        return toAjax(busMessageService.deleteBusMessageByIds(messageIds));
    }
}
