package hu.rozsa.daniel.learningapplication.architect.clean;

import hu.rozsa.daniel.learningapplication.architect.clean.gateway.FakeUserGateway;
import rdani.hu.logic.GatewayFactory;
import rdani.hu.logic.gateway.UserGateway;

public class DefaultGatewayFactory extends GatewayFactory {
    @Override
    protected UserGateway createUserGateway() {
        return new FakeUserGateway();
    }
}
