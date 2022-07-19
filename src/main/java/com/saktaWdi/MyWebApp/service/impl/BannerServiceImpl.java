package com.saktaWdi.MyWebApp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saktaWdi.MyWebApp.mapper.BannerMapper;
import com.saktaWdi.MyWebApp.model.entity.Banner;
import com.saktaWdi.MyWebApp.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;
    @Override
    public List<Banner> getBannerList() {
        return bannerMapper.selectList(new QueryWrapper<Banner>());
    }
}
