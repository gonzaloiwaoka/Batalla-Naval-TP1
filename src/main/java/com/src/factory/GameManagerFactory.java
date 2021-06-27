package com.src.factory;

import com.src.checker.DataChecker;
import com.src.entity.Player;
import com.src.errorManagement.ErrorFunctions;
import com.src.manager.ActionManager;
import com.src.manager.GameManager;
import com.src.manager.ServerManager;
import com.src.setuper.SetUperData;
import com.src.validator.ActionValidator;
import com.src.validator.AttackValidator;
import com.src.validator.MoveValidator;

public class GameManagerFactory {

    public static GameManager createGameManager(Player host, Player noHost, ServerManager serverManager) {

        SetUperData setUperData = new SetUperData(new ErrorFunctions());

        AttackValidator attackValidator = new AttackValidator();
        MoveValidator moveValidator = new MoveValidator();

        ActionValidator actionValidator = new ActionValidator(attackValidator, moveValidator, new ErrorFunctions());
        ActionManager actionManager = new ActionManager(new ErrorFunctions(), actionValidator);

        DataChecker dataChecker = new DataChecker();
        return new GameManager(setUperData, host, noHost, serverManager, actionManager, new ErrorFunctions(), dataChecker);
    }

}
