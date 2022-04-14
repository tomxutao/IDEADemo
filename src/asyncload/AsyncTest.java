package asyncload;

import java.util.HashMap;
import java.util.concurrent.*;

public class AsyncTest {
    public static void main(String[] args) {

        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                LogTime.log(Thread.currentThread().getName() + ", runnable 执行开始");
                LogTime.log(Thread.currentThread().getName() + ", runnable 执行结束");
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                LogTime.log(Thread.currentThread().getName() + ", new Thread 执行开始");
                AsyncLoad.get().run(runnable);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                AsyncLoad.get().start(runnable);
                LogTime.log(Thread.currentThread().getName() + ", new Thread 执行结束");
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AsyncLoad.get().checkRun(runnable);


//        FutureTask<Boolean> futureTask1 = new FutureTask<Boolean>(new Callable<Boolean>() {
//            @Override
//            public Boolean call() throws Exception {
//                Thread.sleep(5000);
//                return true;
//            }
//        });
//        AsyncLoad.get().run(futureTask1);
//        System.out.println("futureTask1 开始");
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println(Thread.currentThread().getName() + " futureTask1 结果：`123");
//                    System.out.println(Thread.currentThread().getName() + " futureTask1 结果：" + futureTask1.get());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    System.out.println(Thread.currentThread().getName() + " futureTask1 结果：`123");
//
//                    if (!futureTask1.isDone() && futureTask1.cancel(false)) {
//                        System.out.println(Thread.currentThread().getName() + " futureTask1 结果：取消成功");
//                    }
//
//                    System.out.println(Thread.currentThread().getName() + " futureTask1 结果：" + futureTask1.get());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
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
            LogTime.log(Thread.currentThread().getName() + ", checkRun 执行开始");
            final ALRunnable alRunnable = runnableHashMap.get(runnable);
            if (alRunnable != null) {
                LogTime.log(Thread.currentThread().getName() + ", checkRun alRunnable");
                alRunnable.run();
            }
            LogTime.log(Thread.currentThread().getName() + ", checkRun 执行结束");

            try {
                LogTime.log(String.valueOf(alRunnable.get()));
                LogTime.log(Thread.currentThread().getName() + ", get 执行结束");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public static class ALRunnable extends FutureTask {

            public ALRunnable(Runnable runnable, Object result) {
                super(runnable, result);
            }

            @Override
            public void run() {
                LogTime.log(Thread.currentThread().getName() + ", ALRunnable.run 执行开始");
                super.run();
                LogTime.log(Thread.currentThread().getName() + ", ALRunnable.run 执行结束");
            }
        }
    }
}
