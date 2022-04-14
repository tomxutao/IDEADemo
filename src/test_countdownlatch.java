import java.util.concurrent.CountDownLatch;


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
        Timer timer = new Timer(5);
        new Thread(timer).start();

        for (int athleteNo = 0; athleteNo < 5; athleteNo++) {
            new Thread(new Athlete(timer, "athlete" + athleteNo)).start();
        }
    }

    static class Timer implements Runnable {
        CountDownLatch countDownLatch;

        public Timer(int numOfAthlete) {
            this.countDownLatch = new CountDownLatch(numOfAthlete);
        }

        public void recordResult(String athleteName) {
            System.out.println(athleteName + " has arrived");
            countDownLatch.countDown();
            System.out.println("There are " + countDownLatch.getCount() + " athletes did not reach the end");
        }

        @Override
        public void run() {
            try {
                System.out.println("countDownLatch.await...");
                countDownLatch.await();
                System.out.println("All the athletes have arrived");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Athlete implements Runnable {
        Timer timer;
        String athleteName;

        public Athlete(Timer timer, String athleteName) {
            this.timer = timer;
            this.athleteName = athleteName;
        }

        @Override
        public void run() {
            try {
                long duration = (long) (Math.random() * 10);
                System.out.println(athleteName + " start running, sleep: " + duration * 1000);
                Thread.sleep(duration * 1000);
                timer.recordResult(athleteName);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
