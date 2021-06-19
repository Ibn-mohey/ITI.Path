public class Main {

    public static void main(String[] args) {
	// write your code here
        // Coach
        Coach elbadry = new Coach("hossam eelbadry", "not hossam elbadry house", 1);

        System.out.println("Coach 1 address is " + elbadry.getAddress());
        System.out.println("Coach 1 name is " + elbadry.getName());

        System.out.println("change name and address  ");

        elbadry.setNameAdd("hossam elbadry", "hossam elbadry house");

        System.out.println("after change");
        System.out.println("Coach 1 address is " + elbadry.getAddress());
        System.out.println("Coach 1 name is " + elbadry.getName());



        Coach gharieb = new Coach("not shawky gharieb", "not shawky gharieb house", 2);


        System.out.println("Coach 2 address is " + gharieb.getAddress());
        System.out.println("Coach 2 name is " + gharieb.getName());

        System.out.println("change name and address  ");

        gharieb.setNameAdd("shawky gharieb", "shawky gharieb house");

        System.out.println("after change");
        System.out.println("Coach 2 address is " + gharieb.getAddress());
        System.out.println("Coach 2 name is " + gharieb.getName());

        // teams

        Team dreamTeam = new Team( 1);
        Team notdreamTeam = new Team( 2);

        // medals
        Medal medal1 = new Medal( 1);
        Medal medal2 = new Medal( 2);
        Medal medal3= new Medal( 3);
        Medal medal4 = new Medal( 4 );
        Medal medal5 = new Medal( 5);

        // players
        Player salah  = new Player("mo salah",  "london u lucky man", 2);

        System.out.println("player 2 address is " + salah.getAddress());
        System.out.println("player 2 name is " + salah.getName());
        salah.assignCoach(elbadry);
        salah.assignTeam( notdreamTeam);
        salah.addMedal(medal1);
        salah.addMedal(medal2);

        System.out.println("salah medal's are " + salah.getNumMedal());

        Player mohey  = new Player( "it's me mohey",  "fooo2",  1);

        System.out.println("player 1 address is " + mohey.getAddress());
        System.out.println("player 1 name is " + mohey.getName());



        mohey.assignCoach( elbadry);
        mohey.assignTeam( dreamTeam);

        mohey.setNameAdd( "still Mohey",  "foo2 awy");

        System.out.println("after change");
        System.out.println("player 1 address is " + mohey.getAddress());
        System.out.println("player 1 name is " + mohey.getName());


        mohey.addMedal(medal3);
        mohey.addMedal(medal4);
        mohey.addMedal(medal5);

        System.out.println("Mohey medal's are " + mohey.getNumMedal());


        // Delegate

        Delegate Egypt  = new Delegate("Egypt");
        Egypt.addPlayer(salah);
        Egypt.addcoach(elbadry);
        Egypt.addPlayer(mohey);
        System.out.println("NO of egpyt players are " + Egypt.getNumPlayers());

        // extra
        System.out.println("player 1 No of coaches are  " + Egypt.getNumCoaches());
        System.out.println("Egypty medal's are " + Egypt.getNumMedals());

        System.out.println("player 1 "+ mohey.getName() + " teamID is " + mohey.getPlayerTeam());
    }
}
