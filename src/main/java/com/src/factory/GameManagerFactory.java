package com.src.factory;

import com.src.checker.DataChecker;
import com.src.entity.Player;
import com.src.errorManagement.ErrorFunctions;
import com.src.manager.ActionManagerV2;
import com.src.manager.GameManagerV2;
import com.src.manager.ServerManager;
import com.src.server.connect.Connect;
import com.src.setuper.SetUperData;
import com.src.validator.ActionValidator;
import com.src.validator.AttackValidator;
import com.src.validator.MoveValidator;

public class GameManagerFactory {

    public static GameManagerV2 createGameManager(Player host, Player noHost, ServerManager serverManager) {

        SetUperData setUperData = new SetUperData(new ErrorFunctions());

        AttackValidator attackValidator = new AttackValidator();
        MoveValidator moveValidator = new MoveValidator();

        ActionValidator actionValidator = new ActionValidator(attackValidator, moveValidator, new ErrorFunctions());
        ActionManagerV2 actionManagerV2 = new ActionManagerV2(new ErrorFunctions(), actionValidator);

        DataChecker dataChecker = new DataChecker();
        return new GameManagerV2(setUperData, host, noHost, serverManager, actionManagerV2, new ErrorFunctions(), dataChecker);
    }

}
