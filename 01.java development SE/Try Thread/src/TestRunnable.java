
package threadingpracticepkg;
public class TestRunnable  implements Runnable {

    private String Name;
    public TestRunnable(String Name)
    {
        this.Name = Name;
    }
    @Override
    public void run()
    {
        for(int i =1;i < 15; i++)
        {
            System.out.println(i + " ,Obj. Name : " + Name);
            try
            {

                Thread.sleep((int)Math.random() * 1000);
            }
            catch  (InterruptedException | IllegalMonitorStateException  e)
            {
                e.printStackTrace();
            }

        }

    }


}
