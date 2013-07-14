package gr.epp.thesis.BattleshipGame;

import java.util.Random;

/**
 *
 * @author tsoutsas.yiorgos & vigkos.ioannis
 * @project Thesis_Battleship.Game
 * @author Vidakis.Nikolas & Vellis Giorgos
 * @since
 */

/*
 * Proxiri Klasi.. gia tis voles tou antipalou.
 */
public class EnemyPlayer_Test {

    private int min = 0;
    private int max = 9;
    private int[][] hittenTargets = new int[100][2];
    Random rand = new Random();

    public EnemyPlayer_Test() {

        enemyFire();
    }

    /*
     * Proxiri methodos. Rixnei tis voles sto myBoard tyxaia.
     */
    public int[][] enemyFire() {
        int[][] enemyFire = new int[1][2];
        
        enemyFire[0][0] = rand.nextInt(max - min + 1) + min;
        enemyFire[0][1] = rand.nextInt(max - min + 1) + min;
        
        
        System.out.println("" + enemyFire[0][0] + enemyFire[0][1]);
        return enemyFire;
    }
}
