import java.util.Set;

public class SerratedDisc{
    private int size;
    private int numberOfPins;
    private static String COMPNYNAME;

    public void setSize(int size){
        this.size = size;
    }

    public void setNumberOfPins(int numberOfPins){
        this.numberOfPins = numberOfPins;
    }

    public int getSize(){
        return size;
    }
    public int getNumberOfPins(){
        return numberOfPins;
    }

    public static void setCOMPNYNAME(String name){
        COMPNYNAME = name;
    }
    public static String getCOMPNYNAME(){
        return COMPNYNAME;
    }

    public SerratedDisc(int size, int numberOfPins){
       this.size = size;
       this.numberOfPins = numberOfPins;

    }
    public void spin(){

        System.out.println("The Disc is spining");
    }
}