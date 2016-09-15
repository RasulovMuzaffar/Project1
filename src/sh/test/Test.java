/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sh.test;

/**
 *
 * @author Muzaffar
 */
public class Test {

    public Test() {
    }

    double b_pl; //ширина основной площадки
    double h_n; //высота насыпи
    double h_b; //высота балласта
    double l_sh = 2.7; //длина шпалы
    double m; //уклон откоса

    double b0() {
        return l_sh + 2 * h_b * Math.tan(Math.toDegrees(30));
    }

    public static void main(String[] args) {
        double alfa1 = Math.atan(1 / 1);;
        double a1 = Math.atan(1);
        System.out.println("" + 2.9880742348215987);
        System.out.println("" + Math.toDegrees(2.9880742348215987));
        System.out.println("" + Math.tan(2.9880742348215987));
//        System.out.println("" + Math.toDegrees(Math.tan(a1)));
    }

}
