package com.src.errorManagement;

import java.util.ArrayList;
import java.util.List;

public class ErrorFunctions {

    private List<Error> errors;


    public List<Error> getErrors() {
        if (errors == null) {
            errors = new ArrayList<>();
        }

        return errors;
    }

    public void addError(String message) {
        getErrors().add(new Error(message));
    }

    public void addError(Error error) {
        getErrors().add(error);
    }

    public void addErrors(List<Error> errors) {
        getErrors().addAll(errors);
    }

    public void showErrors() {
        for (Error element: getErrors()) {
            System.out.println(element.getMessage());
        }
    }

    public void removeErrors() {
        getErrors().clear();
    }
}
