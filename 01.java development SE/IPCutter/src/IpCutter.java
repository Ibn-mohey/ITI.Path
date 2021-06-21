import java.util.Arrays;
import java.util.Scanner;

public class IpCutter {

    private int[] splittedArray;

    public int[] stringToInteger(String[] strings) {

        int[] finalarr = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            finalarr[i] = Integer.parseInt(strings[i]);

        }
        return finalarr;
    }

    public int[] split() {

        Scanner sc= new Scanner(System.in);
        System.out.println("Enter your IP : ");
        String fullstring =  sc.nextLine();

        String[] splitsString = fullstring.split("\\.");

        int[] splitsInteger = stringToInteger(splitsString);

        return splitsInteger;
    }


    @Override
    public String toString() {
        splittedArray = split();
        return "IpCutter{" +
                "splittedArray=\n"
                + splittedArray[0] +'\n'
                + splittedArray[1] +'\n'
                + splittedArray[2] +'\n'
                + splittedArray[3] +'\n'
                +
                '}';
    }

}
