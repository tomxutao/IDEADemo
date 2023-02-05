import com.sun.istack.internal.NotNull;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMulThread {

    public static void main(String[] args) {
        QAdLimitThreadManager manager = new QAdLimitThreadManager("AA", 3);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    System.out.println("tomy thread.start " + finalI);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            };
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            manager.execute(r);
        }
    }

    public static class QAdLimitThreadManager {

        ExecutorService executorService = Executors.newFixedThreadPool(500);

        private String TAG = "QAdLimitThreadManager";

        //场景名称，可为空，用以标识业务场景
        private String mSceneName;
        //最大执行的并发线程数
        private int mMaxThreadCount = 5;
        //正在运行的线程数量
        private int mRunningCount = 0;
        //需要执行的runnable队列
        private final ConcurrentLinkedQueue<Runnable> mTaskQueue = new ConcurrentLinkedQueue<>();

        private final Byte[] mLock = new Byte[0];

        public QAdLimitThreadManager(int mMaxThreadCount) {
            this.mMaxThreadCount = mMaxThreadCount;
        }

        public QAdLimitThreadManager(String sceneName, int mMaxThreadCount) {
            this.mSceneName = sceneName;
            this.mMaxThreadCount = mMaxThreadCount;
            this.TAG += "_" + mSceneName;
        }

        public void execute(final Runnable runnable) {
            Runnable task = () -> {
                runnable.run();
                onTaskFinish();
            };
            offerTask(task);
            checkExecute();
        }

        private void offerTask(@NotNull Runnable task) {
            mTaskQueue.offer(task);
        }

        private void checkExecute() {
            synchronized (mLock) {
                QAdLog.d(TAG, "checkExecute() before mRunningCount: " + mRunningCount);
                if (mRunningCount < mMaxThreadCount) {
                    Runnable task = mTaskQueue.poll();
                    if (task != null) {
                        executorService.execute(task);
                        mRunningCount++;
                        QAdLog.d(TAG, "checkExecute() after mRunningCount: " + mRunningCount);
                    }
                }
            }
        }

        private void onTaskFinish() {
            synchronized (mLock) {
                if (mRunningCount > 0) {
                    mRunningCount--;
                    QAdLog.d(TAG, "onTaskFinish() mRunningCount: " + mRunningCount);
                }
            }
            checkExecute();
        }

    }

    public static class QAdLog {
        private static long curTime = System.currentTimeMillis();

        public static void d(String tag, String msg) {
            System.out.println((System.currentTimeMillis() - curTime) + "  " + tag + ":  " + msg);
        }
    }
}
