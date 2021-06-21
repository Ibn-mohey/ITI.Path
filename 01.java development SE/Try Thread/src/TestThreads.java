package threadingpracticepkg;
public class TestThreads extends Thread {

        private String Name;
        public TestThreads(String Name)
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
                    sleep((int) Math.random() * 1000);
                }
                catch  (InterruptedException | IllegalMonitorStateException  e)
                {
                    e.printStackTrace();
                }

            }
        }


}
