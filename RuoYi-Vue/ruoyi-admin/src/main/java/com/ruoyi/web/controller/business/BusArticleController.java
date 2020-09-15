package com.ruoyi.web.controller.business;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
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
import com.ruoyi.business.domain.BusArticle;
import com.ruoyi.business.service.IBusArticleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文章Controller
 *
 * @author swrod
 * @date 2020-08-05
 */
@RestController
@RequestMapping("/business/article")
public class BusArticleController extends BaseController {
    @Autowired
    private IBusArticleService busArticleService;

    /**
     * 查询文章列表
     */
    @PreAuthorize("@ss.hasPermi('business:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusArticle busArticle) {
        startPage();
        List<BusArticle> list = busArticleService.selectBusArticleList(busArticle);
        return getDataTable(list);
    }

    /**
     * 导出文章列表
     */
    @PreAuthorize("@ss.hasPermi('business:article:export')")
    @Log(title = "文章", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusArticle busArticle) {
        List<BusArticle> list = busArticleService.selectBusArticleList(busArticle);
        ExcelUtil<BusArticle> util = new ExcelUtil<BusArticle>(BusArticle.class);
        return util.exportExcel(list, "article");
    }

    /**
     * 获取文章详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:article:query')")
    @GetMapping(value = "/{articleId}")
    public AjaxResult getInfo(@PathVariable("articleId") Long articleId) {
        return AjaxResult.success(busArticleService.selectBusArticleById(articleId));
    }

    /**
     * 新增文章
     */
    @PreAuthorize("@ss.hasPermi('business:article:add')")
    @Log(title = "文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusArticle busArticle) {
        busArticle.setCreateBy(SecurityUtils.getUsername());
        return toAjax(busArticleService.insertBusArticle(busArticle));
    }

    /**
     * 修改文章
     */
    @PreAuthorize("@ss.hasPermi('business:article:edit')")
    @Log(title = "文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusArticle busArticle) {
        busArticle.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(busArticleService.updateBusArticle(busArticle));
    }

    /**
     * 删除文章
     */
    @PreAuthorize("@ss.hasPermi('business:article:remove')")
    @Log(title = "文章", businessType = BusinessType.DELETE)
    @DeleteMapping("/{articleIds}")
    public AjaxResult remove(@PathVariable Long[] articleIds) {
        return toAjax(busArticleService.deleteBusArticleByIds(articleIds));
    }
}
