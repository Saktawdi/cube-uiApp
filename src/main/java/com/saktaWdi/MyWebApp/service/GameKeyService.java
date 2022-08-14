package com.saktaWdi.MyWebApp.service;


import com.saktaWdi.MyWebApp.model.entity.GameKey;
import com.saktaWdi.MyWebApp.model.entity.KeyList;

public interface GameKeyService {
    int addGames(GameKey gamekey);
    int updateGames(GameKey gamekey);
    GameKey getGameNamesByNum(int userNum);
    int addKeys(KeyList keyList);
    int updateKeys(KeyList keyList);
    KeyList getKeysBySecretKey(String secretKey);

    int getGameMaxId();
    int getKeyMaxId();
}
