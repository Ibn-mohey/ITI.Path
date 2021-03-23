public class MainClass {
    public static void main(String[] args) {
        SerratedDisc myCarDisc = new SerratedDisc(5, 3);
        myCarDisc.spin();
        SerratedDisc.setCOMPNYNAME("namecompny");
        String x = SerratedDisc.getCOMPNYNAME();
        System.out.println(x);

    }

}
