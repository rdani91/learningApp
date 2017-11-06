package hu.rozsa.daniel.learningapplication.libs.rx;

import hu.rozsa.daniel.learningapplication.sixth.network.JsonSampleEntity;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class RxObservable {

    private Observable<String> getIds() {
        return Observable.just("1", "42", "1123", "4");
    }

    public void queryUsers() {
        getIds()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return s.length() > 2;
                    }
                })
                .map(new Function<String, JsonSampleEntity.User>() {
                    @Override
                    public JsonSampleEntity.User apply(String s) throws Exception {
                        JsonSampleEntity.User user = new JsonSampleEntity.User();
                        return user;
                    }
                })
                .subscribe(new Observer<JsonSampleEntity.User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonSampleEntity.User user) {

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
