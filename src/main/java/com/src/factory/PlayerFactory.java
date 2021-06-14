package com.src.factory;

import com.src.entity.Ninja;
import com.src.entity.Player;
import com.src.mapper.GridMapper;

public class PlayerFactory {

    public static Player create() {
        GridMapper gridMapper = new GridMapper();

        Player player = new Player(gridMapper);

        player.getNinjas().add(0, new Ninja(2,true));
        player.getNinjas().add(1, new Ninja(1));
        player.getNinjas().add(2, new Ninja(1));

        return player;
    }
}
