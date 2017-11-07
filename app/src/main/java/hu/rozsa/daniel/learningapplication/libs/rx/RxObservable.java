package hu.rozsa.daniel.learningapplication.libs.rx;

import java.util.List;
import java.util.concurrent.TimeUnit;

import hu.rozsa.daniel.learningapplication.sixth.network.JsonSampleEntity;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class RxObservable {


    private PublishSubject<String> searchSubject = PublishSubject.create();

    public void queryUsers() {
        getIds()

                .map(new Function<String, JsonSampleEntity.User>() {
                    @Override
                    public JsonSampleEntity.User apply(String s) throws Exception {
                        JsonSampleEntity.User user = new JsonSampleEntity.User();
                        return user;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
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

        getIds()
                .flatMap(new Function<String, ObservableSource<JsonSampleEntity.User>>() {
                    @Override
                    public ObservableSource<JsonSampleEntity.User> apply(String s) throws Exception {
                        return Observable.just(new JsonSampleEntity.User());
                    }
                });

        Disposable disposable = getIds().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });
    }

    public void initSearch() {
        Observable<String> debounceObservable = searchSubject.debounce(300, TimeUnit.MILLISECONDS);
    }

    public void sendKeyEvent(String input) {
        searchSubject.onNext(input);
    }

    public Observable<Long> distinctNumbers(List<Long> numbers) {
        return Observable.fromIterable(numbers)
                .distinct();
    }

    public Observable<Long> getEvenNumbers(List<Long> numbers) {
        return Observable.fromIterable(numbers)
                .filter(new Predicate<Long>() {
                    @Override
                    public boolean test(Long number) throws Exception {
                        return number % 2 == 0;
                    }
                });
    }

    public Single<Long> getFirstNumber(List<Long> numbers) {
        return Observable.fromIterable(numbers)
                .first(0L);
    }

    public void getNumbersTill(List<Long> numbers) {
        Observable delay = Observable.timer(1, TimeUnit.MINUTES);
        Observable.fromIterable(numbers)
                .takeUntil(delay);
    }

    public Observable<Long> getFirsThreeElements(List<Long> numbers) {
        return Observable.fromIterable(numbers).take(3);
    }

    public Observable<String> combine() {
        Observable<Long> numberGenerator = Observable
                .interval(200, TimeUnit.MILLISECONDS)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long millies) throws Exception {
                        return millies / 200;
                    }
                });

        Observable<Character> charachters = Observable
                .interval(400, TimeUnit.MILLISECONDS)
                .map(new Function<Long, Character>() {
                    @Override
                    public Character apply(Long millies) throws Exception {
                        int offset = (int) (long) millies / 400 % 20;
                        return (char) ('A' + offset);
                    }
                });

        Observable.zip(numberGenerator, charachters, new BiFunction<Long, Character, String>() {
            @Override
            public String apply(Long aLong, Character character) throws Exception {
                return String.valueOf(aLong) + character;
            }
        });

        return Observable.combineLatest(numberGenerator, charachters, new BiFunction<Long, Character, String>() {
            @Override
            public String apply(Long aLong, Character character) throws Exception {
                return String.valueOf(aLong) + character;
            }
        });
    }


    Observable<Long> getMin(List<Long> numbers) {
        return Observable.fromIterable(numbers)
                .scan(new BiFunction<Long, Long, Long>() {
                    @Override
                    public Long apply(Long long1, Long long2) throws Exception {
                        return long1 < long2 ? long1 : long2;
                    }
                })
                .distinctUntilChanged();
    }

    private Observable<String> getIds() {
        return Observable.just("1", "42", "1123", "4");
    }
}
