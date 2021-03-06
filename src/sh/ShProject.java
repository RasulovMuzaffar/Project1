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
    double b_shp = 1.35; //полуширина шпалы, принимается 13 см для железобетонных шпал;

    //Пункт 1.2
    double b0; //размер зоны основной площадки, воспринимающей внешнее давление, м;
    //Пункт 1.2

    //Пункт 1.3
    double a; //расчетная ширина обочины, м;
    double b_pl; //ширина основной площадки земляного полотна,    
    double b = 1.35; //расстояние от бровки основной площадки до оси ближайшего пути
    //Пункт 1.3

    //Пункт 1.4
    double h_nas; // высота насыпи
    double m = 1;
    double alfa1; //угол заложения насыпи.
    //Пункт 1.4

    //Пункт 1.5
    double q_pr; //Величина нагрузки
    double y;//координаты рассматриваемой точки в осях Y
    double Fia;
    //Пункт 1.5

    //Пункт 1.6
    double Azy; //Величина нагрузки
    double n;
    double z; //координаты рассматриваемой точки в осях Z
    double delta1_0;
    double delta3; //коэффициент, учитывающий влияние откоса на затухание колебаний в откосной части насыпи;
    double Fiy;
    double Fihi_j;
    double hi_j; //высота откоса над рассматриваемой точкой, м;
    double delta1; // коэффициент затухания колебаний в вертикальном направлении;
    double delta2_1; //коэффициент затухания колебаний в горизонтальном направлении в первой зоне;
    double delta2_2; //коэффициент затухания колебаний в горизонтальном направлении во второй зоне;
    //Пункт 1.6

    //Пункт 1.7
    double Cdin; //удельное сцепление грунта, воспринимающего вибродинамическую нагрузку Azy;
    double Fidin; //угол внутреннего трения грунта, воспринимающего вибродинамическую нагрузку Azy;
    double Kc_1; //коэффициенты характеризующие чувствительность грунта к вибродинамическому воздействию;
    double Kfi_1; //коэффициенты характеризующие чувствительность грунта к вибродинамическому воздействию;
    double Kc; //показатели относительного снижения удельного сцепления и угла внутреннего трения под влиянием вибродинамического воздействия;
    double Kfi; //показатели относительного снижения удельного сцепления и угла внутреннего трения под влиянием вибродинамического воздействия;
    double Cdin_min; //минимальные значения прочностных характеристик, определяемые при действии максимальной возможной вибродинамической нагрузки A0;
    double Cst; //прочностные характеристики при действии статической нагрузки;
    double Fidin_min; //минимальные значения прочностных характеристик, определяемые при действии максимальной возможной вибродинамической нагрузки A0;
    double Fist; //прочностные характеристики при действии статической нагрузки;
    double K; //коэффициент виброразрушения грунта;
    //Пункт 1.7

    //Глава 3
    double sigma; //напряжения
    double gamma; //Объемный вес грунта на откосе;
    double delta;
    double C;
    double Fi;
    double sigma_z;
    double sigma_y;
    double sigma_pr;
    int i;
    double delta_10i_0;
    double sigma_10i_0;
    double delta_10_0;
    //Глава 3
    
    //Глава 4
    double dz1sem;
    double dz2sem;
    double dy;
    double mju;
    //Глава 4

    ////////////////////////////////////////////////////
    ////////////для сетки    
    ////////////////////////////////////////////////////
    public static void main(String[] args) {

        Calc calc = new Calc();

        ShProject shp = new ShProject();
//        System.out.println(shp.A0(3, 4));
//        System.out.println(Math.toDegrees(Math.PI/4));

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
            sigma_bsr = Q_shp / l_shp * b_shp;
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
        alfa1 = Math.atan(1 / m); //получаем угол в радиансах
        alfa = Math.atan(h_nas / (a + h_nas / Math.tan(alfa1)));
        return alfa;
    }
    /////////////////end главы 1.4

////////////глава 1.5 Учет пригрузки, действующей на расчетную поверхность откоса
    double q_pr() {
        q_pr = gamma * Fia;
        return q_pr;
    }

    double Fia() {
        if (y <= a) {
            Fia = y * Math.tan(alfa);
        } else if (a < y && y <= a + h_nas / Math.tan(alfa1)) {
            Fia = y * (Math.tan(alfa) - Math.tan(alfa1)) + a * Math.tan(alfa1);
        } else if (y > a + h_nas / Math.tan(alfa1)) {
            Fia = 0;
        }
        return Fia;
    }
    /////////////////end главы 1.5

////////////глава 1.6 Выбродинамические характеристики грунтов
    double Azy() {
        Azy = A0 * Math.exp(n * z - delta1_0 * Fiy + delta3 * Fihi_j);
        return Azy;
    }

    double n() {
        if ("G".equals(tipG)) {
            n = Math.log10(delta1);
        } else if ("P".equals(tipG)) {
            n = Math.log(delta1);
        }
        return n;
    }

    double delta1_0() {
        delta1_0 = delta2_1 + delta2_2;
        return delta1_0;
    }

    double Fiy() {
        if (Math.abs(y + 0.5 * b0) <= 1.35) {
            Fiy = 0;
        } else {
            Fiy = Math.abs(y + 0.5 * b0) - 1.35;
        }
        return Fiy;
    }

    double Fihi_j() {
        if (y <= a) {
            Fihi_j = 0;
        } else {
            Fihi_j = (y - a) * Math.tan(alfa1);
        }
        return Fihi_j;
    }

    double delta3() {
        delta3 = 0.667 * Math.abs(n) * Math.tan(alfa1);
        return delta3;
    }
    /////////////////end главы 1.6

    ////////////глава 1.7 Прочностные характеристики грунтов земляного полотна
    double Cdin() {
        Cdin = Kc_1 + Kc * Math.exp(n * z - delta1_0 * (Math.abs(y + 0.5 * b0) - 1.35) + delta3 * Fihi_j);
        return Cdin;
    }

    double Fidin() {
        Fidin = Kfi_1 + Kfi * Math.exp(n * z - delta1_0 * (Math.abs(y + 0.5 * b0) - 1.35) + delta3 * Fihi_j);
        return Fidin;
    }

    double Kc_1() {
        Kc_1 = Cdin_min / Cst;
        return Kc_1;
    }

    double Kfi_1() {
        Kfi_1 = Fidin_min / Fist;
        return Kfi_1;
    }

    double Kc() {
        Kc = (Cst - Cdin_min) / Cst;
        return Kc;
    }

    double Kfi() {
        Kfi = (Fist - Fidin_min) / Fist;
        return Kfi;
    }
    /////////////////end главы 1.7

    ////////////глава 2 Условия построения сетки линий скольжения
    double ugol1sem1obl() {
//        return Math.toDegrees(Math.PI / 4 - Fidin / 2); //Градусы
        return Math.PI / 4 - Fidin / 2; //Радиансы
    }

    double ugol2sem1obl() {
//        return Math.toDegrees(Math.PI / 4 + Fidin / 2); //Градусы
        return Math.PI / 4 + Fidin / 2; //Радиансы
    }

    double ugol1sem3obl() {
//        return Math.toDegrees(Math.PI / 4 - Fidin / 2); //Градусы
        return Math.PI / 4 + Fidin / 2; //Радиансы
    }

    double ugol2sem3obl() {
//        return Math.toDegrees(Math.PI / 4 + Fidin / 2); //Градусы
        return Math.PI / 4 - Fidin / 2; //Радиансы
    }

    /////////////////end главы 2
    ////////////глава 3 Граничные условния
    double sigma() {
        if (C == 0) {
            sigma = gamma * Fia * Math.cos(alfa) + Cdin * Math.cos(2 * (delta - alfa));
        } else {
            sigma = (gamma * Fia * Math.cos(alfa) + Cdin * Math.cos(Fidin) * Math.cos(2 * (delta - alfa)))
                    / (1 - Math.sin(Fidin) * Math.cos(2 * (delta - alfa)));
        }
        return sigma;
    }

    double delta() {
        if (Fi == 0) {
            delta = 1 / 2 * Math.asin(gamma * Fia * Math.sin(alfa) / Cdin) + alfa;
        } else {
            delta = 1 / 2 * Math.asin(gamma * Fia * Math.sin(alfa))
                    * (Cdin + gamma * Fia * Math.tan(Fidin) * Math.cos(alfa)
                    - Math.sqrt(Math.pow(Cdin + gamma * Fia * Math.cos(alfa) * Math.tan(Fidin), 2)
                            * Math.pow(Math.sin(Fidin), 2)
                            - Math.pow(gamma * Fia * Math.sin(alfa) * Math.sin(Fidin), 2))
                    / (Math.pow(gamma * Fia * Math.sin(alfa) * Math.tan(Fidin), 2)
                    + Math.pow(gamma * Fia * Math.cos(alfa) * Math.tan(Fidin) + Cdin, 2)) * Math.cos(Fidin))
                    + alfa;
        }
        return delta;
    }

    double sigma_z() {
        if (delta == Math.PI / 2) {
            sigma_z = sigma * (1 + Math.sin(Fidin)) + Cdin * Math.cos(Fidin);
        }
        return sigma_z;
    }

    double sigma_y() {
        if (delta == Math.PI / 2) {
            sigma_y = sigma * (1 - Math.sin(Fidin)) - Cdin * Math.cos(Fidin);
        }
        return sigma_y;
    }

//    double tau_zy() {
//        if (delta == Math.PI / 2) {
//            tau_zy = ;
//        }
//        return tau_zy;
//    }
    double sigma_pr() {
        if (C != 0 && Fi == 0) {
            sigma_pr = Cdin * (Math.PI - 2 * alfa + 1);
        } else {
            sigma_pr = (gamma * Fia * Math.cos(alfa) + Cdin * Math.cos(Fidin) * Math.cos(2 * (Math.PI / 2 + alfa)))
                    / (1 - Math.sin(Fidin) * Math.cos(2 * (Math.PI / 2 - alfa)))
                    * Math.exp(Math.PI * Math.tan(Fidin));
        }
        return sigma_pr;
    }

    double delta_10i_0() {
        delta_10i_0 = ((Math.PI - 2 * alfa) / 20) * i + alfa;
        return delta_10i_0;
    }

    double sigma_10i_0() {
        if (C != 0 && Fi == 0) {
            sigma_10i_0 = Cdin * (((Math.PI - 2 * alfa) / 10) * i + alfa);
        } else {
            sigma_10i_0 = ((gamma * Fia * Math.cos(alfa) + Cdin * Math.cos(Fidin) * Math.cos(2 * (delta_10_0 - alfa)))
                    / (1 - Math.sin(Fidin) * Math.cos(2 * (delta_10_0 - alfa))))
                    * Math.exp(2 * delta_10i_0 * Math.tan(Fidin));
        }
        return sigma_10i_0;
    }

    /////////////////end главы 3

        /////////////////глава 4 Расчет предельных напряжений
//    double dz1sem(){
//        dz1sem = dy*Math.tan(delta+mju);
//        return dz1sem;        
//    }
//
//    double dz2sem(){
//        dz2sem = dy*Math.tan(delta-mju);
//        return dz2sem;        
//    }
    
    
    /////////////////end главы 4

}
