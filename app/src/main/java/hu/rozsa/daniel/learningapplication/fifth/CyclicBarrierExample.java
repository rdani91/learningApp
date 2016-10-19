package hu.rozsa.daniel.learningapplication.fifth;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    private Runnable barrier1Action = new Runnable() {
        public void run() {
            System.out.println("BarrierAction 1 executed ");
        }
    };
    private Runnable barrier2Action = new Runnable() {
        public void run() {
            System.out.println("BarrierAction 2 executed ");
        }
    };

    private CyclicBarrier barrier1 = new CyclicBarrier(2, barrier1Action);
    private CyclicBarrier barrier2 = new CyclicBarrier(2, barrier2Action);

    private CyclicBarrierRunnable barrierRunnable1 = new CyclicBarrierRunnable(barrier1, barrier2);

    private CyclicBarrierRunnable barrierRunnable2 = new CyclicBarrierRunnable(barrier1, barrier2);

    public void example() {
        new Thread(barrierRunnable1).start();
        new Thread(barrierRunnable2).start();
    }

    private class CyclicBarrierRunnable implements Runnable {

        CyclicBarrier barrier1 = null;
        CyclicBarrier barrier2 = null;

        CyclicBarrierRunnable(
                CyclicBarrier barrier1,
                CyclicBarrier barrier2) {

            this.barrier1 = barrier1;
            this.barrier2 = barrier2;
        }

        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread()
                                         .getName() +
                                           " waiting at barrier 1");
                this.barrier1.await();

                Thread.sleep(1000);
                System.out.println(Thread.currentThread()
                                         .getName() +
                                           " waiting at barrier 2");
                this.barrier2.await();

                System.out.println(Thread.currentThread()
                                         .getName() +
                                           " done!");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
