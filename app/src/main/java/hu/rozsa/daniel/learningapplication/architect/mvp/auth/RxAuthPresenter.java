package hu.rozsa.daniel.learningapplication.architect.mvp.auth;

import java.util.List;
import java.util.concurrent.Callable;

import hu.rozsa.daniel.learningapplication.architect.mvp.model.UserRepository;
import hu.rozsa.daniel.learningapplication.sixth.network.JsonSampleEntity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxAuthPresenter {
    private final AuthenticationContract.View view;
    private final UserRepository              userRepository;

    public RxAuthPresenter(AuthenticationContract.View view, UserRepository userRepository) {

        this.view = view;
        this.userRepository = userRepository;
    }

    public void onUserSelected(JsonSampleEntity.User item) {

    }

    public void queryUsers() {
        view.showLoading();

        Observable
                .fromCallable(new Callable<List<JsonSampleEntity.User>>() {
                    @Override
                    public List<JsonSampleEntity.User> call() throws Exception {

                        return userRepository.getUsers();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<JsonSampleEntity.User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<JsonSampleEntity.User> users) {
                        if (users == null) {
                            view.showError("Error at getting users");
                        } else {
                            view.showUsers(users);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
