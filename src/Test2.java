public class Test2 {

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        test2.start();
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                x();
            }
        }).start();
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                y();
            }
        }).start();

        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("k() " + k);
    }

    private final Object monitor = new Object();
    private int k = 0;

    public void x() {
        synchronized (monitor) {
            System.out.println("x()");
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            k = 1;
        }
    }

    public void y() {
        synchronized (monitor) {
            System.out.println("y()");
            monitor.notify();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("y() " + k);
        }
    }

}
