package rdani.hu.logic;

public abstract class LazyInstance<T> {

    private volatile T instance;

    public T get() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance = createInstance();
                }
            }
        }
        return instance;
    }

    abstract T createInstance();

}
