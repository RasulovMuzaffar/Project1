/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sh;
import sh.PoTexRasch;

/**
 *
 * @author Muzaffar
 */
public class Korrect {

    public Korrect() {
    }

    public static void Korrect(double d1, double d2) {
//        double d1 = PoTexRasch.k_d0_10();
        PoTexRasch.zyM[0][10].setDelta(d1);
        System.out.println("====================");
//        double d2 = PoTexRasch.k_d1_9();
        PoTexRasch.zyM[1][9].setDelta(d2);
    }
    
}
