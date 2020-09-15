package com.ruoyi.web.controller.api;

import com.ruoyi.business.domain.BusArticle;
import com.ruoyi.business.domain.BusConstruct;
import com.ruoyi.business.domain.BusMessage;
import com.ruoyi.business.service.IBusArticleService;
import com.ruoyi.business.service.IBusConstructService;
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
 * 篇章接口Controller
 *
 * @author swrod
 * @date 2020-08-05
 */
@RestController
@RequestMapping("/api/construct")
public class ApiConstructController extends BaseController {
    @Autowired
    private IBusConstructService busConstructService;

    /**
     * 查询建筑篇章列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BusConstruct busConstruct) {
        startPage();
        List<BusConstruct> list = busConstructService.selectBusConstructList(busConstruct);
        return getDataTable(list);
    }

    /**
     * 获取建筑篇章详细信息
     */
    @GetMapping(value = "/{conId}")
    public AjaxResult getInfo(@PathVariable("conId") Long conId) {
        return AjaxResult.success(busConstructService.selectBusConstructById(conId));
    }
}
