package com.src.checker;

import com.src.entity.Ninja;
import com.src.entity.Player;

public class DataChecker {

    public void checkNinjas(Player player) {
        ninjasAlive(player);
        generalAlive(player);
    }

    /**
     *  Determina si el general esta vivo y si no es asi, cambia el boolean
     *  generalAlive a falso
     * */

    private void generalAlive(Player player) {
        boolean stillAlive = false;

        for (Ninja element: player.getNinjas()) {
            if (element.isGeneral()) {
                stillAlive = true;
            }
        }

        if (!stillAlive) {
            for (Ninja element : player.getNinjas()) {
                element.setGeneralAlive(false);
            }
        }
    }

    private void ninjasAlive(Player player) {
        System.out.println(player.getNinjas().size());
        int i = player.getNinjas().size() - 1;
        while (i >= 0) {
            if (player.getNinjas().get(i).getHp() <= 0) {
                player.getNinjas().remove(i);
            }
            i--;
        }

    }
}
