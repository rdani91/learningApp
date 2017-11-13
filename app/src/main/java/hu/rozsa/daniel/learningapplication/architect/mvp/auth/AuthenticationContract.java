package hu.rozsa.daniel.learningapplication.architect.mvp.auth;

import java.util.List;

import hu.rozsa.daniel.learningapplication.architect.mvp.model.entity.UserDetail;
import hu.rozsa.daniel.learningapplication.sixth.network.JsonSampleEntity;

public interface AuthenticationContract {


    interface View {

        void showLoading();

        void hideLoading();

        void proceedToDetailScreen(UserDetail userDetail);

        void showError(String message);

        void showUsers(List<JsonSampleEntity.User> users);
    }

    interface Presenter {

        void queryUsers();

        void onUserSelected(JsonSampleEntity.User item);
    }
}
