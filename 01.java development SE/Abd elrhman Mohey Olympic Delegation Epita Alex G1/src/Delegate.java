import java.util.ArrayList;
import java.util.List;


public class Delegate {


    public String country;
    private List<Player> players = new ArrayList<Player>();
    private List<Coach> coaches = new ArrayList<Coach>();
    private int numPlayers;
    private int numCoachs;
    private int numMedals;


    public Delegate(String country){

        this.country = country;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        this.numPlayers = this.players.size();
    }

    public void addcoach(Coach caoch) {
        this.coaches.add(caoch);
        this.numCoachs = this.coaches.size();
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    // extra
    public int getNumCoaches() {
        return numCoachs;
    }

    public int getNumMedals(){

        this.numMedals = 0;
        for(Player player :players){
            this.numMedals += player.getNumMedal();
        }
        return numMedals;
    }

}
