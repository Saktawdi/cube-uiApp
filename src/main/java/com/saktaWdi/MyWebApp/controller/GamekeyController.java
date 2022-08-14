package com.saktaWdi.MyWebApp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.saktaWdi.MyWebApp.model.entity.GameKey;
import com.saktaWdi.MyWebApp.model.entity.KeyList;
import com.saktaWdi.MyWebApp.service.GameKeyService;
import com.saktaWdi.MyWebApp.utils.CommonResult;
import com.saktaWdi.MyWebApp.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
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
        gameKey.setId(gameKeyService.getGameMaxId()+1);
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

    @PostMapping("getSecretKey")
    public CommonResult getSecretKey(@RequestBody String jsonData){
        String num= JSON.parseObject(jsonData).getString("user_num");
        String name=JSON.parseObject(jsonData).getString("game_name");
        String tempStr=num+"|@|"+name;
        String key=CommonUtils.MD5(tempStr);
        if(!key.isEmpty()){
            return CommonResult.ok().setResult(key);
        }else{
            return CommonResult.fail();
        }
    }

    @PostMapping("saveKeys")
    public CommonResult saveKeys(@RequestBody String jsonData){
        String secretKey=JSON.parseObject(jsonData).getString("secret_key");
        String listJson = JSON.parseObject(jsonData).getString("list_json");
        KeyList keyList=gameKeyService.getKeysBySecretKey(secretKey);
        if(keyList!=null){
            if(keyList.getSecretKey().equals(listJson)){
                return CommonResult.ok().setResult("无需更新");
            }
            keyList.setListJson(listJson);
            int rows=gameKeyService.updateKeys(keyList);
            return rows==1?CommonResult.ok().setResult("更新成功"):CommonResult.fail().setResult("更新失败");
        }else{
            KeyList newKeyList=new KeyList();
            newKeyList.setId(gameKeyService.getKeyMaxId()+1);
            newKeyList.setSecretKey(secretKey);
            newKeyList.setListJson(listJson);
            int rows=gameKeyService.addKeys(newKeyList);
            return rows==1?CommonResult.ok().setResult("新增数据成功"):CommonResult.fail().setResult("新增失败");
        }
    }

    @PostMapping("getKeys")
    public CommonResult getKeys(@RequestBody String jsonData){
        String secretKey=JSON.parseObject(jsonData).getString("secret_key");
        KeyList keyList=gameKeyService.getKeysBySecretKey(secretKey);
        if(keyList!=null){
            return CommonResult.ok().setResult(keyList.getListJson());
        }else{
            return CommonResult.fail().setResult("无此结果");
        }
    }

    @PostMapping("updataKeysApi")
    public CommonResult updataKeys(@RequestBody String jsonData){
        String secretKey=JSON.parseObject(jsonData).getString("secret_key");
        String listJson = JSON.parseObject(jsonData).getString("list_json");
        if(secretKey.isEmpty()){
            return CommonResult.fail().setResult("密钥为空");
        }
        KeyList keyList=gameKeyService.getKeysBySecretKey(secretKey);
        if(keyList==null){
            return CommonResult.fail().setResult("无此密钥！请检查密钥是否正确");
        }
        keyList.setListJson(listJson);
        int rows=gameKeyService.updateKeys(keyList);
        return rows==1?CommonResult.ok().setResult("更新成功"):CommonResult.fail().setResult("更新失败!");
    }
}
