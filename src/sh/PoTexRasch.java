/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sh;

/**
 *
 * @author Muzaffar
 */
public class PoTexRasch {

    //По указанию Шавката
    static double z; //
    static double y; //

    //По указанию Шавката
    //Пункт 1.2
    static double b0; //размер зоны основной площадки, воспринимающей внешнее давление, м;
    static double l_shp = 2.75; //длина шпалы, см;
    static double b_shp = 1.35; //полуширина шпалы, принимается 13 см для железобетонных шпал; 
    static double h_b = 0.4; //толщина балласта под шпалой, м;
    //Пункт 1.2

    //Пункт 1.3
    static double a; //расчетная ширина обочины, м;
    static double b_pl; //ширина основной площадки земляного полотна,    
    static double b = 1.35; //расстояние от бровки основной площадки до оси ближайшего пути
    //Пункт 1.3

    //Пункт 1.4
    static double alfa; //коэффициент затухания напряжений в балластной призме;
    static double h_nas = 0.3; // высота насыпи
    static double m = 1;
    static double alfa1; //угол заложения насыпи.
    //Пункт 1.4

    ////////////По указанию Шавката
    private static void z(int j) {
        Model model = new Model();
        PoTexRasch p = new PoTexRasch();
        z = j * b0 / 10 * Math.sin(p.alfa());
        model.setZ(z);
//        return z;
    }

    private static void y(int j) {
        Model model = new Model();
        
        y = j * b0 / 10 * Math.cos(alfa);
        
        model.setY(y);
//        return z;
    }

    /////////////////end 
////////////глава 1.2 Ширина загружения земляного полотна
    double b0() {
        b0 = l_shp + 2 * h_b * Math.tan(Math.toRadians(30));
        return b0;
    }
    /////////////////end главы 1.2

////////////глава 1.3 Расчетная ширина обочины земляного полотна
    double a_1p() {
        a = 0.5 * (b_pl - b0);
        return a;
    }

    double a_Np() {
        //b=l_shp/2 (10 формула)
        a = b - 0.5 * b0;
        return a;
    }
    /////////////////end главы 1.3

////////////глава 1.4 Угол заложения расчетного откоса
    double alfa() {
        PoTexRasch p = new PoTexRasch();
        alfa1 = Math.atan(1 / m); //получаем угол в радиансах
        alfa = Math.atan(h_nas / (p.a_1p() + h_nas / Math.tan(alfa1)));
        return alfa;
    }
    /////////////////end главы 1.4

    public static void main(String[] args) {
//PoTexRasch p = new PoTexRasch();
        Model model = new Model();
        z(1);
        System.out.println("" + model.getZ());
    }
}
