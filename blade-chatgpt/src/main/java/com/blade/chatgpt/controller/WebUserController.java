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
import com.blade.chatgpt.domain.WebUser;
import com.blade.chatgpt.service.IWebUserService;
import com.blade.common.utils.poi.ExcelUtil;
import com.blade.common.core.page.TableDataInfo;

/**
 * web用户Controller
 *
 * @author blade
 * @date 2023-03-18
 */
@RestController
@RequestMapping("/chatgpt/webuser")
public class WebUserController extends BaseController {
    @Autowired
    private IWebUserService webUserService;

    /**
     * 查询web用户列表
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:webuser:list')")
    @GetMapping("/list")
    public TableDataInfo list(WebUser webUser) {
        startPage();
        List<WebUser> list = webUserService.selectWebUserList(webUser);
        return getDataTable(list);
    }

    /**
     * 导出web用户列表
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:webuser:export')")
    @Log(title = "web用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WebUser webUser) {
        List<WebUser> list = webUserService.selectWebUserList(webUser);
        ExcelUtil<WebUser> util = new ExcelUtil<WebUser>(WebUser.class);
        util.exportExcel(response, list, "web用户数据");
    }

    /**
     * 获取web用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:webuser:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return success(webUserService.selectWebUserById(id));
    }

    /**
     * 新增web用户
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:webuser:add')")
    @Log(title = "web用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WebUser webUser) {
        return toAjax(webUserService.insertWebUser(webUser));
    }

    /**
     * 修改web用户
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:webuser:edit')")
    @Log(title = "web用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WebUser webUser) {
        return toAjax(webUserService.updateWebUser(webUser));
    }

    /**
     * 删除web用户
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:webuser:remove')")
    @Log(title = "web用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(webUserService.deleteWebUserByIds(ids));
    }
}
