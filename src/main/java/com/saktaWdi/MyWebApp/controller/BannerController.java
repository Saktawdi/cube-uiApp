package com.saktaWdi.MyWebApp.controller;

import com.saktaWdi.MyWebApp.model.entity.Banner;
import com.saktaWdi.MyWebApp.service.BannerService;
import com.saktaWdi.MyWebApp.utils.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @GetMapping("list")
    public CommonResult<Banner> getBannerList() {
        List<Banner> bannerList=bannerService.getBannerList();
        if (!bannerList.isEmpty()){
            return CommonResult.ok().setResult(bannerList);
        }else{
            return CommonResult.fail();
        }
    }

}
