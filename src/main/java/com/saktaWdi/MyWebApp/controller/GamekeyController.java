package com.saktaWdi.MyWebApp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.saktaWdi.MyWebApp.model.entity.GameKey;
import com.saktaWdi.MyWebApp.service.GameKeyService;
import com.saktaWdi.MyWebApp.utils.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/v1/pri/gameKey")
@Slf4j
public class GamekeyController {
    @Autowired
    private GameKeyService gameKeyService;

    @PostMapping("addGames")
    public CommonResult addGames(@RequestBody String JsonData){
        GameKey gameKey=new GameKey();
        gameKey.setId(gameKeyService.getMaxId()+1);
        JsonData.replace("\\\\","");
        JSONObject keyData= JSON.parseObject(JsonData);
        keyData=JSON.parseObject(keyData.getString("JsonData"));
        gameKey.setUserNum(keyData.getIntValue("user_num"));
        JSONArray games=keyData.getJSONArray("games");
        gameKey.setGames(JSON.toJSONString(games));
        gameKey.setCreateTime(new Date());

        GameKey oldGamekey=gameKeyService.getGameNamesByNum(gameKey.getUserNum());
        if(oldGamekey==null){
            int rows=gameKeyService.addGames(gameKey);
            return rows==1?CommonResult.ok():CommonResult.fail();
        }else{
            if(oldGamekey.getGames().equals(gameKey.getGames())){
                return CommonResult.ok().setResult("无需更新");
            }
            oldGamekey.setGames(gameKey.getGames());
            int rows=gameKeyService.updateGames(oldGamekey);
            return rows==1?CommonResult.ok().setResult("更新成功"):CommonResult.fail();
        }
    }

    @PostMapping("getGames")
    public CommonResult getGames(@RequestBody String userNum){
        Integer num= Integer.valueOf(JSON.parseObject(userNum).getString("user_num"));
        GameKey gamekey=gameKeyService.getGameNamesByNum(num);
        if (gamekey!=null){
            return CommonResult.ok().setResult(gamekey.getGames());
        }else{
            return CommonResult.fail().setResult("无数据");
        }
    }


}
