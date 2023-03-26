package com.blade.chatgpt.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.blade.common.annotation.Log;
import com.blade.common.core.controller.BaseController;
import com.blade.common.core.domain.AjaxResult;
import com.blade.common.enums.BusinessType;
import com.blade.chatgpt.domain.WebUserLimitMsg;
import com.blade.chatgpt.service.IWebUserLimitMsgService;
import com.blade.common.utils.poi.ExcelUtil;
import com.blade.common.core.page.TableDataInfo;

/**
 * web用户使用限制信息Controller
 * 
 * @author Blade
 * @date 2023-03-25
 */
@RestController
@RequestMapping("/chatgpt/webuserLimitMsg")
public class WebUserLimitMsgController extends BaseController
{
    @Autowired
    private IWebUserLimitMsgService webUserLimitMsgService;

    /**
     * 查询web用户使用限制信息列表
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:webuserLimitMsg:list')")
    @GetMapping("/list")
    public TableDataInfo list(WebUserLimitMsg webUserLimitMsg)
    {
        startPage();
        List<WebUserLimitMsg> list = webUserLimitMsgService.selectWebUserLimitMsgList(webUserLimitMsg);
        return getDataTable(list);
    }

    /**
     * 导出web用户使用限制信息列表
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:webuserLimitMsg:export')")
    @Log(title = "web用户使用限制信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WebUserLimitMsg webUserLimitMsg)
    {
        List<WebUserLimitMsg> list = webUserLimitMsgService.selectWebUserLimitMsgList(webUserLimitMsg);
        ExcelUtil<WebUserLimitMsg> util = new ExcelUtil<WebUserLimitMsg>(WebUserLimitMsg.class);
        util.exportExcel(response, list, "web用户使用限制信息数据");
    }

    /**
     * 获取web用户使用限制信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:webuserLimitMsg:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(webUserLimitMsgService.selectWebUserLimitMsgById(id));
    }

    /**
     * 新增web用户使用限制信息
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:webuserLimitMsg:add')")
    @Log(title = "web用户使用限制信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WebUserLimitMsg webUserLimitMsg)
    {
        return toAjax(webUserLimitMsgService.insertWebUserLimitMsg(webUserLimitMsg));
    }

    /**
     * 修改web用户使用限制信息
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:webuserLimitMsg:edit')")
    @Log(title = "web用户使用限制信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WebUserLimitMsg webUserLimitMsg)
    {
        return toAjax(webUserLimitMsgService.updateWebUserLimitMsg(webUserLimitMsg));
    }

    /**
     * 删除web用户使用限制信息
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:webuserLimitMsg:remove')")
    @Log(title = "web用户使用限制信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(webUserLimitMsgService.deleteWebUserLimitMsgByIds(ids));
    }
}
