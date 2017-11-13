package rdani.hu.logic.gateway;

import java.util.List;

import rdani.hu.logic.entity.User;
import rdani.hu.logic.entity.UserDetail;

public interface UserGateway {

    List<User> getUsers();

    UserDetail getUserDetail(User user);
}
