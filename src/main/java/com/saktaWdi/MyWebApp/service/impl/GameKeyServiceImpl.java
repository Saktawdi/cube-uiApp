package com.saktaWdi.MyWebApp.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saktaWdi.MyWebApp.mapper.GameKeyMapper;
import com.saktaWdi.MyWebApp.model.entity.GameKey;
import com.saktaWdi.MyWebApp.model.entity.Users;
import com.saktaWdi.MyWebApp.service.GameKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GameKeyServiceImpl implements GameKeyService {
    @Autowired
    private GameKeyMapper gameKeyMapper;
    @Override
    public int addGames(GameKey gameKey) {
        return gameKeyMapper.insert(gameKey);
    }

    @Override
    public GameKey getGameNamesByNum(int userNum) {
        GameKey gameKey=gameKeyMapper.selectOne(new QueryWrapper<GameKey>().eq("user_num",userNum));
        return gameKey;
    }

    @Override
    public int getMaxId() {
        QueryWrapper<GameKey> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("id");
        int usersCount=gameKeyMapper.selectCount(queryWrapper);
        return usersCount;
    }

    @Override
    public int updateGames(GameKey gamekey) {
       return gameKeyMapper.updateById(gamekey);
    }
}
