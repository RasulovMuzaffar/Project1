/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sh;

import sh.raschet.Calc;

/**
 *
 * @author Muzaffar
 */
public class ShProject {

    /**
     * @param args the command line arguments
     */
    String tipG; //Тип грунта глинистый G или барханный P
    String tipP; //Тип поезда грузовой Gruz или пассажирский Pass

    double A0; //Результирующая амплитуда колебаний грунтов основной площадки;
    double Az; //Амплитуда вертикальных колебаний грунтов основной площадки;
    double Ay; //Амплитуда горизонтальных колебаний грунтов основной площадки;
    double sigma_bsr; //средние напряжения, передающиеся от шпалы на балласт, Мпа;
    double mju_0; //коэффициент Пуассона земляного полотна;
    double E; //модуль упругости грунта земляного полотна, Мпа;
    double alfa; //коэффициент затухания напряжений в балластной призме;
    double h_b; //толщина балласта под шпалой, м;
    double v; //скорость движения поездов, км/ч;
    double Q_shp; //нагрузка от рельса на шпалу;
    double l_shp; //длина шпалы, см;
    double b; //полуширина шпалы, принимается 13 см для железобетонных шпал;

    //Пункт 1.2
    double b0;
    //Пункт 1.2

    ////////////////////////////////////////////////////
    ////////////для сетки    
    ////////////////////////////////////////////////////
    double sigma; //напряжения
    double gamma; //Объемный вес грунта на откосе;
    double Fia;
    double C_din;
    double Fi_din;
    double delta;

    public static void main(String[] args) {

        Calc calc = new Calc();

        ShProject shp = new ShProject();
        System.out.println(shp.A0(3, 4));
    }
////////////глава 1.1 Характеристики колебального процесса грунтов земляного полотна

    double Ay(double Az) {
        if (tipG == "G") {
            if (tipP == "Pass") {
                if (50 <= v && v <= 180) {
                    Ay = Az - 48 - 0.42 * (v - 50);
                }
            } else if (tipP == "Gruz") {
                if (35 <= v && v <= 115) {
                    Ay = Az - 41 - 0.3 * (v - 35);
                }
            }
        }
        if (tipG == "P") {
            if (tipP == "Pass") {
                if (65 <= v && v <= 90) {
                    Ay = Az - 138.48 - 0.22 * (v - 65);
                }
            }
            sigma_bsr = Q_shp / l_shp * b;
            if (40 <= v && v <= 180) {
                alfa = 1.55 + 0.008 * (v - 40);
            }
//            else if (tipP == "Gruz") {
//                if (35 <= v && v <= 115) {
//                    Ay = Az - 48 - 0.42 * (v - 50);
//                }
//            }
        }
        return Az;
    }

    double Az() {
        Az = 160.9 * sigma_bsr * ((1 - Math.pow(mju_0, 2)) / E) * Math.exp(-alfa * (h_b - 0.25));
        return Az;
    }

    double A0(double Az, double Ay) {
        A0 = Math.sqrt(Math.pow(Az, 2) + Math.pow(Ay, 2));
        return A0;
    }

    /////////////////end главы 1.1
////////////глава 1.2 Ширина загружения земляного полотна
    double b0() {
        b0 = l_shp + 2 * h_b * Math.tan(Math.toRadians(30));
        return b0;
    }
    /////////////////end главы 1.2
}
