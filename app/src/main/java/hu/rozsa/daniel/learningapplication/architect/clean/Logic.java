package hu.rozsa.daniel.learningapplication.architect.clean;

import rdani.hu.logic.AbstractLogic;
import rdani.hu.logic.GatewayFactory;

public class Logic extends AbstractLogic {

    private static Logic instance = new Logic();

    private Logic() {
        //no instance
    }

    public static Logic getInstance() {
        return instance;
    }

    @Override
    protected GatewayFactory createGateway() {
        return new DefaultGatewayFactory();
    }
}
