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
import com.blade.chatgpt.domain.GptAccount;
import com.blade.chatgpt.service.IGptAccountService;
import com.blade.common.utils.poi.ExcelUtil;
import com.blade.common.core.page.TableDataInfo;

/**
 * GPT 账号Controller
 * 
 * @author blade
 * @date 2023-03-12
 */
@RestController
@RequestMapping("/chatgpt/gptAccount")
public class GptAccountController extends BaseController
{
    @Autowired
    private IGptAccountService gptAccountService;

    /**
     * 查询GPT 账号列表
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:gptAccount:list')")
    @GetMapping("/list")
    public TableDataInfo list(GptAccount gptAccount)
    {
        startPage();
        List<GptAccount> list = gptAccountService.selectGptAccountList(gptAccount);
        return getDataTable(list);
    }

    /**
     * 导出GPT 账号列表
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:gptAccount:export')")
    @Log(title = "GPT 账号", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GptAccount gptAccount)
    {
        List<GptAccount> list = gptAccountService.selectGptAccountList(gptAccount);
        ExcelUtil<GptAccount> util = new ExcelUtil<GptAccount>(GptAccount.class);
        util.exportExcel(response, list, "GPT 账号数据");
    }

    /**
     * 获取GPT 账号详细信息
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:gptAccount:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(gptAccountService.selectGptAccountById(id));
    }

    /**
     * 新增GPT 账号
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:gptAccount:add')")
    @Log(title = "GPT 账号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GptAccount gptAccount)
    {
        return toAjax(gptAccountService.insertGptAccount(gptAccount));
    }

    /**
     * 修改GPT 账号
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:gptAccount:edit')")
    @Log(title = "GPT 账号", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GptAccount gptAccount)
    {
        return toAjax(gptAccountService.updateGptAccount(gptAccount));
    }

    /**
     * 删除GPT 账号
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:gptAccount:remove')")
    @Log(title = "GPT 账号", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(gptAccountService.deleteGptAccountByIds(ids));
    }
}
