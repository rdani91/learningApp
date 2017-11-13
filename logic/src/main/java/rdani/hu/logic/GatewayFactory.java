package rdani.hu.logic;

import rdani.hu.logic.gateway.UserGateway;

public abstract class GatewayFactory {

    private LazyInstance<UserGateway> userGatewayLazyInstance = new LazyInstance<UserGateway>() {
        @Override
        UserGateway createInstance() {
            return createUserGateway();
        }
    };

    public UserGateway getUserGateway() {
        return userGatewayLazyInstance.get();
    }

    protected abstract UserGateway createUserGateway();

}
