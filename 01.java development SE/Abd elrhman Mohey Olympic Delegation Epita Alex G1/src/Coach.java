public class Coach extends Person {

    private int coachID;

    public Coach(String name, String address, int coachID) {
        super(name, address);

        this.coachID = coachID;
    }
}
