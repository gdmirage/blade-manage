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
import com.blade.chatgpt.domain.GptApiRequestMsg;
import com.blade.chatgpt.service.IGptApiRequestMsgService;
import com.blade.common.utils.poi.ExcelUtil;
import com.blade.common.core.page.TableDataInfo;

/**
 * GPT API 请求信息Controller
 * 
 * @author blade
 * @date 2023-03-12
 */
@RestController
@RequestMapping("/chatgpt/gptRequest")
public class GptApiRequestMsgController extends BaseController
{
    @Autowired
    private IGptApiRequestMsgService gptApiRequestMsgService;

    /**
     * 查询GPT API 请求信息列表
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:gptRequest:list')")
    @GetMapping("/list")
    public TableDataInfo list(GptApiRequestMsg gptApiRequestMsg)
    {
        startPage();
        List<GptApiRequestMsg> list = gptApiRequestMsgService.selectGptApiRequestMsgList(gptApiRequestMsg);
        return getDataTable(list);
    }

    /**
     * 导出GPT API 请求信息列表
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:gptRequest:export')")
    @Log(title = "GPT API 请求信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GptApiRequestMsg gptApiRequestMsg)
    {
        List<GptApiRequestMsg> list = gptApiRequestMsgService.selectGptApiRequestMsgList(gptApiRequestMsg);
        ExcelUtil<GptApiRequestMsg> util = new ExcelUtil<GptApiRequestMsg>(GptApiRequestMsg.class);
        util.exportExcel(response, list, "GPT API 请求信息数据");
    }

    /**
     * 获取GPT API 请求信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:gptRequest:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(gptApiRequestMsgService.selectGptApiRequestMsgById(id));
    }

    /**
     * 新增GPT API 请求信息
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:gptRequest:add')")
    @Log(title = "GPT API 请求信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GptApiRequestMsg gptApiRequestMsg)
    {
        return toAjax(gptApiRequestMsgService.insertGptApiRequestMsg(gptApiRequestMsg));
    }

    /**
     * 修改GPT API 请求信息
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:gptRequest:edit')")
    @Log(title = "GPT API 请求信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GptApiRequestMsg gptApiRequestMsg)
    {
        return toAjax(gptApiRequestMsgService.updateGptApiRequestMsg(gptApiRequestMsg));
    }

    /**
     * 删除GPT API 请求信息
     */
    @PreAuthorize("@ss.hasPermi('chatgpt:gptRequest:remove')")
    @Log(title = "GPT API 请求信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(gptApiRequestMsgService.deleteGptApiRequestMsgByIds(ids));
    }
}
