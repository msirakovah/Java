public class UnsynchronizedExample {

    static class PrintStringsThread implements Runnable {

        Thread thread;
        String str1, str2;

        PrintStringsThread(String str1, String str2) {
            this.str1 = str1;
            this.str2 = str2;
            thread = new Thread(this);
            thread.start();
        }

        public void run() {
            TwoStrings.print(str1, str2);
        }

    }

    static class TwoStrings {

        // This method is not synchronized
        static void print(String str1, String str2) {
            System.out.print(str1);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                //Empty catch
            }
            System.out.println(str2);
        }
    }

    public static void main(String[] args) {
        UnsynchronizedExample example = new UnsynchronizedExample();
        new PrintStringsThread("Hello ", "there.");
        new PrintStringsThread("How are ", "you?");
        new PrintStringsThread("Thank you ", "very much!");
    }

}
