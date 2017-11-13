package hu.rozsa.daniel.learningapplication.architect.mvp.model;

public class Injector {

    public static UserRepository injectUserTaskRepository() {
        return new FakeUserRepository();
    }
}
