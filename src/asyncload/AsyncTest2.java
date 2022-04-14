package asyncload;

import java.util.HashMap;
import java.util.concurrent.*;

public class AsyncTest2 {
    public static void main(String[] args) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                LogTime.log(Thread.currentThread().getName() + " runnable 执行开始");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogTime.log(Thread.currentThread().getName() + " runnable 执行结束");
            }
        };

        AsyncLoad.get().run(runnable);

        AsyncLoad.get().start(runnable);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AsyncLoad.get().checkRun(runnable);


    }




    public static class AsyncLoad {

        ThreadPoolExecutor executor;
        private HashMap<Runnable, ALRunnable> runnableHashMap;

        public AsyncLoad() {
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
            static final AsyncLoad holder = new AsyncLoad();
        }

        public static AsyncLoad get() {
            return Holder.holder;
        }

        public void run(FutureTask futureTask) {
            executor.submit(futureTask);
        }



        public void run(Runnable runnable) {
            final ALRunnable alRunnable = new ALRunnable(runnable, null);
            runnableHashMap.put(runnable, alRunnable);
        }

        public void start(Runnable runnable) {
            final ALRunnable alRunnable = runnableHashMap.get(runnable);
            if (alRunnable != null) {
                executor.execute(alRunnable);
            }
        }

        public void checkRun(Runnable runnable) {
            LogTime.log(Thread.currentThread().getName() + " checkRun 执行开始");
            final ALRunnable alRunnable = runnableHashMap.get(runnable);
            if (alRunnable != null) {
                LogTime.log(Thread.currentThread().getName() + " checkRun alRunnable");
                alRunnable.run();
                LogTime.log(Thread.currentThread().getName() + " checkRun true");
            }
            LogTime.log(Thread.currentThread().getName() + " checkRun 执行结束");

            try {
                LogTime.log(String.valueOf(alRunnable.get()));
                LogTime.log(Thread.currentThread().getName() + " get 执行结束");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        public static class ALRunnable extends FutureTask {

            public ALRunnable(Runnable runnable, Object result) {
                super(runnable, result);
            }
        }
    }
}
