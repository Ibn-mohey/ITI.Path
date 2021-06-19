import java.util.ArrayList;
import java.util.List;



public class Player extends Person {


    private int playerID;
    private int numMedal;

    private Coach coach;
    private Team team;
    private List<Medal> medalIDs = new ArrayList<Medal>();



    public Player(String name, String address, int playerID) {
        super(name, address);

        this.playerID = playerID;

    }


    public void assignCoach(Coach coach) {
        this.coach = coach;
    }

    public void assignTeam(Team team) {
        this.team = team;
    }

    public void addMedal(Medal medal) {
        this.medalIDs.add(medal);
        this.numMedal = this.medalIDs.size();

    }

    // didnt asked for but i think it's needed
    public int getNumMedal() {
        this.numMedal = this.medalIDs.size();
        return numMedal;
    }
    public int getPlayerTeam(){
        return this.team.getTeamID();
    }
}
