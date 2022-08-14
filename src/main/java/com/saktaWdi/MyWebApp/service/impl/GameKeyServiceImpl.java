package com.saktaWdi.MyWebApp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.saktaWdi.MyWebApp.mapper.GameKeyMapper;
import com.saktaWdi.MyWebApp.mapper.KeyListMapper;
import com.saktaWdi.MyWebApp.model.entity.GameKey;
import com.saktaWdi.MyWebApp.model.entity.KeyList;
import com.saktaWdi.MyWebApp.service.GameKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GameKeyServiceImpl implements GameKeyService {
    @Autowired
    private GameKeyMapper gameKeyMapper;
    @Autowired
    private KeyListMapper keyListMapper;
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
    public int getGameMaxId() {
        QueryWrapper<GameKey> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("id");
        int usersCount=gameKeyMapper.selectCount(queryWrapper);
        return usersCount;
    }

    @Override
    public int getKeyMaxId() {
        QueryWrapper<KeyList> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("id");
        int Count=keyListMapper.selectCount(queryWrapper);
        return Count;
    }

    @Override
    public int updateGames(GameKey gamekey) {
       return gameKeyMapper.updateById(gamekey);
    }

    @Override
    public int addKeys(KeyList keyList) {
        return keyListMapper.insert(keyList);
    }

    @Override
    public int updateKeys(KeyList keyList) {
        return keyListMapper.updateById(keyList);
    }

    @Override
    public KeyList getKeysBySecretKey(String secretKey) {
        return keyListMapper.selectOne(new QueryWrapper<KeyList>().eq("secret_key",secretKey));
    }
}
