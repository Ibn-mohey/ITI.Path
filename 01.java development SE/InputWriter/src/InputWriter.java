import java.io.*;

public class InputWriter {

    public static void run() throws IOException{

        //InputStreamReader r = new InputStreamReader(System.in);
        BufferedReader readder = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writter = new BufferedWriter(new FileWriter("./output.txt",true));
        String name = "";


        while (!name.equals("stop")){
                System.out.println("enter the data:");
                writter.append(name + "\n");
            name = readder.readLine();
            }

            readder.close();
            writter.close();


    }
}
