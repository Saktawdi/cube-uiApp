package com.saktaWdi.MyWebApp.controller;

import com.saktaWdi.MyWebApp.model.entity.Notice;
import com.saktaWdi.MyWebApp.service.NoticeService;
import com.saktaWdi.MyWebApp.utils.CommonResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("api/v1/pri/admin/noticeSys")
public class NoticeSysController {
    @Autowired
    private NoticeService noticeService;
    @PostMapping("add")
    public CommonResult addNotice(@Validated  @RequestBody Notice notice){
        if(noticeService.findNoticeById(notice.getId())!=null) return CommonResult.fail().setResult("已经存在ID为"+notice.getId()+"的公告");
        int rows = noticeService.addNotice(notice);
        return rows == 1 ? CommonResult.ok() : CommonResult.fail().setResult("新增公告失败");
    }

    @GetMapping("getNotices")
    public CommonResult showAllNotices(){
        List<Notice> notices = noticeService.showAllNotices();
        return CommonResult.ok().setResult(notices);
    }

    @PutMapping("setStatus")
    public CommonResult setStatus(@RequestBody @RequestParam(name = "notice_id") int id, @RequestBody int status){
        try {
            Notice notice = noticeService.findNoticeById(id);
            notice.setStatus(status);

            int rows = noticeService.updateNotice(notice);
            return rows == 1 ? CommonResult.ok() : CommonResult.fail();
        }catch(NullPointerException e){
            return CommonResult.fail().setResult("传入notice_id或status参数为空");
        }
    }
    @PostMapping("updateNotice")
    public CommonResult updateNotice(@Validated @RequestBody Notice newNotice){
        Notice notice = noticeService.findNoticeById(newNotice.getId());
        notice.setContent(newNotice.getContent());
        notice.setTitle(newNotice.getTitle());
        int rows = noticeService.updateNotice(notice);
        return rows == 1 ? CommonResult.ok() : CommonResult.fail();
    }
}
