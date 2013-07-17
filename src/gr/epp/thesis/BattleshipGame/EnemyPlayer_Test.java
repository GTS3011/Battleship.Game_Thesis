package gr.epp.thesis.BattleshipGame;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since Spring Semester 2013
 * @inst. Applied Informatics and Multimedia - TEI of Crete
 */

/*
 * Proxiri Klasi.. gia tis tuxaies voles tou antipalou. 
 * About to be DELETED!!!
 */
public class EnemyPlayer_Test {

    private int min = 0;
    private int max = 99;
    private int cnt = 0;
    private int enemyFire = 0;
    private boolean fired = false;
    private int[] hittenTargets = new int[100];
    Random rand = new Random();

    public EnemyPlayer_Test() {
    }

    /*
     * Proxiri methodos!!
     * Rixnei tis voles sti myBoard tyxaia, kai sigratei sti mnimi pou exei 
     * xtipisei idi..!! O Pinakas hittenTargets periexei tis idi dokimasmenes
     * voles tou antipaloy
     */
    public int enemyFire() {
        if (!Arrays.asList(hittenTargets).contains(enemyFire)) {
            enemyFire = rand.nextInt(max - min + 1) + min;
            hittenTargets[cnt] = enemyFire;
            cnt++;
        } else {
            System.out.println("SFALMA!");
        }
        System.out.println("Enemy Firing @ " + enemyFire);
        System.out.println("\nReport: ");
        for (int i = 0; i < cnt; i++) {
            System.out.println("" + hittenTargets[i]);
        }
        fired = true;
        return enemyFire;
    }

    public boolean isFired() {
        return fired;
    }
}
