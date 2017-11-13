package hu.rozsa.daniel.learningapplication.architect.mvp.model;

import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;

import hu.rozsa.daniel.learningapplication.architect.mvp.model.entity.UserDetail;
import hu.rozsa.daniel.learningapplication.sixth.network.JsonSampleEntity;

public class FakeUserRepository implements UserRepository {
    @Override
    public List<JsonSampleEntity.User> getUsers() {
        SystemClock.sleep(1200);
        List<JsonSampleEntity.User> fakeList = new ArrayList<>();

        fakeList.add(new JsonSampleEntity.User("test", 2));
        fakeList.add(new JsonSampleEntity.User("test2", 5));
        fakeList.add(new JsonSampleEntity.User("test3", 13));
        fakeList.add(new JsonSampleEntity.User("test4", 25));

        return fakeList;
    }

    @Override
    public UserDetail getUserDetail(JsonSampleEntity.User selectedUser) {
        return new UserDetail();
    }
}
