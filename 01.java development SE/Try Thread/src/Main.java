
public class Main {

    public static void main(String[] args) {



	// write your code here



        threadingpracticepkg.TestThreads Thread = new threadingpracticepkg.TestThreads("Thread OBJ");


        threadingpracticepkg.TestRunnable Runnable = new threadingpracticepkg.TestRunnable("Runnable OBJ");
        Thread RunnableThread = new Thread(Runnable);

        RunnableThread.start();
        Thread.start();
        new Thread(new threadingpracticepkg.TestRunnable("Runnable One")).start();
    }
}
