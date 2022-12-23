public class ThreadGroupTest {

    public static void main (String[] args) {

        class SimpleThread extends Thread {

            public SimpleThread(String str) {
                super(str);
            }

            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        sleep((long)(Math.random() * 1000));
                    } catch (InterruptedException e) {
                        //Empty catch
                    }
                }
                System.out.format("DONE! %s in thread group %s%n", getName(), getThreadGroup().getName());
            }
        }

        // Start three threads first.  They should belong
        // to a same ThreadsGroup.
        new SimpleThread("Boston").start();
        new SimpleThread("New York").start();
        new SimpleThread("Seoul").start();

        // Get ThreadGroup of the current thread and display
        // the number of active threads that belong to the
        // ThreadGroup.
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        System.out.println("Number of active threads in this thread group = " + group.activeCount());

        // Display the name of the main thread
        Thread main = Thread.currentThread();
        System.out.println("Thread " + main.getName() + " in thread group " + main.getThreadGroup().getName());

        // Display the names of the threads in the current
        // ThreadGroup.
        Thread[] t_array = new Thread[10];
        int actualSize = group.enumerate(t_array);
        for (int i=0; i<actualSize;i++){
            System.out.println("Thread " + t_array[i].getName()
                    + " in thread group " + group.getName());
        }

    }
}




