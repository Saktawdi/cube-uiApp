package com.saktaWdi.MyWebApp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saktaWdi.MyWebApp.mapper.NoticeMapper;
import com.saktaWdi.MyWebApp.model.entity.Notice;
import com.saktaWdi.MyWebApp.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Override
    public List<Notice> showAllNotices() {
        List<Notice> notices = noticeMapper.selectList(new QueryWrapper<Notice>());
        return notices;
    }

    @Override
    public int addNotice(Notice notice) {
        return noticeMapper.insert(notice);
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeMapper.updateById(notice);
    }

    @Override
    public Notice findNoticeById(int id) {
        Notice notice = noticeMapper.selectOne(new QueryWrapper<Notice>().eq("id",id));
        return notice;
    }

    @Override
    public int getNoticesNum() {
        return noticeMapper.selectCount(new QueryWrapper<Notice>().select("id"));
    }
}
