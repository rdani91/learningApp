package hu.rozsa.daniel.learningapplication.architect.mvp.auth;

import android.os.Handler;
import android.os.Looper;

import java.util.List;

import hu.rozsa.daniel.learningapplication.architect.mvp.model.UserRepository;
import hu.rozsa.daniel.learningapplication.architect.mvp.model.entity.UserDetail;
import hu.rozsa.daniel.learningapplication.sixth.network.JsonSampleEntity;

public class DefaultAuthPresenter implements AuthenticationContract.Presenter {

    private final AuthenticationContract.View view;
    private final UserRepository              userRepository;

    public DefaultAuthPresenter(AuthenticationContract.View view, UserRepository userRepository) {
        this.view = view;
        this.userRepository = userRepository;
    }

    @Override
    public void queryUsers() {

        view.showLoading();

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<JsonSampleEntity.User> users = userRepository.getUsers();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        view.hideLoading();
                        if (users == null) {
                            view.showError("Error at getting users");
                        } else {
                            view.showUsers(users);
                        }
                    }
                });
            }
        }).start();
    }

    public void onUserSelected(final JsonSampleEntity.User selectedUser) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final UserDetail userDetail = userRepository.getUserDetail(selectedUser);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        view.proceedToDetailScreen(userDetail);
                    }
                });
            }
        }).start();
    }
}
