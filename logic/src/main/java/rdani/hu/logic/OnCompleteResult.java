package rdani.hu.logic;

public interface OnCompleteResult<T> {

    void onSuccess(T result);

    void onError(String error);

}
