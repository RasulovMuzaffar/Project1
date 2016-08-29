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

    String tipG; //Тип грунта глинистый G или барханный P
    String tipP; //Тип поезда грузовой Gruz или пассажирский Pass

    //По указанию Шавката
    static double z; //
    static double y; //

    //По указанию Шавката
    //Пункт 1.1
    double A0 = 190; //Результирующая амплитуда колебаний грунтов основной площадки;
    double Az; //Амплитуда вертикальных колебаний грунтов основной площадки;
    double Ay; //Амплитуда горизонтальных колебаний грунтов основной площадки;
    double sigma_bsr; //средние напряжения, передающиеся от шпалы на балласт, Мпа;
    double mju_0; //коэффициент Пуассона земляного полотна;
    double E; //модуль упругости грунта земляного полотна, Мпа;
    double v; //скорость движения поездов, км/ч;
    double Q_shp; //нагрузка от рельса на шпалу;
//Пункт 1.1

    //Пункт 1.2
    static double b0; //размер зоны основной площадки, воспринимающей внешнее давление, м;
    static double l_shp = 2.75; //длина шпалы, см;
    static double b_shp = 1.35; //полуширина шпалы, принимается 13 см для железобетонных шпал; 
    static double h_b = 0.4; //толщина балласта под шпалой, м;
    //Пункт 1.2

    //Пункт 1.3
    static double a = 0.85; //расчетная ширина обочины, м;
    static double b_pl = 3; //ширина основной площадки земляного полотна,    
    static double b = 1.35; //расстояние от бровки основной площадки до оси ближайшего пути
    //Пункт 1.3

    //Пункт 1.4
    static double alfa; //коэффициент затухания напряжений в балластной призме;
    static double h_nas = 0.3; // высота насыпи
    static double m = 1;
    static double alfa1; //угол заложения насыпи.
    //Пункт 1.4

    //Пункт 1.6
    double Azy; //Величина нагрузки
    double n;
//    double z; //координаты рассматриваемой точки в осях Z
    double delta1_0;
    double delta3; //коэффициент, учитывающий влияние откоса на затухание колебаний в откосной части насыпи;
    double Fiy;
    double Fihi_j;
    double hi_j; //высота откоса над рассматриваемой точкой, м;
    double delta1 = 0.22; // коэффициент затухания колебаний в вертикальном направлении;
    double delta2_1 = 0.090; //коэффициент затухания колебаний в горизонтальном направлении в первой зоне;
    double delta2_2 = 0.005; //коэффициент затухания колебаний в горизонтальном направлении во второй зоне;
    //Пункт 1.6

    //Пункт 1.7
    static double Cdin; //удельное сцепление грунта, воспринимающего вибродинамическую нагрузку Azy;
    static double Fidin; //угол внутреннего трения грунта, воспринимающего вибродинамическую нагрузку Azy;
    double Kc_1; //коэффициенты характеризующие чувствительность грунта к вибродинамическому воздействию;
    double Kfi_1; //коэффициенты характеризующие чувствительность грунта к вибродинамическому воздействию;
    double Kc = 0.2; //показатели относительного снижения удельного сцепления и угла внутреннего трения под влиянием вибродинамического воздействия;
    double Kfi = 0.1; //показатели относительного снижения удельного сцепления и угла внутреннего трения под влиянием вибродинамического воздействия;
    double Cdin_min; //минимальные значения прочностных характеристик, определяемые при действии максимальной возможной вибродинамической нагрузки A0;
    double Cst = 0.5; //прочностные характеристики при действии статической нагрузки;
    double Fidin_min; //минимальные значения прочностных характеристик, определяемые при действии максимальной возможной вибродинамической нагрузки A0;
    double Fist = 38; //прочностные характеристики при действии статической нагрузки;
    double K; //коэффициент виброразрушения грунта;
    //Пункт 1.7

    //Глава 3
    static double sigma; //напряжения
    double gamma = 1.85; //Объемный вес грунта на откосе;
    static double delta;
    double Fia;
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
    static double mju;
//Глава 4
    static ModelIJ[][] ij = new ModelIJ[1][10];
    public static ModelZY[][] zyM = new ModelZY[11][31];

    ////////////По указанию Шавката
    public static void ZY(int i, int j) {
        PoTexRasch p = new PoTexRasch();
        ModelZY zy = new ModelZY();

        if (i + j == 10) {
            z = j * p.b0() / 10 * Math.sin(p.alfa());
            y = j * p.b0() / 10 * Math.cos(p.alfa());

//        p.sigma(z, y);
//        p.delta(z, y);
            sigma = p.sigma(z, y);
            delta = p.delta(z, y);
            zy.setZ(z);
            zy.setY(y);
            zy.setSigma(sigma);
            zy.setDelta(delta);
            zy.setCdin(Cdin);
            zy.setFidin(Fidin);
            zyM[i][j] = zy;
//        } else if (i == 10 && j == 30) {
//            zy.setZ(30);
//            zy.setY(30);
//            zy.setSigma(30);
//            zy.setDelta(30);
//            zy.setCdin(30);
//            zy.setFidin(30);
//            zyM[i][j] = zy;
        } else {
            zy.setZ(0);
            zy.setY(0);
            zy.setSigma(0);
            zy.setDelta(0);
            zy.setCdin(0);
            zy.setFidin(0);
            zyM[i][j] = zy;
        }
    }

    /////////////////end 
    ////////////глава 1.2 Ширина загружения земляного полотна
    //----(8)----
    double b0() {
        b0 = l_shp + 2 * h_b * Math.tan(Math.toRadians(30));
//        System.out.println("b0 " + b0);
        return b0;
    }
    /////////////////end главы 1.2

////////////глава 1.3 Расчетная ширина обочины земляного полотна
    //----(9)----
    double a_1p() {
        PoTexRasch p = new PoTexRasch();
        a = 0.5 * (b_pl - p.b0());
//        System.out.println("a " + a);
        return a;
    }

    //----(10)----
    double a_Np() {
        PoTexRasch p = new PoTexRasch();
        //b=l_shp/2 (10 формула)
        a = b - 0.5 * p.b0();
        return a;
    }
    /////////////////end главы 1.3

////////////глава 1.4 Угол заложения расчетного откоса
    //----(11)----
    double alfa() {
        PoTexRasch p = new PoTexRasch();
        alfa1 = Math.atan(1 / m); //получаем угол в радиансах
        alfa = Math.atan(h_nas / (p.a_1p() + h_nas / Math.tan(alfa1)));
//        System.out.println("alfa " + alfa1 + " " + alfa);
        return alfa;
    }
    /////////////////end главы 1.4

    ////////////глава 3 Граничные условния
    //----(25)----
    double sigma(double z, double y) {
        PoTexRasch p = new PoTexRasch();
        Fia = p.Fia(y);
        alfa = p.alfa();
        Cdin = p.Cdin(z, y);
        Fidin = p.Fidin(z, y);
        delta = p.delta(z, y);
        sigma = (gamma * Fia * Math.cos(alfa) + Cdin * Math.cos(Fidin) * Math.cos(2 * (delta - alfa)))
                / (1 - Math.sin(Fidin) * Math.cos(2 * (delta - alfa)));
//        System.out.println("alfa " + alfa);
//        System.out.println("a.alfa() " + p.alfa());
        return sigma;
    }

    //----(26)----
    double delta(double z, double y) {
        PoTexRasch p = new PoTexRasch();
        Fia = p.Fia(y);
        alfa = p.alfa();
        Cdin = p.Cdin(z, y);
        Fidin = p.Fidin(z, y);
        delta = 1 / 2 * Math.asin(gamma * Fia * Math.sin(alfa))
                * (Cdin + gamma * Fia * Math.tan(Fidin) * Math.cos(alfa)
                - Math.sqrt(Math.pow(Cdin + gamma * Fia * Math.cos(alfa) * Math.tan(Fidin), 2)
                        * Math.pow(Math.sin(Fidin), 2)
                        - Math.pow(gamma * Fia * Math.sin(alfa) * Math.sin(Fidin), 2))
                / (Math.pow(gamma * Fia * Math.sin(alfa) * Math.tan(Fidin), 2)
                + Math.pow(gamma * Fia * Math.cos(alfa) * Math.tan(Fidin) + Cdin, 2)) * Math.cos(Fidin))
                + alfa;
        return delta;
    }

    /////////////////end главы 3
    ////////////глава 1.5 Учет пригрузки, действующей на расчетную поверхность откоса
    //----(13)----
    double Fia(double y) {
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

    ////////////глава 1.7 Прочностные характеристики грунтов земляного полотна
//----(19)----
    double Cdin(double z, double y) {
        PoTexRasch p = new PoTexRasch();
//        System.out.println("Kc --> " + Kc);
//        System.out.println("z - y -->> " + z + " - " + y);
        Cdin = p.Kc_1() + Kc * Math.exp(p.n("G") * z - p.delta1_0() * (Math.abs(y + 0.5 * p.b0()) - 1.35) + p.delta3(p.n("G")) * p.Fihi_j(y));
//        System.out.println("Cdin ------------->>> " + Cdin);
        return Cdin;
    }

//----(20)----
    double Fidin(double z, double y) {
        PoTexRasch p = new PoTexRasch();
        Fidin = p.Kfi_1() + Kfi * Math.exp(p.n("G") * z - delta1_0 * (Math.abs(y + 0.5 * p.b0()) - 1.35) + delta3 * p.Fihi_j(y));
//        System.out.println("Fidin ------------->>> " + Fidin);
        return Fidin;
    }

//----(2111)----
    double Kc_1() {
        Kc_1 = 1 - Kc;
//        System.out.println("Kc_1 --> " + Kc_1);
        return Kc_1;
    }

//----(2211)----
    double Kfi_1() {
        Kfi_1 = 1 - Kfi;
        return Kfi_1;
    }

    /////////////////end главы 1.7
    ////////////глава 1.6 Выбродинамические характеристики грунтов
    double Azy() {
        Azy = A0 * Math.exp(n * z - delta1_0 * Fiy + delta3 * Fihi_j);
        return Azy;
    }

    double n(String tipG) {
        if ("G".equals(tipG)) {
            n = Math.log10(delta1);
        } else if ("P".equals(tipG)) {
            n = Math.log(delta1);
        }
//        System.out.println("n --> " + n);
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

    double Fihi_j(double y) {
        if (y <= a) {
            Fihi_j = 0;
        } else {
            Fihi_j = (y - a) * Math.tan(alfa1);
        }
        return Fihi_j;
    }

    double delta3(double n) {
        delta3 = 0.667 * Math.abs(n) * Math.tan(alfa1);
        return delta3;
    }
    /////////////////end главы 1.6

    ////////////Глава 4
    double mju() {
        mju = 0.25 * Math.PI - 0.5 * Fidin;
        return mju;
    }

    /////////////////end главы 4
    ////////////глава 1.1 Характеристики колебального процесса грунтов земляного полотна
    double Ay(double Az) {
        if ("G".equals(tipG)) {
            if ("Pass".equals(tipP)) {
                if (50 <= v && v <= 180) {
                    Ay = Az - 48 - 0.42 * (v - 50);
                }
            } else if ("Gruz".equals(tipP)) {
                if (35 <= v && v <= 115) {
                    Ay = Az - 41 - 0.3 * (v - 35);
                }
            }
        }
        if ("P".equals(tipG)) {
            if ("Pass".equals(tipP)) {
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

    /////////////////end главы 1.1
//    public static void main(String[] args) {
//        PoTexRasch p = new PoTexRasch();
//
////        for (int i = 9; i >= 0; i--) {
////            for (int j = 0; j <= 10; j++) {
//        for (int i = 0; i < zyM.length; i++) {
//            for (int j = 0; j < zyM[i].length; j++) {
////                int i=1;
//                if (i + j == 10) {
//                    ZY(i, j);
////                    System.out.println("ZYMZ " + i + ":" + j + " -- " + zyM[i][j].getZ());
////                    System.out.println("ZYMY " + i + ":" + j + " -- " + zyM[i][j].getY());
////                    System.out.println("ZYMS " + i + ":" + j + " -- " + zyM[i][j].getSigma());
////                    System.out.println("ZYMD " + i + ":" + j + " -- " + zyM[i][j].getDelta());
////                    System.out.println("ZYMCd " + i + ":" + j + " -- " + zyM[i][j].getCdin());
////                    System.out.println("ZYMFd " + i + ":" + j + " -- " + zyM[i][j].getFidin());
////                    p.mju();
////                    System.out.println("z " + zyM[i][j].getZ() + " : y " + zyM[i][j].getY());
////                    System.out.println("Cdin " + p.Cdin(zyM[i][j].getZ(), zyM[i][j].getY()));
//                } else {
//                    ModelZY zy = new ModelZY();
//
//                    zy.setZ(0);
//                    zy.setY(0);
//                    zy.setSigma(0);
//                    zy.setDelta(0);
//                    zy.setCdin(0);
//                    zy.setFidin(0);
//                    zyM[i][j] = zy;
//                }
//            }
//        }
////        zyM[8][2].setDelta(0.001);
//
//        for (int i = 0; i < zyM.length; i++) {
////            System.out.println(zyM[i].length);
//            for (int j = 0; j < zyM[i].length; j++) {
//                System.out.print(i + ":" + j + " " + zyM[i][j].toString());
//            }
//            System.out.println("");
//        }
//    }
}
