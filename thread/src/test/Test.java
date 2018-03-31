import java.util.concurrent.locks.Lock;

/**
 * Created by Nathy on 2018/2/8.
 */
public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
           Thread thread=new Thread(new MyThread(i,i));
            thread.start();

        }
    }


    public static class MyThread implements Runnable {
        private Integer o;
        private Integer obj;

        public MyThread(Integer o,Integer obj) {
            this.o = o;
            this.obj=obj;
        }

        public void run() {
            say(o,obj);
        }
    }

    private static Integer lock=0;

    public static void say(Integer o,Integer obj) {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + "=======" + o);
        }
    }

}
