package hu.rozsa.daniel.learningapplication.sixth.network;

public class JsonSampleEntity {
    public String name;
    public User user;
    public int progress;


    public static class User {
        public String name;
        public int age;

        public Education education;

        public static class Education {
            public int level;
            public String language;
        }
    }

}
