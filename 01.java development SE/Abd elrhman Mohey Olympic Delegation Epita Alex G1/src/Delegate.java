import java.util.ArrayList;
import java.util.List;


public class Delegate {


    public String country;
    private List<Player> players = new ArrayList<Player>();
    private List<Coach> coaches = new ArrayList<Coach>();
    private int numMedals;


    public Delegate(String country){

        this.country = country;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void addcoach(Coach caoch) {
        this.coaches.add(caoch);
    }

    public int getNumPlayers() {
        return this.players.size();
    }

    // extra
    public int getNumCoaches() {
        return this.coaches.size();
    }

    public int getNumMedals(){

        this.numMedals = 0;
        for(Player player :players){
            this.numMedals += player.getNumMedal();
        }
        return numMedals;
    }

}
