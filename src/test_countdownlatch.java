import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class test_countdownlatch {


//    private static CountDownLatch startSignal = new CountDownLatch(1);
//    //用来表示裁判员需要维护的是6个运动员
//    private static CountDownLatch endSignal = new CountDownLatch(6);
//
//    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(6);
//        for (int i = 0; i < 6; i++) {
//            executorService.execute(() -> {
//                try {
//                    System.out.println(Thread.currentThread().getName() + " 运动员等待裁判员响哨！！！");
//                    startSignal.await();
//                    System.out.println(Thread.currentThread().getName() + "正在全力冲刺");
//                    endSignal.countDown();
//                    System.out.println(Thread.currentThread().getName() + "  到达终点");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//        System.out.println("裁判员发号施令啦！！！");
//        startSignal.countDown();
//        endSignal.await();
//        System.out.println("所有运动员到达终点，比赛结束！");
//        executorService.shutdown();
//    }











    public static void main(String[] args) {
//        Timer timer = new Timer(5);
//        new Thread(timer).start();
//
//        for (int athleteNo = 0; athleteNo < 5; athleteNo++) {
//            new Thread(new Athlete(timer, "athlete" + athleteNo)).start();
//        }
//

        CountDownLatch countDownLatch = new CountDownLatch(1);
        final String[] result = new String[1];

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setValue(result, "1");
                countDownLatch.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setValue(result, "2");
                countDownLatch.countDown();
            }
        }).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("result: " + result[0]);
    }

    private static synchronized void setValue(String[] result, String value) {
        System.out.println(value);
        if (result[0] == null) {
            result[0] = value;
        }
    }

}
