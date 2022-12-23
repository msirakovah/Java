public class ProducerConsumer{

    public class CubbyHole {
        private int contents;
        private boolean available = false;

        public int get() {
            available = false;
            return contents;
        }

        public void put(int value) {
            contents = value;
            available = true;
        }
    }

    public class Producer extends Thread {
        private CubbyHole cubbyhole;
        private int number;

        public Producer(CubbyHole c, int number) {
            super();
            cubbyhole = c;
            this.number = number;
        }

        public void run() {
            for (int i = 0; i < 10; i++) {
                cubbyhole.put(i);
                System.out.println("Producer #" + this.number
                        + " put: " + i);
                try {
                    sleep((int)(Math.random() * 100));
                } catch (InterruptedException e) {
                    //Empty catch
                }
            }
        }
    }

    public class Consumer extends Thread {
        private CubbyHole cubbyhole;
        private int number;

        public Consumer(CubbyHole c, int number) {
            super();
            cubbyhole = c;
            this.number = number;
        }

        public void run() {
            int value = 0;
            for (int i = 0; i < 10; i++) {
                value = cubbyhole.get();
                System.out.println("Consumer #" + this.number
                        + " got: " + value);
            }
        }
    }

    public static void main(String[] args) {
        CubbyHole c = new ProducerConsumer().new CubbyHole();
        Producer p1 = new ProducerConsumer().new Producer(c, 1);
        Consumer c1 = new ProducerConsumer().new Consumer(c, 1);

        p1.start();
        c1.start();

    }
}