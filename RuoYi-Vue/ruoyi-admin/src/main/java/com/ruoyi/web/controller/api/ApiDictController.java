package com.ruoyi.web.controller.api;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 字典接口Controller
 *
 * @author swrod
 * @date 2020-08-05
 */
@RestController
@RequestMapping("/api/dict")
public class ApiDictController extends BaseController {
    @Autowired
    private ISysDictDataService dictDataService;

    /**
     * 获取字典数据列表
     *
     * @param dictData
     * @return
     */
    @GetMapping("/list")
    public List<SysDictData> list(SysDictData dictData) {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return list;
    }
}
