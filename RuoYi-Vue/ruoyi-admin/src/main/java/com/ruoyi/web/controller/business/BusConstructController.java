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
import com.ruoyi.business.domain.BusConstruct;
import com.ruoyi.business.service.IBusConstructService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 建筑篇章Controller
 *
 * @author Sword
 * @date 2020-08-21
 */
@RestController
@RequestMapping("/business/construct")
public class BusConstructController extends BaseController {
    @Autowired
    private IBusConstructService busConstructService;

    /**
     * 查询建筑篇章列表
     */
    @PreAuthorize("@ss.hasPermi('business:construct:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusConstruct busConstruct) {
        startPage();
        List<BusConstruct> list = busConstructService.selectBusConstructList(busConstruct);
        return getDataTable(list);
    }

    /**
     * 导出建筑篇章列表
     */
    @PreAuthorize("@ss.hasPermi('business:construct:export')")
    @Log(title = "建筑篇章", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusConstruct busConstruct) {
        List<BusConstruct> list = busConstructService.selectBusConstructList(busConstruct);
        ExcelUtil<BusConstruct> util = new ExcelUtil<BusConstruct>(BusConstruct.class);
        return util.exportExcel(list, "construct");
    }

    /**
     * 获取建筑篇章详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:construct:query')")
    @GetMapping(value = "/{conId}")
    public AjaxResult getInfo(@PathVariable("conId") Long conId) {
        return AjaxResult.success(busConstructService.selectBusConstructById(conId));
    }

    /**
     * 新增建筑篇章
     */
    @PreAuthorize("@ss.hasPermi('business:construct:add')")
    @Log(title = "建筑篇章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusConstruct busConstruct) {
        return toAjax(busConstructService.insertBusConstruct(busConstruct));
    }

    /**
     * 修改建筑篇章
     */
    @PreAuthorize("@ss.hasPermi('business:construct:edit')")
    @Log(title = "建筑篇章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusConstruct busConstruct) {
        return toAjax(busConstructService.updateBusConstruct(busConstruct));
    }

    /**
     * 删除建筑篇章
     */
    @PreAuthorize("@ss.hasPermi('business:construct:remove')")
    @Log(title = "建筑篇章", businessType = BusinessType.DELETE)
    @DeleteMapping("/{conIds}")
    public AjaxResult remove(@PathVariable Long[] conIds) {
        return toAjax(busConstructService.deleteBusConstructByIds(conIds));
    }
}
