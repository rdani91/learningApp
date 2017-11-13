package rdani.hu.logic;

import rdani.hu.logic.interactor.UserInteractor;

public abstract class AbstractLogic {


    private       LazyInstance<GatewayFactory> gatewayFactoryLazyInstance = new LazyInstance<GatewayFactory>() {
        @Override
        GatewayFactory createInstance() {
            return createGateway();
        }
    };
    private final LazyInstance<UserInteractor> userInteractorLazyInstance = new LazyInstance<UserInteractor>() {
        @Override
        UserInteractor createInstance() {
            return createUserInteractor();
        }
    };

    public UserInteractor getUserInteractor() {
        return userInteractorLazyInstance.get();
    }

    private UserInteractor createUserInteractor() {
        return new UserInteractor(gatewayFactoryLazyInstance.get().getUserGateway());
    }

    protected abstract GatewayFactory createGateway();

}
