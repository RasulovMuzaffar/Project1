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
    static double old_d;
    static double new_d;
    //По указанию Шавката
    //Пункт 1.1
    double A0 = 442; //Результирующая амплитуда колебаний грунтов основной площадки;
    double Az; //Амплитуда вертикальных колебаний грунтов основной площадки;
    double Ay; //Амплитуда горизонтальных колебаний грунтов основной площадки;
    double sigma_bsr; //средние напряжения, передающиеся от шпалы на балласт, Мпа;
    double mju_0; //коэффициент Пуассона земляного полотна;
    static double E; //модуль упругости грунта земляного полотна, Мпа;
    double v; //скорость движения поездов, км/ч;
    double Q_shp; //нагрузка от рельса на шпалу;
//Пункт 1.1

    //Пункт 1.2
    static double b0; //размер зоны основной площадки, воспринимающей внешнее давление, м;
    static double l_shp = 2.75; //длина шпалы, см;
    static double b_shp = 1.35; //полуширина шпалы, принимается 13 см для железобетонных шпал; 
    static double h_b; //= 0.4толщина балласта под шпалой, м;
    //Пункт 1.2

    //Пункт 1.3
    static double a; //= 0.85расчетная ширина обочины, м;
    static double b_pl; //= 5 ширина основной площадки земляного полотна,    
    static double b = 1.35; //расстояние от бровки основной площадки до оси ближайшего пути
    //Пункт 1.3

    //Пункт 1.4
    static double alfa; //коэффициент затухания напряжений в балластной призме;
    static double h_nas; // = 0.3 высота насыпи
    static double m; // m=1
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
    double delta1 = 0.4988; // коэффициент затухания колебаний в вертикальном направлении;
    double delta2_1 = 0.207; //коэффициент затухания колебаний в горизонтальном направлении в первой зоне;
    double delta2_2 = 0.008; //коэффициент затухания колебаний в горизонтальном направлении во второй зоне;
    //Пункт 1.6

    //Пункт 1.7
    static double Cdin; //удельное сцепление грунта, воспринимающего вибродинамическую нагрузку Azy;
    static double Fidin; //угол внутреннего трения грунта, воспринимающего вибродинамическую нагрузку Azy;
    double Kc_1; //коэффициенты характеризующие чувствительность грунта к вибродинамическому воздействию;
    double Kfi_1; //коэффициенты характеризующие чувствительность грунта к вибродинамическому воздействию;
    double Kc = 0.134; //показатели относительного снижения удельного сцепления и угла внутреннего трения под влиянием вибродинамического воздействия;
    double Kfi = 0.226; //показатели относительного снижения удельного сцепления и угла внутреннего трения под влиянием вибродинамического воздействия;
    double Cdin_min; //минимальные значения прочностных характеристик, определяемые при действии максимальной возможной вибродинамической нагрузки A0;
    static double Cst; //= 0.5 прочностные характеристики при действии статической нагрузки;
    double Fidin_min; //минимальные значения прочностных характеристик, определяемые при действии максимальной возможной вибродинамической нагрузки A0;
    static double Fist; //= 38 прочностные характеристики при действии статической нагрузки;
    double K = 0.010; //коэффициент виброразрушения грунта;
    //Пункт 1.7

    //Глава 3
    static double sigma; //напряжения
    static double gamma; //= 1.85 Объемный вес грунта на откосе;
    static double gamma_b = 1.65; //= 1.65 Объемный вес балласта;
    static double delta;
    double Fia;
    double C;
    double Fi;
    double sigma_z;
    double sigma_y;
    double sigma_pr;
    int i;
    static double delta_10i_0;
    static double sigma_10i_0;
    double delta_10_0;
    //Глава 3
//Глава 4
    static double mju;
//Глава 4

    //ЗОНА 1
    static double z1z;
    static double z1y;
    static double Bij1;
    static double Bi1j;
    static double Dij1;
    static double Di1j;
    static double Fi1j;
    static double Fij1;
    static double Hij1;
    static double Hi1j;
    static double sigmaij;
    static double deltaij;

    static double z3z;
    static double z3y;
    //

    static ModelIJ[][] ij = new ModelIJ[1][10];
    public static ModelZY[][] zyM = new ModelZY[31][11];

    public PoTexRasch() {
    }

    public static void Peremennie(double _m, double _h_nas, double _b_pl, double _gamma, double _Cst, double _Fist, double _h_b, double _E) {
        m = _m;
        h_nas = _h_nas;
        b_pl = _b_pl;
        gamma = _gamma;
        Cst = _Cst;
        Fist = Math.toRadians(_Fist);
        h_b = _h_b;
        E = _E;
    }

//    ////////////глава 1.2 Ширина загружения земляного полотна
//    //----(8)----
    double b0() {
        b0 = l_shp + 2 * h_b * Math.tan(Math.toRadians(30));
//        System.out.println("---------------------------------------" + b0);
        return b0;
    }
//    /////////////////end главы 1.2
//
//////////////глава 1.3 Расчетная ширина обочины земляного полотна
//    //----(9)----

    double a_1p() {
        a = 0.5 * (b_pl - b0);
//        System.out.println("---------------------------------------" + a);
        return a;
    }
//
//    //----(10)----
//    double a_Np() {
//        a = b - 0.5 * this.b0();
//        return a;
//    }
//    /////////////////end главы 1.3
//
//////////////глава 1.4 Угол заложения расчетного откоса
//    //----(11)----

    public static double B0() {
        PoTexRasch p = new PoTexRasch();
        return p.b0();
    }

    public static double alf() {
        PoTexRasch p = new PoTexRasch();
        return p.alfa();
    }

    double alfa() {
        alfa1 = Math.atan(1 / m); //получаем угол в радиансах
        alfa = Math.atan(h_nas / (this.a_1p() + h_nas / Math.tan(alfa1)));
//        alfa = Math.atan(h_nas * Math.tan(alfa1) / (this.a_1p() * Math.tan(alfa1) + h_nas));
//        System.out.println("alfa1 - > " + Math.toDegrees(alfa1));
//        System.out.println("alfa - > " + Math.toDegrees(alfa));
        return alfa;
    }
//    /////////////////end главы 1.4
//
//    ////////////глава 3 Граничные условния
//    //----(25)----

    double sigma(double z, double y) {
        Fia = this.Fia(y);
        alfa = this.alfa();
        Cdin = this.Cdin(z, y);
        Fidin = this.Fidin(z, y);
        delta = this.delta(z, y);
        sigma = (gamma * Fia * Math.cos(alfa) + Cdin * Math.cos(Fidin) * Math.cos(2 * (delta - alfa)))
                / (1 - Math.sin(Fidin) * Math.cos(2 * (delta - alfa)));

//        sigma = (Fia * Math.cos(alfa) + Cdin * Math.cos(Fidin) * Math.cos(2 * (delta - alfa)))
//                / (1 - Math.sin(Fidin) * Math.cos(2 * (delta - alfa)));
        return sigma;
    }
//
//    //----(26)----

    double delta(double z, double y) {
        Fia = this.Fia(y);
        alfa = this.alfa();
        Cdin = this.Cdin(z, y);
        Fidin = this.Fidin(z, y);
        double A1, A2;

        A1 = Cdin + gamma * Fia * Math.tan(Fidin) * Math.cos(alfa)
                - Math.sqrt(Math.pow(Cdin + gamma * Fia * Math.cos(alfa) * Math.tan(Fidin), 2)
                        * Math.pow(Math.sin(Fidin), 2)
                        - Math.pow(gamma * Fia * Math.sin(alfa) * Math.sin(Fidin), 2));
        A2 = (Math.pow(gamma * Fia * Math.sin(alfa) * Math.tan(Fidin), 2)
                + Math.pow(gamma * Fia * Math.cos(alfa) * Math.tan(Fidin) + Cdin, 2)) * Math.cos(Fidin);
        delta = 0.5 * Math.asin(gamma * Fia * Math.sin(alfa) * (A1 / A2)) + alfa;
//        delta = 0.5 * Math.asin(Fia * Math.sin(alfa)
//                * (Cdin + Fia * Math.tan(Fidin) * Math.cos(alfa)
//                - Math.sqrt(Math.pow(Cdin + Fia * Math.cos(alfa) * Math.tan(Fidin), 2)
//                        * Math.pow(Math.sin(Fidin), 2)
//                        - Math.pow(Fia * Math.sin(alfa) * Math.sin(Fidin), 2)))
//                / (Math.cos(Fidin) * (Math.pow(Fia * Math.sin(alfa) * Math.tan(Fidin), 2)
//                + Math.pow(Cdin + Fia * Math.cos(alfa) * Math.tan(Fidin), 2))))
//                + alfa;
        return delta;
    }
//
//    /////////////////end главы 3
//    ////////////глава 1.5 Учет пригрузки, действующей на расчетную поверхность откоса

    double alfa2() {
        a = this.a_1p();
        return Math.atan(h_b / a);
    }

//    //----(13)----
    double Fia(double y) {
        a = this.a_1p();
//        if (y <= a) {
//            Fia = y * Math.tan(alfa);
//        } else if (a < y && y <= a + h_nas / Math.tan(alfa1)) {
//            Fia = y * (Math.tan(alfa) - Math.tan(alfa1)) + a * Math.tan(alfa1);
//        } else if (y > a + h_nas / Math.tan(alfa1)) {
//            Fia = 0;
//        }

        if (y > a + h_nas / Math.tan(alfa1)) {
            Fia = 0;
        } else if (a < y && y <= a + h_nas / Math.tan(alfa1)) {
            Fia = gamma * y * (Math.tan(alfa) - Math.tan(alfa1)) + gamma * a * Math.tan(alfa);
        } else if (y <= a) {
            Fia = gamma_b * (h_b - y * Math.tan(this.alfa2())) + gamma * a * Math.tan(alfa);
        }
//        System.out.println("Fia ------>>>> y ---> " + y + " ----- " + Fia);
        return Fia;
    }
//    /////////////////end главы 1.5
//
//    ////////////глава 1.7 Прочностные характеристики грунтов земляного полотна
////----(19)----

    double Cdin(double z, double y) {
        Kc_1 = this.Kc_1();
        n = this.n("P");
        b0 = this.b0();
        delta1_0 = this.delta1_0();
        Fihi_j = this.Fihi_j(y);
        delta3 = this.delta3(n);
//
        Cdin = Kc_1 + Kc * Math.exp(n * z - delta1_0
                * (Math.abs(y + 0.5 * b0) - 1.35) + delta3 * Fihi_j);

//        Cdin = Cst * (Kc_1 + Kc * Math.exp(n * z - delta1_0
//                * (Math.abs(y + 0.5 * b0) - 1.35) + delta3 * Fihi_j));
        return Cdin;
    }
//
////----(20)----

    double Fidin(double z, double y) {
        Kfi_1 = this.Kfi_1();
        n = this.n("P");
        delta1_0 = this.delta1_0();
        b0 = this.b0();
        Fihi_j = this.Fihi_j(y);
        delta3 = this.delta3(n);

        Fidin = Kfi_1 + Kfi * Math.exp(n * z - delta1_0
                * (Math.abs(y + 0.5 * b0) - 1.35) + delta3 * Fihi_j);
//        Fidin = Fist * (Kfi_1 + Kfi * Math.exp(n * z - delta1_0
//                * (Math.abs(y + 0.5 * b0) - 1.35) + delta3 * Fihi_j));
        return Fidin;
    }
//
////----(2111)----

    double Kc_1() {
        Kc_1 = 1 - Kc;
        return Kc_1;
    }
//
////----(2211)----

    double Kfi_1() {
        Kfi_1 = 1 - Kfi;
        return Kfi_1;
    }
//
//    /////////////////end главы 1.7
//    ////////////глава 1.6 Выбродинамические характеристики грунтов
//    //----(14)----

    double Azy(int i, int j) {
        n = this.n("P");
        delta1_0 = this.delta1_0();

        Azy = A0 * Math.exp(n * zyM[i][j].getZ() - delta1_0
                * this.Fiy(zyM[i][j].getZ()) + this.delta3(this.n("P")) * this.Fihi_j(zyM[i][j].getZ()));
        return Azy;
    }
//

    double n(String tipG) {
        if ("G".equals(tipG)) {
            n = Math.log10(delta1);
        } else if ("P".equals(tipG)) {
            n = Math.log(delta1);
        }
        return n;
    }
//
////----(15)----

    double delta1_0() {
        delta1_0 = delta2_1 + delta2_2;
        return delta1_0;
    }
//
//    //----(16)----

    double Fiy(double y) {
        if (Math.abs(y + 0.5 * this.b0()) <= 1.35) {
            Fiy = 0;
        } else {
            Fiy = Math.abs(y + 0.5 * this.b0()) - 1.35;
        }

//        if (Math.abs(y - 0.5 * this.b0()) <= 1.35) {
//            Fiy = 0;
//        } else {
//            Fiy = Math.abs(y - 0.5 * this.b0()) - 1.35;
//        }
        return Fiy;
    }
//
//    //----(17)----

    double Fihi_j(double y) {
        a = this.a_1p();
        if (y <= a) {
            Fihi_j = 0;
        } else {
            Fihi_j = (y - a) * Math.tan(alfa1);
        }
        return Fihi_j;
    }
//
//    //----(18)----

    double delta3(double n) {
        delta3 = 0.667 * Math.abs(n) * Math.tan(alfa1);
        return delta3;
    }
//    /////////////////end главы 1.6
//
//    ////////////глава 1.1 Характеристики колебального процесса грунтов земляного полотна
////    double Ay(double Az) {
////        if ("G".equals(tipG)) {
////            if ("Pass".equals(tipP)) {
////                if (50 <= v && v <= 180) {
////                    Ay = Az - 48 - 0.42 * (v - 50);
////                }
////            } else if ("Gruz".equals(tipP)) {
////                if (35 <= v && v <= 115) {
////                    Ay = Az - 41 - 0.3 * (v - 35);
////                }
////            }
////        }
////        if ("P".equals(tipG)) {
////            if ("Pass".equals(tipP)) {
////                if (65 <= v && v <= 90) {
////                    Ay = Az - 138.48 - 0.22 * (v - 65);
////                }
////            }
////            sigma_bsr = Q_shp / l_shp * b_shp;
////            if (40 <= v && v <= 180) {
////                alfa = 1.55 + 0.008 * (v - 40);
////            }
////        }
////        return Ay;
////    }
////
////    double Az() {
////        Az = 160.9 * sigma_bsr * ((1 - Math.pow(mju_0, 2)) / E) * Math.exp(-alfa * (h_b - 0.25));
////        return Az;
////    }
//    /////////////////end главы 1.1
//    //НАЧАЛИ ПОСТРОИТЬ СЕТКУ ЗОНЫ 1
//    //----- 39 формула асосида z(i, j)

    double z1z(int i, int j) {
//        System.out.println("z1z - " + zyM[i][j - 1].getDelta());
        z1z = zyM[i][j - 1].getZ() - (zyM[i][j - 1].getY() - this.z1y(i, j))
                * Math.tan(zyM[i][j - 1].getDelta() + 0.25 * Math.PI - 0.5 * zyM[i][j - 1].getFidin());
        return z1z;
    }
//
//    //----- 39 ва 41 формулалар асосида y(i, j)

    double z1y(int i, int j) {
//        System.out.println("z1y - " + zyM[i - 1][j].getDelta());
        double A1, A2;
        A1 = zyM[i - 1][j].getZ() - zyM[i][j - 1].getZ()
                - zyM[i - 1][j].getY() * Math.tan(zyM[i - 1][j].getDelta() - 0.25 * Math.PI + 0.5 * zyM[i - 1][j].getFidin())
                + zyM[i][j - 1].getY() * Math.tan(zyM[i][j - 1].getDelta() + 0.25 * Math.PI - 0.5 * zyM[i][j - 1].getFidin());
        A2 = Math.tan(zyM[i][j - 1].getDelta() + 0.25 * Math.PI - 0.5 * zyM[i][j - 1].getFidin())
                - Math.tan(zyM[i - 1][j].getDelta() - 0.25 * Math.PI + 0.5 * zyM[i - 1][j].getFidin());
        z1y = A1 / A2;
//        System.out.println("==============zyM[i - 1][j].getFidin()================ [i-1:j]" + (i - 1) + ":" + j + "  " + zyM[i - 1][j].getFidin());
//        System.out.println("==============this.Fidin(zyM[i-1][j].getZ(), zyM[i-1][j].getY())================ [i-1:j]" + (i - 1) + ":" + j + "  " + this.Fidin(zyM[i - 1][j].getZ(), zyM[i - 1][j].getY()));
//        System.out.println("+++++++++++++++++");
//        System.out.println("==============zyM[i - 1][j].getCdin()================ [i-1:j] " + (i - 1) + ":" + j + "  " + zyM[i - 1][j].getCdin());
//        System.out.println("==============this.Cdin(zyM[i-1][j].getZ(), zyM[i-1][j].getY())================ [i-1:j] " + (i - 1) + ":" + j + "  " + this.Cdin(zyM[i - 1][j].getZ(), zyM[i - 1][j].getY()));
//        System.out.println("+++++++++++++++++");
//        System.out.println("+++++++++++++++++");
        return z1y;
    }
//

    double Fi1_hi(double y) {
        if (y <= 0.5 * b_pl) {
            return 0;
        } else {
            return Math.tan(alfa1);
        }
    }
//    //----- 49 формула Ф(i, j-1)

    double Fij1(int i, int j) {
        n = this.n("P");
        delta1_0 = this.delta1_0();
        b0 = this.b0();
//
        Fij1 = K * A0 * Math.exp(n * zyM[i][j - 1].getZ() - delta1_0
                * (zyM[i][j - 1].getY() + 0.5 * b0) + 1.35 * delta1_0
                + Math.abs(n) * 0.667 * this.Fihi_j(zyM[i][j - 1].getY()) * Math.tan(alfa1) - K * this.Azy(i, j - 1));

//        Fij1 = K * A0 * Math.exp(n * zyM[i][j - 1].getZ() - delta1_0
//                * (zyM[i][j - 1].getY() - 1.35) + Math.abs(n) * 0.667
//                * this.Fi1_hi(zyM[i][j - 1].getY()) * Math.tan(alfa1) - K * this.Azy(i, j - 1));
        return Fij1;
    }
//
//    //----- 49 формула Ф(i-1, j)

    double Fi1j(int i, int j) {
        n = this.n("P");
        delta1_0 = this.delta1_0();
        b0 = this.b0();
//
        Fi1j = K * A0 * Math.exp(n * zyM[i - 1][j].getZ() - delta1_0
                * (zyM[i - 1][j].getY() + 0.5 * b0) + 1.35 * delta1_0
                + Math.abs(n) * 0.667 * this.Fihi_j(zyM[i - 1][j].getY()) * Math.tan(alfa1) - K * this.Azy(i - 1, j));

//        Fij1 = K * A0 * Math.exp(n * zyM[i - 1][j].getZ() - delta1_0
//                * (zyM[i - 1][j].getY() - 1.35) + Math.abs(n) * 0.667
//                * this.Fi1_hi(zyM[i - 1][j].getY()) * Math.tan(alfa1) - K * this.Azy(i - 1, j));
        return Fi1j;
    }
//
//    //----- 50 формула B(i, j-1)

    double Hij1(int i, int j) {
        n = this.n("P");
        delta1_0 = this.delta1_0();
        b0 = this.b0();
//
        Hij1 = Math.exp(n * zyM[i][j - 1].getZ() - delta1_0
                * (zyM[i][j - 1].getY() + 0.5 * b0) + 1.35 * delta1_0
                + Math.abs(n) * 0.667 * this.Fihi_j(zyM[i][j - 1].getY()) * Math.tan(alfa1));

//        Hij1 = Math.exp(n * zyM[i][j - 1].getZ() - delta1_0
//                * (zyM[i][j - 1].getY() - 1.35)
//                + Math.abs(n) * 0.667 * this.Fi1_hi(zyM[i][j - 1].getY()) * Math.tan(alfa1));
        return Hij1;
    }
//
//    //----- 50 формула B(i-1, j)

    double Hi1j(int i, int j) {
        n = this.n("P");
        delta1_0 = this.delta1_0();
        b0 = this.b0();
//
        Hi1j = Math.exp(n * zyM[i - 1][j].getZ() - delta1_0
                * (zyM[i - 1][j].getY() + 0.5 * b0) + 1.35 * delta1_0
                + Math.abs(n) * 0.667 * this.Fihi_j(zyM[i - 1][j].getY()) * Math.tan(alfa1));

//        Hi1j = Math.exp(n * zyM[i - 1][j].getZ() - delta1_0
//                * (zyM[i - 1][j].getY() - 1.35)
//                + Math.abs(n) * 0.667 * this.Fi1_hi(zyM[i - 1][j].getY()) * Math.tan(alfa1));
        return Hi1j;
    }
//
//    //----- 47 формула B(i, j-1)

    double Bij1(int i, int j) {
        n = this.n("P");
        delta1_0 = this.delta1_0();
//
        Bij1 = 0.15 * gamma * this.Hij1(i, j) * A0 / 439 - this.Fij1(i, j)
                * ((delta1_0 - Math.abs(n) * 0.667 * this.Fihi_j(zyM[i][j - 1].getY())
                * Math.tan(alfa1)) * Math.sin(2 * zyM[i][j - 1].getDelta())
                + n * Math.cos(2 * zyM[i][j - 1].getDelta()))
                * (Fist * Kfi * (zyM[i][j - 1].getSigma() * Math.cos(zyM[i][j - 1].getFidin())
                - zyM[i][j - 1].getCdin() * Math.sin(zyM[i][j - 1].getFidin()))
                + Cst * Kc * Math.cos(zyM[i][j - 1].getFidin()));

//        Bij1 = 0.15 * gamma * this.Hij1(i, j) * A0 / 439 - this.Fij1(i, j)
//                * ((delta1_0 - Math.abs(n) * 0.667 * this.Fi1_hi(zyM[i][j - 1].getY())
//                * Math.tan(alfa1)) * Math.sin(2 * zyM[i][j - 1].getDelta())
//                + n * Math.cos(2 * zyM[i][j - 1].getDelta()))
//                * (Fist * Kfi * (zyM[i][j - 1].getSigma() * Math.cos(zyM[i][j - 1].getFidin())
//                - zyM[i][j - 1].getCdin() * Math.sin(zyM[i][j - 1].getFidin()))
//                + Cst * Kc * Math.cos(zyM[i][j - 1].getFidin()));
        return Bij1;
    }
//
//    //----- 47 формула B(i-1, j)

    double Bi1j(int i, int j) {
        n = this.n("P");
        delta1_0 = this.delta1_0();

        Bi1j = 0.15 * gamma * this.Hi1j(i, j) * A0 / 439 - this.Fi1j(i, j)
                * ((delta1_0 - Math.abs(n) * 0.667 * this.Fihi_j(zyM[i - 1][j].getY())
                * Math.tan(alfa1)) * Math.sin(2 * zyM[i - 1][j].getDelta())
                + n * Math.cos(2 * zyM[i - 1][j].getDelta()))
                * (Fist * Kfi * (zyM[i - 1][j].getSigma() * Math.cos(zyM[i - 1][j].getFidin())
                - zyM[i][j - 1].getCdin() * Math.sin(zyM[i - 1][j].getFidin()))
                + Cst * Kc * Math.cos(zyM[i - 1][j].getFidin()));
//        Bi1j = 0.15 * gamma * this.Hi1j(i, j) * A0 / 439 - this.Fi1j(i, j)
//                * ((delta1_0 - Math.abs(n) * 0.667 * this.Fi1_hi(zyM[i - 1][j].getY())
//                * Math.tan(alfa1)) * Math.sin(2 * zyM[i - 1][j].getDelta())
//                + n * Math.cos(2 * zyM[i - 1][j].getDelta()))
//                * (Fist * Kfi * (zyM[i - 1][j].getSigma() * Math.cos(zyM[i - 1][j].getFidin())
//                - zyM[i][j - 1].getCdin() * Math.sin(zyM[i - 1][j].getFidin()))
//                + Cst * Kc * Math.cos(zyM[i - 1][j].getFidin()));
        return Bi1j;
    }
//
//    //----- 48 формула D(i, j-1)

    double Dij1(int i, int j) {
        n = this.n("P");
        delta1_0 = this.delta1_0();

        Dij1 = 0.04 * gamma * this.Hij1(i, j) * A0 / 439 + this.Fij1(i, j)
                * (n * Math.sin(2 * zyM[i][j - 1].getDelta())
                - (delta1_0 - this.Fihi_j(zyM[i][j - 1].getY()) * Math.abs(n) * 0.667
                * Math.tan(alfa1)) * Math.cos(2 * zyM[i][j - 1].getDelta()))
                * (Fist * Kfi * (zyM[i][j - 1].getSigma() * Math.cos(zyM[i][j - 1].getFidin())
                - zyM[i][j - 1].getCdin() * Math.sin(zyM[i][j - 1].getFidin()))
                + Cst * Kc * Math.cos(zyM[i][j - 1].getFidin()));
//        Dij1 = 0.04 * gamma * this.Hij1(i, j) * A0 / 439 + this.Fij1(i, j)
//                * (n * Math.sin(2 * zyM[i][j - 1].getDelta())
//                - (delta1_0 - this.Fi1_hi(zyM[i][j - 1].getY()) * Math.abs(n) * 0.667
//                * Math.tan(alfa1)) * Math.cos(2 * zyM[i][j - 1].getDelta()) + n * Math.cos(2 * zyM[i][j - 1].getDelta()))
//                * (Fist * Kfi * (zyM[i][j - 1].getSigma() * Math.cos(zyM[i][j - 1].getFidin())
//                - zyM[i][j - 1].getCdin() * Math.sin(zyM[i][j - 1].getFidin()))
//                + Cst * Kc * Math.cos(zyM[i][j - 1].getFidin()));
        return Dij1;
    }
//
//    //----- 48 формула D(i-1, j)

    double Di1j(int i, int j) {
        n = this.n("P");
        delta1_0 = this.delta1_0();

        Di1j = 0.04 * gamma * this.Hi1j(i, j) * A0 / 439 + this.Fi1j(i, j)
                * (n * Math.sin(2 * zyM[i - 1][j].getDelta())
                - (delta1_0 - this.Fihi_j(zyM[i - 1][j].getY()) * Math.abs(n) * 0.667
                * Math.tan(alfa1)) * Math.cos(2 * zyM[i - 1][j].getDelta()))
                * (Fist * Kfi * (zyM[i - 1][j].getSigma() * Math.cos(zyM[i - 1][j].getFidin())
                - zyM[i - 1][j].getCdin() * Math.sin(zyM[i - 1][j].getFidin()))
                + Cst * Kc * Math.cos(zyM[i - 1][j].getFidin()));
//        Dij1 = 0.04 * gamma * this.Hij1(i, j) * A0 / 439 + this.Fij1(i, j)
//                * (n * Math.sin(2 * zyM[i - 1][j].getDelta())
//                - (delta1_0 - this.Fi1_hi(zyM[i - 1][j].getY()) * Math.abs(n) * 0.667
//                * Math.tan(alfa1)) * Math.cos(2 * zyM[i - 1][j].getDelta()) + n * Math.cos(2 * zyM[i - 1][j].getDelta()))
//                * (Fist * Kfi * (zyM[i - 1][j].getSigma() * Math.cos(zyM[i - 1][j].getFidin())
//                - zyM[i - 1][j].getCdin() * Math.sin(zyM[i - 1][j].getFidin()))
//                + Cst * Kc * Math.cos(zyM[i - 1][j].getFidin()));
        return Di1j;
    }
//
//    //----- 40 формулалар асосида топилди

    double sigmaij(int i, int j) {

        sigmaij = zyM[i][j - 1].getSigma() - this.Q(i, j)
                + 2 * (zyM[i][j - 1].getSigma() * Math.tan(zyM[i][j - 1].getFidin()) + zyM[i][j - 1].getCdin())
                * (zyM[i][j - 1].getDelta() - this.deltaij(i, j));
        return sigmaij;
    }
//
//    //----- 40 ва 42 формулалар асосида топилди

    double deltaij(int i, int j) {
//        System.out.println("i , j , d : " + i + " , " + j + " , " + zyM[i - 1][j].getDelta());
        deltaij = (this.W(i, j) - this.Q(i, j) - zyM[i - 1][j].getSigma() + zyM[i][j - 1].getSigma()
                + 2 * (zyM[i][j - 1].getSigma() * Math.tan(zyM[i][j - 1].getFidin()) * zyM[i][j - 1].getDelta()
                + zyM[i][j - 1].getCdin() * zyM[i][j - 1].getDelta()
                + zyM[i - 1][j].getSigma() * Math.tan(zyM[i - 1][j].getFidin()) * zyM[i - 1][j].getDelta()
                + zyM[i - 1][j].getCdin() * zyM[i - 1][j].getDelta()))
                / (2 * (zyM[i][j - 1].getSigma() * Math.tan(zyM[i][j - 1].getFidin()) + zyM[i][j - 1].getCdin()
                + zyM[i - 1][j].getSigma() * Math.tan(zyM[i - 1][j].getFidin()) + zyM[i - 1][j].getCdin()));
//        System.out.println("i , j , d : " + i + " , " + j + " , " + deltaij);
        return deltaij;
    }
//
//    //------ 40 тенгламанинг унг кисми

    double Q(int i, int j) {
        return (gamma + this.Bij1(i, j)) * ((zyM[i][j - 1].getZ() - z1z) + (zyM[i][j - 1].getY() - z1y) * Math.tan(zyM[i][j - 1].getFidin()))
                + this.Dij1(i, j) * ((zyM[i][j - 1].getY() - z1y) + (zyM[i][j - 1].getZ() - z1z) * Math.tan(zyM[i][j - 1].getFidin()));
//        return (gamma + this.Bij1(i, j)) * ((zyM[i][j - 1].getZ() - z1z) + (zyM[i][j - 1].getY() - this.z1y(i, j)) * Math.tan(zyM[i][j - 1].getFidin()))
//                + this.Dij1(i, j) * ((zyM[i][j - 1].getY() - z1y) + (zyM[i][j - 1].getZ() - this.z1z(i, j)) * Math.tan(zyM[i][j - 1].getFidin()));
    }
//
//    //------ 42 тенгламанинг унг кисми

    double W(int i, int j) {
        return (gamma + this.Bi1j(i, j)) * ((zyM[i - 1][j].getZ() - z1z) - (zyM[i - 1][j].getY() - z1y) * Math.tan(zyM[i - 1][j].getFidin()))
                + this.Di1j(i, j) * ((zyM[i - 1][j].getY() - z1y) + (zyM[i - 1][j].getZ() - z1z) * Math.tan(zyM[i - 1][j].getFidin()));

//        return (gamma + this.Bi1j(i, j)) * ((zyM[i - 1][j].getZ() - this.z1z(i, j)) - (zyM[i - 1][j].getY() - this.z1y(i, j)) * Math.tan(zyM[i - 1][j].getFidin()))
//                + this.Di1j(i, j) * ((zyM[i - 1][j].getY() - this.z1y(i, j)) + (zyM[i - 1][j].getZ() - this.z1z(i, j)) * Math.tan(zyM[i - 1][j].getFidin()));
    }

    //------ (34)
    double delta_10i_0(int i) {
//        System.out.println("delta_10i_0(int i) " + i);
        alfa = this.alfa();
        delta_10i_0 = ((Math.PI - 2 * alfa) / 20) * (i - 10) + alfa;
        return delta_10i_0;
    }

    //------ (35)
    double sigma_10i_0(int i) {
//        System.out.println("sigma_10i_0(int i) " + i);
        alfa = this.alfa();
        Cdin = this.Cdin(0, 0);
        Fidin = this.Fidin(0, 0);
//        double A1 = gamma * this.Fia(a) * Math.cos(alfa);
//        double A2 = Cdin * Math.cos(Fidin) * Math.cos(2 * (this.delta_10i_0(20) - alfa));
//        double A3 = 1 - Math.sin(Fidin) * Math.cos(2 * (this.delta_10i_0(20) - alfa));
//        double A4 = Math.exp(2 * this.delta_10i_0(i) * Math.tan(Fidin));
//        sigma_10i_0 = ((A1 + A2) / A3) * A4;

        sigma_10i_0 = 2 * Cdin * (this.delta_10i_0(i) - this.delta_10i_0(10))
                + (Cdin * Math.cos(Fidin) / (1 - Math.sin(Fidin)))
                * Math.exp(2 * (this.delta_10i_0(i) - this.delta_10i_0(10)) * Math.tan(Fidin));
        return sigma_10i_0;
    }

//Находим Правый край!! 3 области
    double z3y(int i, int j) {
        return (zyM[i - 1][j].getY() * Math.tan(zyM[i - 1][j].getDelta() - 0.25 * Math.PI + 0.5 * zyM[i - 1][j].getFidin()) + 0)
                / Math.tan(zyM[i - 1][j].getDelta() - 0.25 * Math.PI + 0.5 * zyM[i - 1][j].getFidin());
    }
//    //----- 49 формула Ф(i-1, j)

    double Fi1j3(int i, int j) {
        n = this.n("P");
        delta1_0 = this.delta1_0();
        b0 = this.b0();

        return K * A0 * Math.exp(n * zyM[i - 1][j].getZ() - delta1_0
                * (zyM[i - 1][j].getY() + 0.5 * b0) + 1.35 * delta1_0
                + Math.abs(n) * 0.667 * this.Fihi_j(zyM[i - 1][j].getY()) * Math.tan(alfa1) - K * this.Azy(i - 1, j));
    }
//

//    //----- 50 формула B(i-1, j)
    double Hi1j3(int i, int j) {
        n = this.n("P");
        delta1_0 = this.delta1_0();
        b0 = this.b0();
//

        return Math.exp(n * zyM[i - 1][j].getZ() - delta1_0
                * (zyM[i - 1][j].getY() + 0.5 * b0) + 1.35 * delta1_0
                + Math.abs(n) * 0.667 * this.Fihi_j(zyM[i - 1][j].getY()) * Math.tan(alfa1));
    }
//

//    //----- 47 формула B(i-1, j)
    double Bi1j3(int i, int j) {
        n = this.n("P");
        delta1_0 = this.delta1_0();

        return 0.15 * gamma * this.Hi1j3(i, j) * A0 / 439 - this.Fi1j(i, j)
                * ((delta1_0 - Math.abs(n) * 0.667 * this.Fihi_j(zyM[i - 1][j].getY())
                * Math.tan(alfa1)) * Math.sin(2 * zyM[i - 1][j].getDelta())
                + n * Math.cos(2 * zyM[i - 1][j].getDelta()))
                * (Fist * Kfi * (zyM[i - 1][j].getSigma() * Math.cos(zyM[i - 1][j].getFidin())
                - zyM[i][j - 1].getCdin() * Math.sin(zyM[i - 1][j].getFidin()))
                + Cst * Kc * Math.cos(zyM[i - 1][j].getFidin()));
    }
//
//
//    //----- 48 формула D(i-1, j)

    double Di1j3(int i, int j) {
        n = this.n("P");
        delta1_0 = this.delta1_0();

        return 0.04 * gamma * this.Hi1j3(i, j) * A0 / 439 + this.Fi1j(i, j)
                * (n * Math.sin(2 * zyM[i - 1][j].getDelta())
                - (delta1_0 - this.Fihi_j(zyM[i - 1][j].getY()) * Math.abs(n) * 0.667
                * Math.tan(alfa1)) * Math.cos(2 * zyM[i - 1][j].getDelta()))
                * (Fist * Kfi * (zyM[i - 1][j].getSigma() * Math.cos(zyM[i - 1][j].getFidin())
                - zyM[i - 1][j].getCdin() * Math.sin(zyM[i - 1][j].getFidin()))
                + Cst * Kc * Math.cos(zyM[i - 1][j].getFidin()));
    }
//
//    //----- 40 формулалар асосида топилди

    double sigmaij3(int i, int j) {

        return zyM[i - 1][j].getSigma() - this.W3(i, j)
                - 2 * (zyM[i - 1][j].getSigma() * Math.tan(zyM[i - 1][j].getFidin()) + zyM[i - 1][j].getCdin())
                * (zyM[i - 1][j].getDelta() - Math.PI / 2);
    }
//

//
//    //------ 42 тенгламанинг унг кисми
    double W3(int i, int j) {
        return (gamma + this.Bi1j3(i, j)) * ((zyM[i - 1][j].getZ() - 0) - (zyM[i - 1][j].getY() - z1y) * Math.tan(zyM[i - 1][j].getFidin()))
                + this.Di1j3(i, j) * ((zyM[i - 1][j].getY() - z1y) + (zyM[i - 1][j].getZ() - 0) * Math.tan(zyM[i - 1][j].getFidin()));

//        return (gamma + this.Bi1j(i, j)) * ((zyM[i - 1][j].getZ() - this.z1z(i, j)) - (zyM[i - 1][j].getY() - this.z1y(i, j)) * Math.tan(zyM[i - 1][j].getFidin()))
//                + this.Di1j(i, j) * ((zyM[i - 1][j].getY() - this.z1y(i, j)) + (zyM[i - 1][j].getZ() - this.z1z(i, j)) * Math.tan(zyM[i - 1][j].getFidin()));
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////По указанию Шавката
    public static void ZY(int i, int j) {
        PoTexRasch p = new PoTexRasch();
        ModelZY zy = new ModelZY();

        if (i + j == 10) {  //А0 кесма учун z, y, sigma, delta ларни аниклаймиз
            z = (j * p.b0() / 10) * Math.sin(p.alfa());
            y = (j * p.b0() / 10) * Math.cos(p.alfa());

            sigma = p.sigma(z, y);
            delta = p.delta(z, y);
            zy.setZ(z);
            zy.setY(y);
            zy.setSigma(sigma);
            zy.setDelta(delta);
            zy.setCdin(Cdin);
            zy.setFidin(Fidin);
            zyM[i][j] = zy;

//        } else if ((i + j > 10) && (i <= 10 && j <= 10)) { //А0А1 учбурчакдаги барча нукталар учун z, y, sigma, delta ларни аниклаймиз
//            z1z = p.z1z(i, j);
//            z1y = p.z1y(i, j);
//            Fidin = p.Fidin(z1z, z1y);
//            Cdin = p.Cdin(z1z, z1y);
//            deltaij = p.deltaij(i, j);
//            sigmaij = p.sigmaij(i, j);
//
//            zy.setZ(z1z);
//            zy.setY(z1y);
//            zy.setSigma(sigmaij);
//            zy.setDelta(deltaij);
//            zy.setCdin(Cdin);
//            zy.setFidin(Fidin);
//            zyM[i][j] = zy;
            /////////////////////////////////////////
        } else if ((i + j > 10) && (i <= 10 && j <= 10)) { //А0А1 учбурчакдаги барча нукталар учун z, y, sigma, delta ларни аниклаймиз
            int kkk = 0;
            z1z = p.z1z(i, j);
            z1y = p.z1y(i, j);
            Fidin = p.Fidin(z1z, z1y);
            Cdin = p.Cdin(z1z, z1y);
            deltaij = p.deltaij(i, j);
            sigmaij = p.sigmaij(i, j);
//            System.out.println("zyfcds " + z1z + " " + z1y + " " + Fidin + " " + Cdin + " " + deltaij + " " + sigmaij);
            old_d = deltaij;
            p.k_di1j(i, j, old_d);
            p.k_dij1(i, j, old_d);
            do {

                new_d = p.deltaij(i, j);
//                System.out.println("old_d >>> " + old_d + "  new_d >>> " + new_d);
                z1z = p.z1z(i, j);
                z1y = p.z1y(i, j);
                Fidin = p.Fidin(z1z, z1y);
                Cdin = p.Cdin(z1z, z1y);
                sigmaij = p.sigmaij(i, j);

//                System.out.println("zyfcds " + z1z + " " + z1y + " " + Fidin + " " + Cdin + " " + new_d + " " + sigmaij);

                zy.setZ(z1z);
                zy.setY(z1y);
                zy.setSigma(sigmaij);
                zy.setDelta(new_d);
                zy.setCdin(Cdin);
                zy.setFidin(Fidin);
                zyM[i][j] = zy;

                System.out.println("old_d >>> " + old_d + "  new_d >>> " + new_d);
                System.out.println(Math.abs(old_d - new_d));
                if (E >= Math.abs(old_d - new_d)) {                    
                    break;
                } else {
                    old_d = new_d;
                }
                kkk++;
                System.out.println("kkk >>>>> " + kkk);
            } while (kkk < 100);

///////////////////////
////////////////////
            /////////////////////////////////////////
//
        } else if ((i >= 10 && i <= 20) && j == 0) { //0 даги барча нукталар (10:0 ---->> 20:0) учун z, y, sigma, delta ларни аниклаймиз
            zy.setZ(0);
            zy.setY(0);
            zy.setCdin(p.Cdin(0, 0));
            zy.setFidin(p.Fidin(0, 0));
//            zy.setSigma(p.sigma(0,0));
//            zy.setDelta(p.delta(0, 0));
            zy.setSigma(p.sigma_10i_0(i));
            zy.setDelta(p.delta_10i_0(i));
            zyM[i][j] = zy;
        } else if ((i >= 11 && i <= 20) && j != 0) { //А10А2 учбурчакдаги барча нукталар учун z, y, sigma, delta ларни аниклаймиз
            z1z = p.z1z(i, j);
            z1y = p.z1y(i, j);
            Fidin = p.Fidin(z1z, z1y);
            Cdin = p.Cdin(z1z, z1y);
            deltaij = p.deltaij(i, j);
            sigmaij = p.sigmaij(i, j);

            zy.setZ(z1z);
            zy.setY(z1y);
            zy.setSigma(sigmaij);
            zy.setDelta(deltaij);
            zy.setCdin(Cdin);
            zy.setFidin(Fidin);
            zyM[i][j] = zy;

        } else if (i > 20 && i - j == 20) { //0А3 кесмадаги барча нукталар учун z, y, sigma, delta ларни аниклаймиз
            z3z = 0;
            z3y = p.z3y(i, j);
            Fidin = p.Fidin(z1z, z1y);
            Cdin = p.Cdin(z3z, z3y);
            deltaij = Math.PI / 2;
            sigmaij = p.sigmaij3(i, j);

            zy.setZ(z3z);
            zy.setY(z3y);
            zy.setSigma(sigmaij);
            zy.setDelta(deltaij);
            zy.setCdin(Cdin);
            zy.setFidin(Fidin);
            zyM[i][j] = zy;

        } else if (i > 20 && i - j < 20) { //А30А2 учбурчакдаги барча нукталар учун z, y, sigma, delta ларни аниклаймиз
//            Внутренные точки 3й области

            z1z = p.z1z(i, j);
            z1y = p.z1y(i, j);
            Fidin = p.Fidin(z1z, z1y);
            Cdin = p.Cdin(z1z, z1y);
            deltaij = p.deltaij(i, j);
            sigmaij = p.sigmaij(i, j);

            zy.setZ(z1z);
            zy.setY(z1y);
            zy.setSigma(sigmaij);
            zy.setDelta(deltaij);
            zy.setCdin(Cdin);
            zy.setFidin(Fidin);
            zyM[i][j] = zy;
        } else { //матрицани колган кисмини хозирча 0га тулдирамиз
            zy.setZ(0);
            zy.setY(0);
            zy.setSigma(0);
            zy.setDelta(0);
            zy.setCdin(0);
            zy.setFidin(0);
            zyM[i][j] = zy;
        }
    }

//    /////////////////end 
//
//    ////////////КОНЕЦ ЗОНЫ 1 
////    public static void main(String[] args) {
////        PoTexRasch p = new PoTexRasch();
////
//////        for (int i = 9; i >= 0; i--) {
//////            for (int j = 0; j <= 10; j++) {
////        for (int i = 0; i < zyM.length; i++) {
////            for (int j = 0; j < zyM[i].length; j++) {
//////                int i=1;
////                if (i + j == 10) {
////                    ZY(i, j);
//////                    System.out.println("ZYMZ " + i + ":" + j + " -- " + zyM[i][j].getZ());
//////                    System.out.println("ZYMY " + i + ":" + j + " -- " + zyM[i][j].getY());
//////                    System.out.println("ZYMS " + i + ":" + j + " -- " + zyM[i][j].getSigma());
//////                    System.out.println("ZYMD " + i + ":" + j + " -- " + zyM[i][j].getDelta());
//////                    System.out.println("ZYMCd " + i + ":" + j + " -- " + zyM[i][j].getCdin());
//////                    System.out.println("ZYMFd " + i + ":" + j + " -- " + zyM[i][j].getFidin());
//////                    p.mju();
//////                    System.out.println("z " + zyM[i][j].getZ() + " : y " + zyM[i][j].getY());
//////                    System.out.println("Cdin " + p.Cdin(zyM[i][j].getZ(), zyM[i][j].getY()));
////                } else {
////                    ModelZY zy = new ModelZY();
////
////                    zy.setZ(0);
////                    zy.setY(0);
////                    zy.setSigma(0);
////                    zy.setDelta(0);
////                    zy.setCdin(0);
////                    zy.setFidin(0);
////                    zyM[i][j] = zy;
////                }
////            }
////        }
//////        zyM[8][2].setDelta(0.001);
////
////        for (int i = 0; i < zyM.length; i++) {
//////            System.out.println(zyM[i].length);
////            for (int j = 0; j < zyM[i].length; j++) {
////                System.out.print(i + ":" + j + " " + zyM[i][j].toString());
////            }
////            System.out.println("");
////        }
////    }
//
    ///КОРРЕКТИРОВКА
    public static double k_d0_10() {
        double d1 = 0.5 * (PoTexRasch.zyM[0][10].getDelta() + PoTexRasch.zyM[1][10].getDelta());
        return d1;
    }

    public static double k_d1_9() {
        double d2 = 0.5 * (PoTexRasch.zyM[1][9].getDelta() + PoTexRasch.zyM[1][10].getDelta());
        return d2;
    }

    double k_di1j(int i, int j, double d) {
        double deltai1j = 0.5 * (zyM[i - 1][j].getDelta() + d);
//        System.out.println("k_di1j zyM[i - 1][j]--->>> " + zyM[i - 1][j].getDelta());
//        System.out.println("k_di1j --->>> " + deltai1j);
        zyM[i - 1][j].setDelta(deltai1j);
        return deltai1j;
    }

    double k_dij1(int i, int j, double d) {
        double deltaij1 = 0.5 * (zyM[i][j - 1].getDelta() + d);
//        System.out.println("k_dij1 zyM[i][j - 1]--->>> " + zyM[i][j - 1].getDelta());
//        System.out.println("k_dij1 --->>> " + deltaij1);
        zyM[i][j - 1].setDelta(deltaij1);
        return deltaij1;
    }

    public void Korrect() {
        double d1 = PoTexRasch.k_d0_10();
        PoTexRasch.zyM[0][10].setDelta(d1);
        System.out.println("----------------------");
        double d2 = PoTexRasch.k_d1_9();
        PoTexRasch.zyM[1][9].setDelta(d2);
    }
    
    double k_dz0(int i, int j, double d) {
        double deltaz0 = 0.5 * (zyM[i - 1][j].getDelta() + Math.PI/2);
//        System.out.println("k_di1j zyM[i - 1][j]--->>> " + zyM[i - 1][j].getDelta());
//        System.out.println("k_di1j --->>> " + deltai1j);
        zyM[i - 1][j].setDelta(deltaz0);
        return deltaz0;
    }
}
