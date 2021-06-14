package com.src.factory;

import com.src.errorManagement.ErrorFunctions;
import com.src.manager.ActionManager;
import com.src.mapper.GridShower;
import com.src.validator.ActionValidator;
import com.src.validator.AttackValidator;
import com.src.validator.MoveValidator;


public class ActionManagerFactory {
    public static ActionManager create() {
        AttackValidator attackValidator = new AttackValidator();
        MoveValidator moveValidator = new MoveValidator();
        ActionValidator ninjaValidator = new ActionValidator();

        GridShower gridShower = new GridShower();

        return new ActionManager(ninjaValidator, gridShower, new ErrorFunctions());
    }
}
