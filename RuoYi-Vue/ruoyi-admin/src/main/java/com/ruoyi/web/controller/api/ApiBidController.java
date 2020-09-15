package com.ruoyi.web.controller.api;

import com.ruoyi.business.domain.BusArticle;
import com.ruoyi.business.domain.BusBid;
import com.ruoyi.business.domain.BusMessage;
import com.ruoyi.business.service.IBusArticleService;
import com.ruoyi.business.service.IBusBidService;
import com.ruoyi.business.service.IBusMessageService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 招标接口Controller
 *
 * @author swrod
 * @date 2020-08-05
 */
@RestController
@RequestMapping("/api/bid")
public class ApiBidController extends BaseController {
    @Autowired
    private IBusBidService busBidService;
    @Autowired
    private IBusMessageService busMessageService;

    /**
     * 查询招标文章列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BusBid busBid) {
        startPage();
        List<BusBid> list = busBidService.selectBusBidList(busBid);
        return getDataTable(list);
    }

    /**
     * 获取招标文章详细信息
     */
    @GetMapping(value = "/{bidId}")
    public AjaxResult getInfo(@PathVariable("bidId") Long bidId) {
        return AjaxResult.success(busBidService.selectBusBidById(bidId));
    }


    /**
     * 新增留言
     */
    @Log(title = "投标", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusMessage busMessage) {
        return toAjax(busMessageService.insertBusMessage(busMessage));
    }
}
