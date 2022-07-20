package com.saktaWdi.MyWebApp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saktaWdi.MyWebApp.config.CacheKeyConfig;
import com.saktaWdi.MyWebApp.mapper.BannerMapper;
import com.saktaWdi.MyWebApp.model.entity.Banner;
import com.saktaWdi.MyWebApp.service.BannerService;
import com.saktaWdi.MyWebApp.utils.BashCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerMapper bannerMapper;
    @Autowired
    private BashCache bashCache;
    @Override
    public List<Banner> getBannerList() {
        try {
            Object cacheObj= bashCache.getCache().get(CacheKeyConfig.BANNER_KEY,()->{
               List<Banner> bannerList= bannerMapper.selectList(new QueryWrapper<>());
               return bannerList;
            });
            if (cacheObj instanceof List){
                List<Banner> bannerList=(List<Banner>) cacheObj;
                return bannerList;
            }
        }catch(ExecutionException e){
            e.printStackTrace();
        }
        return null;

    }
}
