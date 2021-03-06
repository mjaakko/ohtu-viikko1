package ohtuesimerkki;

import java.util.Collections;

public class Main {
    private static final int MAX_PLAYERS = 10;
    
    public static void main(String[] args) {
        Reader reader = new PlayerReader("http://nhlstatistics.herokuapp.com/players.txt");
        Statistics stats = new Statistics(reader);
        
        System.out.println("Philadelphia Flyers");
        for (Player player : stats.team("PHI") ) {
            System.out.println( player );
        }
        
        System.out.println("");
        
        System.out.println("Top scorers");
        for (Player player : stats.topScorers(MAX_PLAYERS) ) {
            System.out.println( player );
        }        
    }
}
