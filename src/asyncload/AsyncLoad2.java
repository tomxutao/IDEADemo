package asyncload;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncLoad2 {

    ThreadPoolExecutor executor;
    private HashMap<Runnable, ALRunnable> runnableHashMap;

    private void checkInit() {
        if (executor == null) {
            int corePoolSize = Math.max(4, Runtime.getRuntime().availableProcessors());
            executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<Runnable>(5));
        }
        if (runnableHashMap == null) {
            runnableHashMap = new HashMap<>();
        }
    }

    private static class Holder {
        static final AsyncLoad2 holder = new AsyncLoad2();
    }

    public static AsyncLoad2 get() {
        return Holder.holder;
    }


    public void run(Runnable runnable) {
        checkInit();
        final ALRunnable alRunnable = new ALRunnable(runnable);
        runnableHashMap.put(runnable, alRunnable);
        executor.execute(alRunnable);
    }

    public void checkRun(Runnable runnable) {
        checkInit();
        final ALRunnable alRunnable = runnableHashMap.get(runnable);
        if (alRunnable == null) {
            return;
        }
        if (alRunnable.loadEnd) {
            return;
        }
        alRunnable.run();
        System.out.println("结束");
    }


    public static class ALRunnable implements Runnable {

        private volatile boolean loadStart;
        private volatile boolean loadEnd;

        private Runnable runnable;

        public ALRunnable(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override
        public void run() {
            synchronized (ALRunnable.class) {
                System.out.println("threadname: " + Thread.currentThread().getName() + ", loadStart: " + loadStart);
                if (loadStart) {
                    return;
                }
                loadStart = true;
                if (runnable != null) {

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runnable.run();
                }
                loadEnd = true;
            }
        }
    }
}

