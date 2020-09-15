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
import com.ruoyi.business.domain.BusBid;
import com.ruoyi.business.service.IBusBidService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 招标文章Controller
 *
 * @author sword
 * @date 2020-08-08
 */
@RestController
@RequestMapping("/business/bid")
public class BusBidController extends BaseController {
    @Autowired
    private IBusBidService busBidService;

    /**
     * 查询招标文章列表
     */
    @PreAuthorize("@ss.hasPermi('business:bid:list')")
    @GetMapping("/list")
    public TableDataInfo list(BusBid busBid) {
        startPage();
        List<BusBid> list = busBidService.selectBusBidList(busBid);
        return getDataTable(list);
    }

    /**
     * 导出招标文章列表
     */
    @PreAuthorize("@ss.hasPermi('business:bid:export')")
    @Log(title = "招标文章", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BusBid busBid) {
        List<BusBid> list = busBidService.selectBusBidList(busBid);
        ExcelUtil<BusBid> util = new ExcelUtil<BusBid>(BusBid.class);
        return util.exportExcel(list, "bid");
    }

    /**
     * 获取招标文章详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:bid:query')")
    @GetMapping(value = "/{bidId}")
    public AjaxResult getInfo(@PathVariable("bidId") Long bidId) {
        return AjaxResult.success(busBidService.selectBusBidById(bidId));
    }

    /**
     * 新增招标文章
     */
    @PreAuthorize("@ss.hasPermi('business:bid:add')")
    @Log(title = "招标文章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BusBid busBid) {
        busBid.setCreateBy(SecurityUtils.getUsername());
        return toAjax(busBidService.insertBusBid(busBid));
    }

    /**
     * 修改招标文章
     */
    @PreAuthorize("@ss.hasPermi('business:bid:edit')")
    @Log(title = "招标文章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BusBid busBid) {
        busBid.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(busBidService.updateBusBid(busBid));
    }

    /**
     * 删除招标文章
     */
    @PreAuthorize("@ss.hasPermi('business:bid:remove')")
    @Log(title = "招标文章", businessType = BusinessType.DELETE)
    @DeleteMapping("/{bidIds}")
    public AjaxResult remove(@PathVariable Long[] bidIds) {
        return toAjax(busBidService.deleteBusBidByIds(bidIds));
    }
}
