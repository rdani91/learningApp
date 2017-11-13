package hu.rozsa.daniel.learningapplication.architect.clean.gateway;

import java.util.List;

import rdani.hu.logic.entity.User;
import rdani.hu.logic.entity.UserDetail;
import rdani.hu.logic.gateway.UserGateway;

public class FakeUserGateway implements UserGateway {
    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public UserDetail getUserDetail(User user) {
        return null;
    }
}
