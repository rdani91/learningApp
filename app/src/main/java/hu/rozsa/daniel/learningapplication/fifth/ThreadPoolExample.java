package hu.rozsa.daniel.learningapplication.fifth;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {

    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private BlockingDeque<Runnable> runnables = new LinkedBlockingDeque<>();

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(NUMBER_OF_CORES*2, NUMBER_OF_CORES*2, 60L, TimeUnit.SECONDS, runnables);


    public void executeNext(Runnable runnable){
        executor.execute(runnable);
    }

}
