package com.src.factory;

import com.src.loader.GameLoader;
import com.src.screens.*;

public class ScreenFactory {

    public static GameLoader createScreens() {

        ScreenLoad screenLoad = new ScreenLoad();
        ScreenMain screenMain = new ScreenMain();
        ScreenCreateGame screenCreateGame = new ScreenCreateGame();

        return new GameLoader(screenLoad, screenMain, screenCreateGame);
    }
}
