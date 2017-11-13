package rdani.hu.logic.interactor;

import java.util.List;

import rdani.hu.logic.OnCompleteResult;
import rdani.hu.logic.entity.User;
import rdani.hu.logic.gateway.UserGateway;

public class UserInteractor {


    private final UserGateway userGateway;

    public UserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public void getUsers(final OnCompleteResult<List<User>> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<User> users = userGateway.getUsers();

                callback.onSuccess(users);
            }
        }).start();

    }

    public void getUserDetail(User item) {

    }
}
