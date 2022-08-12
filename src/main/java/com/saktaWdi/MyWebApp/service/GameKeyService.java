package com.saktaWdi.MyWebApp.service;


import com.saktaWdi.MyWebApp.model.entity.GameKey;

public interface GameKeyService {
    int addGames(GameKey gamekey);
    int updateGames(GameKey gamekey);
    GameKey getGameNamesByNum(int userNum);

    int getMaxId();
}
