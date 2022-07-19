package com.saktaWdi.MyWebApp;

import com.saktaWdi.MyWebApp.model.entity.Banner;
import com.saktaWdi.MyWebApp.service.BannerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = MyWebAppApplication.class)
@Slf4j
class BannerTests {

	@Autowired
	private BannerService bannerService;
	@Test
	public void testBannerList(){
		List<Banner> list = bannerService.getBannerList();
		log.info("轮播图列表:{}",list);
	}

}
