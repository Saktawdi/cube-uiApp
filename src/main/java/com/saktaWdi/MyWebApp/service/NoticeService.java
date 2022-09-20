package com.saktaWdi.MyWebApp.service;

import com.saktaWdi.MyWebApp.model.entity.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> showAllNotices();
    int addNotice(Notice notice);
    Notice findNoticeById(int id);
    int getNoticesNum();
    int updateNotice(Notice notice);
}
