package com.ruoyi.web.controller.api;

import com.ruoyi.business.domain.BusArticle;
import com.ruoyi.business.domain.BusMessage;
import com.ruoyi.business.service.IBusArticleService;
import com.ruoyi.business.service.IBusMessageService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章接口Controller
 *
 * @author swrod
 * @date 2020-08-05
 */
@RestController
@RequestMapping("/api/article")
public class ApiArticleController extends BaseController {
    @Autowired
    private IBusArticleService busArticleService;

    /**
     * 查询文章列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BusArticle busArticle) {
        startPage();
        List<BusArticle> list = busArticleService.selectBusArticleList(busArticle);
        return getDataTable(list);
    }

    /**
     * 获取文章详细信息
     */
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(@PathVariable("articleId") Long articleId) {
        return AjaxResult.success(busArticleService.selectBusArticleById(articleId));
    }
}
