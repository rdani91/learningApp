package hu.rozsa.daniel.learningapplication.architect.mvp.model;

import java.util.List;

import hu.rozsa.daniel.learningapplication.architect.mvp.model.entity.UserDetail;
import hu.rozsa.daniel.learningapplication.sixth.network.JsonSampleEntity;

public interface UserRepository {

   List<JsonSampleEntity.User> getUsers();

   UserDetail getUserDetail(JsonSampleEntity.User selectedUser);
}
