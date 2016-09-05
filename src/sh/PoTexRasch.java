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
    double K = 0.010; //коэффициент виброразрушения грунта;
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
    //

    static ModelIJ[][] ij = new ModelIJ[1][10];
//    public static ModelZY[][] zyM = new ModelZY[11][31];
    public static ModelZY[][] zyM = new ModelZY[31][11];

    ////////////По указанию Шавката
    public static void ZY(int i, int j) {
        PoTexRasch p = new PoTexRasch();
        ModelZY zy = new ModelZY();

        if (i + j == 10) { //0.10
            z = j * p.b0() / 10 * Math.sin(p.alfa());
            y = j * p.b0() / 10 * Math.cos(p.alfa());

            sigma = p.sigma(z, y);
            delta = p.delta(z, y);
            zy.setZ(z);
            zy.setY(y);
            zy.setSigma(sigma);
            zy.setDelta(delta);
            zy.setCdin(Cdin);
            zy.setFidin(Fidin);
//            zy.setMju(p.mju());
            zyM[i][j] = zy;

            if (i == 10 && j == 0) {
                for (int k = 11; k < 21; k++) {
                    zyM[k][0] = zy;
                }
            }
//        } else if (i + j > 10 && (i <= 10 && j <= 10)) {
        } else if ((i + j == 11) && (i != 11)){
            z1z = p.z1z(i, j);
            z1y = p.z1y(i, j);
            Bi1j = p.Bi1j(i, j);
            Bij1 = p.Bij1(i, j);
            Di1j = p.Di1j(i, j);
            Dij1 = p.Dij1(i, j);
//            deltaij = p.deltaij(i, j);
//            sigmaij = p.sigmaij(i, j);

            zy.setZ(z1z);
            zy.setY(z1y);
//            zy.setSigma(sigmaij);
//            zy.setDelta(deltaij);
            zy.setCdin(Cdin);
            zy.setFidin(Fidin);
            zyM[i][j] = zy;
            System.out.println("z (" + i + " , " + j + ") ---->>>> " + z1z);
            System.out.println("y (" + i + " , " + j + ") ---->>>> " + z1y);
            System.out.println("p.Bij1(" + i + " , " + j + ") ---->>>> " + Bij1);
            System.out.println("p.Bi1j(" + i + " , " + j + ") ---->>>> " + Bi1j);
            System.out.println("p.Dij1(" + i + " , " + j + ") ---->>>> " + Dij1);
            System.out.println("p.Di1j(" + i + " , " + j + ") ---->>>> " + Di1j);
            System.out.println("p.Hi1j(" + i + " , " + j + ") ---->>>> " + p.Hi1j(i, j));
            System.out.println("p.Hij1(" + i + " , " + j + ") ---->>>> " + p.Hij1(i, j));
            System.out.println("p.Fi1j(" + i + " , " + j + ") ---->>>> " + p.Fi1j(i, j));
            System.out.println("p.Fij1(" + i + " , " + j + ") ---->>>> " + p.Fij1(i, j));
//            System.out.println("p.Azyij1(" + i + " , " + j + ") ---->>>> " + p.Azy(i, j - 1));
//            System.out.println("p.Azyi1j(" + i + " , " + j + ") ---->>>> " + p.Azy(i - 1, j));
            System.out.println("p.Sigma(" + i + " , " + j + ") ---->>>> " + sigmaij);
            System.out.println("p.delta(" + i + " , " + j + ") ---->>>> " + deltaij);
            System.out.println("Cdin(" + i + " , " + j + ") ---->>>> " + Cdin);
            System.out.println("Fidin(" + i + " , " + j + ") ---->>>> " + Fidin);
            System.out.println("----------------------------------------------------");
        } else if ((i + j == 12) && (i != 12)){
            z1z = p.z1z(i, j);
            z1y = p.z1y(i, j);
            Bi1j = p.Bi1j(i, j);
            Bij1 = p.Bij1(i, j);
            Di1j = p.Di1j(i, j);
            Dij1 = p.Dij1(i, j);
//            deltaij = p.deltaij(i, j);
//            sigmaij = p.sigmaij(i, j);

            zy.setZ(z1z);
            zy.setY(z1y);
//            zy.setSigma(sigmaij);
//            zy.setDelta(deltaij);
            zy.setCdin(Cdin);
            zy.setFidin(Fidin);
            zyM[i][j] = zy;
            System.out.println("z (" + i + " , " + j + ") ---->>>> " + z1z);
            System.out.println("y (" + i + " , " + j + ") ---->>>> " + z1y);
            System.out.println("p.Bij1(" + i + " , " + j + ") ---->>>> " + Bij1);
            System.out.println("p.Bi1j(" + i + " , " + j + ") ---->>>> " + Bi1j);
            System.out.println("p.Dij1(" + i + " , " + j + ") ---->>>> " + Dij1);
            System.out.println("p.Di1j(" + i + " , " + j + ") ---->>>> " + Di1j);
            System.out.println("p.Hi1j(" + i + " , " + j + ") ---->>>> " + p.Hi1j(i, j));
            System.out.println("p.Hij1(" + i + " , " + j + ") ---->>>> " + p.Hij1(i, j));
            System.out.println("p.Fi1j(" + i + " , " + j + ") ---->>>> " + p.Fi1j(i, j));
            System.out.println("p.Fij1(" + i + " , " + j + ") ---->>>> " + p.Fij1(i, j));
//            System.out.println("p.Azyij1(" + i + " , " + j + ") ---->>>> " + p.Azy(i, j - 1));
//            System.out.println("p.Azyi1j(" + i + " , " + j + ") ---->>>> " + p.Azy(i - 1, j));
            System.out.println("p.Sigma(" + i + " , " + j + ") ---->>>> " + sigmaij);
            System.out.println("p.delta(" + i + " , " + j + ") ---->>>> " + deltaij);
            System.out.println("Cdin(" + i + " , " + j + ") ---->>>> " + Cdin);
            System.out.println("Fidin(" + i + " , " + j + ") ---->>>> " + Fidin);
            System.out.println("----------------------------------------------------");
        } else if ((i + j == 13) && (i != 13)){
            z1z = p.z1z(i, j);
            z1y = p.z1y(i, j);
            Bi1j = p.Bi1j(i, j);
            Bij1 = p.Bij1(i, j);
            Di1j = p.Di1j(i, j);
            Dij1 = p.Dij1(i, j);
//            deltaij = p.deltaij(i, j);
//            sigmaij = p.sigmaij(i, j);

            zy.setZ(z1z);
            zy.setY(z1y);
//            zy.setSigma(sigmaij);
//            zy.setDelta(deltaij);
            zy.setCdin(Cdin);
            zy.setFidin(Fidin);
            zyM[i][j] = zy;
            System.out.println("z (" + i + " , " + j + ") ---->>>> " + z1z);
            System.out.println("y (" + i + " , " + j + ") ---->>>> " + z1y);
            System.out.println("p.Bij1(" + i + " , " + j + ") ---->>>> " + Bij1);
            System.out.println("p.Bi1j(" + i + " , " + j + ") ---->>>> " + Bi1j);
            System.out.println("p.Dij1(" + i + " , " + j + ") ---->>>> " + Dij1);
            System.out.println("p.Di1j(" + i + " , " + j + ") ---->>>> " + Di1j);
            System.out.println("p.Hi1j(" + i + " , " + j + ") ---->>>> " + p.Hi1j(i, j));
            System.out.println("p.Hij1(" + i + " , " + j + ") ---->>>> " + p.Hij1(i, j));
            System.out.println("p.Fi1j(" + i + " , " + j + ") ---->>>> " + p.Fi1j(i, j));
            System.out.println("p.Fij1(" + i + " , " + j + ") ---->>>> " + p.Fij1(i, j));
//            System.out.println("p.Azyij1(" + i + " , " + j + ") ---->>>> " + p.Azy(i, j - 1));
//            System.out.println("p.Azyi1j(" + i + " , " + j + ") ---->>>> " + p.Azy(i - 1, j));
            System.out.println("p.Sigma(" + i + " , " + j + ") ---->>>> " + sigmaij);
            System.out.println("p.delta(" + i + " , " + j + ") ---->>>> " + deltaij);
            System.out.println("Cdin(" + i + " , " + j + ") ---->>>> " + Cdin);
            System.out.println("Fidin(" + i + " , " + j + ") ---->>>> " + Fidin);
            System.out.println("----------------------------------------------------");
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
//        PoTexRasch p = new PoTexRasch();
        a = 0.5 * (b_pl - this.b0());
//        System.out.println("a " + a);
        return a;
    }

    //----(10)----
    double a_Np() {
//        PoTexRasch p = new PoTexRasch();
        //b=l_shp/2 (10 формула)
        a = b - 0.5 * this.b0();
        return a;
    }
    /////////////////end главы 1.3

////////////глава 1.4 Угол заложения расчетного откоса
    //----(11)----
    double alfa() {
//        PoTexRasch p = new PoTexRasch();
        alfa1 = Math.atan(1 / m); //получаем угол в радиансах
        alfa = Math.atan(h_nas / (this.a_1p() + h_nas / Math.tan(alfa1)));
//        System.out.println("alfa " + alfa1 + " " + alfa);
        return alfa;
    }
    /////////////////end главы 1.4

    ////////////глава 3 Граничные условния
    //----(25)----
    double sigma(double z, double y) {
//        PoTexRasch p = new PoTexRasch();
        Fia = this.Fia(y);
        alfa = this.alfa();
        Cdin = this.Cdin(z, y);
        Fidin = this.Fidin(z, y);
        delta = this.delta(z, y);
        sigma = (gamma * Fia * Math.cos(alfa) + Cdin * Math.cos(Fidin) * Math.cos(2 * (delta - alfa)))
                / (1 - Math.sin(Fidin) * Math.cos(2 * (delta - alfa)));
//        System.out.println("alfa " + alfa);
//        System.out.println("a.alfa() " + p.alfa());
        System.out.println("sigma {" + z + " : " + y + "} " + sigma);
        return sigma;
    }

    //----(26)----
    double delta(double z, double y) {
//        PoTexRasch p = new PoTexRasch();
        Fia = this.Fia(y);
        alfa = this.alfa();
        Cdin = this.Cdin(z, y);
        Fidin = this.Fidin(z, y);
        System.out.println("------------------DELTA");
        System.out.println(Fia + " , " + alfa + " , " + Cdin + " , " + Fidin);

        delta = 1 / 2 * Math.asin(gamma * Fia * Math.sin(alfa)
                * (Cdin + gamma * Fia * Math.tan(Fidin) * Math.cos(alfa)
                - Math.sqrt(
                        Math.pow(Cdin + gamma * Fia * Math.cos(alfa) * Math.tan(Fidin), 2)
                        * Math.pow(Math.sin(Fidin), 2)
                        - Math.pow(gamma * Fia * Math.sin(alfa) * Math.sin(Fidin), 2)
                )
                / (Math.pow(gamma * Fia * Math.sin(alfa) * Math.tan(Fidin), 2)
                + Math.pow(gamma * Fia * Math.cos(alfa) * Math.tan(Fidin) + Cdin, 2)) * Math.cos(Fidin)))
                + alfa;
//        System.out.println(gamma * Fia * Math.sin(alfa)
//                * (Cdin + gamma * Fia * Math.tan(Fidin) * Math.cos(alfa)
//                - Math.sqrt(Math.pow(Cdin + gamma * Fia * Math.cos(alfa) * Math.tan(Fidin), 2)
//                        * Math.pow(Math.sin(Fidin), 2)
//                        - Math.pow(gamma * Fia * Math.sin(alfa) * Math.sin(Fidin), 2))
//                / (Math.pow(gamma * Fia * Math.sin(alfa) * Math.tan(Fidin), 2)
//                + Math.pow(gamma * Fia * Math.cos(alfa) * Math.tan(Fidin) + Cdin, 2)) * Math.cos(Fidin))
//                + " : " + (gamma * Fia * Math.sin(alfa))
//                + " : " + (Cdin + gamma * Fia * Math.tan(Fidin) * Math.cos(alfa))
//                + " : " + (Math.sqrt(Math.pow(Cdin + gamma * Fia * Math.cos(alfa) * Math.tan(Fidin), 2)
//                        * Math.pow(Math.sin(Fidin), 2) - Math.pow(gamma * Fia * Math.sin(alfa) * Math.sin(Fidin), 2)))
//                + " >>: " + (Math.pow(Cdin + gamma * Fia * Math.cos(alfa) * Math.tan(Fidin), 2) * (Math.pow(Math.sin(Fidin), 2))
//                + " : " + Math.pow(gamma * Fia * Math.sin(alfa) * Math.sin(Fidin), 2))
//                + " >>: " + (Math.pow(Cdin + gamma * Fia * Math.cos(alfa) * Math.tan(Fidin), 2)
//                * Math.pow(Math.sin(Fidin), 2) - Math.pow(gamma * Fia * Math.sin(alfa) * Math.sin(Fidin), 2))
//                + " <<: "
//                + (Math.pow(gamma * Fia * Math.sin(alfa) * Math.tan(Fidin), 2)
//                + Math.pow(gamma * Fia * Math.cos(alfa) * Math.tan(Fidin) + Cdin, 2))
//                + " : " + Math.cos(Fidin));
//        System.out.println("delta - " + delta);
        System.out.println("delta {" + z + " : " + y + "} " + delta);
        System.out.println("------------------DELTA");
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
//        PoTexRasch p = new PoTexRasch();
//        System.out.println("Kc --> " + Kc);
//        System.out.println("z - y -->> " + z + " - " + y);
        Cdin = this.Kc_1() + Kc * Math.exp(this.n("P") * z - this.delta1_0() * (Math.abs(y + 0.5 * this.b0()) - 1.35) + this.delta3(this.n("P")) * this.Fihi_j(y));
//        System.out.println("Cdin ------------->>> " + Cdin);
        return Cdin;
    }

//----(20)----
    double Fidin(double z, double y) {
//        PoTexRasch p = new PoTexRasch();
        Fidin = this.Kfi_1() + Kfi * Math.exp(this.n("P") * z - this.delta1_0() * (Math.abs(y + 0.5 * this.b0()) - 1.35) + this.delta3(this.n("P")) * this.Fihi_j(y));
        System.out.println("Fidin ------------->>> " + Fidin);
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
    double Azy(int i, int j) {
        Azy = A0 * Math.exp(this.n("P") * zyM[i][j].getZ() - this.delta1_0() * this.Fiy(zyM[i][j].getZ()) + this.delta3(this.n("P")) * this.Fihi_j(zyM[i][j].getZ()));
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

    double Fiy(double y) {
        if (Math.abs(y + 0.5 * this.b0()) <= 1.35) {
            Fiy = 0;
        } else {
            Fiy = Math.abs(y + 0.5 * this.b0()) - 1.35;
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
        delta3 = 0.667 * Math.abs(this.n("P")) * Math.tan(alfa1);
        return delta3;
    }
    /////////////////end главы 1.6

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
        return Ay;
    }

    double Az() {
        Az = 160.9 * sigma_bsr * ((1 - Math.pow(mju_0, 2)) / E) * Math.exp(-alfa * (h_b - 0.25));
        return Az;
    }

    /////////////////end главы 1.1
    //НАЧАЛИ ПОСТРОИТЬ СЕТКУ ЗОНЫ 1
    double z1z(int i, int j) {
//        System.out.println(zyM[i - 1][j].getFidin());
        z1z = zyM[i][j - 1].getZ() - zyM[i][j - 1].getY() * Math.tan(zyM[i][j - 1].getSigma() + 0.25 * Math.PI - 0.5 * zyM[i][j - 1].getFidin())
                - this.z1y(i, j) * Math.tan(zyM[i][j - 1].getSigma() + 0.25 * Math.PI - 0.5 * zyM[i][j - 1].getFidin());
//        if (i == 1 && j == 10) {
//            System.out.println("============== z1z " + z1z);
//        }
        System.out.println("z1z {" + i + " : " + j + "} " + z1z);
        return z1z;
    }

    double z1y(int i, int j) {
        z1y = (zyM[i - 1][j].getZ() - zyM[i][j - 1].getZ()
                + zyM[i][j - 1].getY() * Math.tan(zyM[i][j - 1].getSigma() + 0.25 * Math.PI - 0.5 * zyM[i][j - 1].getFidin())
                - zyM[i - 1][j].getY() * Math.tan(zyM[i - 1][j].getSigma() + 0.25 * Math.PI - 0.5 * zyM[i - 1][j].getFidin()))
                / (Math.tan(zyM[i][j - 1].getSigma() + 0.25 * Math.PI - 0.5 * zyM[i][j - 1].getFidin())
                - Math.tan(zyM[i - 1][j].getSigma() + 0.25 * Math.PI - 0.5 * zyM[i - 1][j].getFidin()));
        System.out.println("z1y {" + i + " : " + j + "} " + z1y);
        return z1y;
    }

    double Fij1(int i, int j) {
        Fij1 = K * A0 * Math.exp(this.n("P") * zyM[i][j - 1].getZ() - this.delta1_0()
                * (zyM[i][j - 1].getY() + 0.5 * this.b0()) + 1.35 * this.delta1_0()
                + Math.abs(this.n("P")) * 0.667 * this.Fihi_j(zyM[i][j - 1].getY()) * Math.tan(alfa1) - K * this.Azy(i, j - 1));
        System.out.println("Fij1 {" + i + " : " + j + "} " + Fij1);
        return Fij1;
    }

    double Fi1j(int i, int j) {
        Fi1j = K * A0 * Math.exp(this.n("P") * zyM[i - 1][j].getZ() - this.delta1_0()
                * (zyM[i - 1][j].getY() + 0.5 * this.b0()) + 1.35 * this.delta1_0()
                + Math.abs(this.n("P")) * 0.667 * this.Fihi_j(zyM[i - 1][j].getY()) * Math.tan(alfa1) - K * this.Azy(i - 1, j));
        System.out.println("Fij1 {" + i + " : " + j + "} " + Fi1j);
        return Fi1j;
    }

    double Hij1(int i, int j) {
        Hij1 = Math.exp(this.n("P") * zyM[i][j - 1].getZ() - this.delta1_0()
                * (zyM[i][j - 1].getY() + 0.5 * this.b0()) + 1.35 * this.delta1_0()
                + Math.abs(this.n("P")) * 0.667 * this.Fihi_j(zyM[i][j - 1].getY()) * Math.tan(alfa1));
        System.out.println("Hij1 {" + i + " : " + j + "} " + Hij1);
        return Hij1;
    }

    double Hi1j(int i, int j) {
        Hi1j = Math.exp(this.n("P") * zyM[i - 1][j].getZ() - this.delta1_0()
                * (zyM[i - 1][j].getY() + 0.5 * this.b0()) + 1.35 * this.delta1_0()
                + Math.abs(this.n("P")) * 0.667 * this.Fihi_j(zyM[i - 1][j].getY()) * Math.tan(alfa1));
        System.out.println("Hi1j {" + i + " : " + j + "} " + Hi1j);
        return Hi1j;
    }

    double Bij1(int i, int j) {
        Bij1 = 0.15 * gamma * this.Hij1(i, j) * A0 / 439 - this.Fij1(i, j)
                * ((this.delta1_0() - Math.abs(this.n("P")) * 0.667 * this.Fihi_j(zyM[i][j - 1].getY())
                * Math.tan(alfa1)) * Math.sin(2 * zyM[i][j - 1].getDelta())
                + this.n("P") * Math.cos(2 * zyM[i][j - 1].getDelta()))
                * (Fist * Kfi * zyM[i][j - 1].getSigma() * Math.cos(zyM[i][j - 1].getFidin())
                - zyM[i][j - 1].getCdin() * Math.sin(zyM[i][j - 1].getFidin())
                + Cst * Kc * Math.cos(zyM[i][j - 1].getFidin()));
//        System.out.println("----------------------Bij1");
//        System.out.println("Fij1 -->> " + this.Fij1(i, j));
//        System.out.println("Fi1j -->> " + this.Fi1j(i, j));
//        System.out.println("Hij1 -->> " + this.Hij1(i, j));
//        System.out.println("Hi1j -->> " + this.Hi1j(i, j));
//        System.out.println("gamma - " + gamma);
//        System.out.println("A0 - " + A0);
//        System.out.println("delta1_0 - " + this.delta1_0());
//        System.out.println("Math.abs(n(\"P\")) - " + Math.abs(n("P")));
//        System.out.println("Math.abs(n(\"P\")) - " + Math.abs(n("P")));
//        System.out.println("this.Fihi_j(zyM[i][j - 1].getY()) - " + this.Fihi_j(zyM[i][j - 1].getY()));
//        System.out.println("Math.tan(alfa1) - " + Math.tan(alfa1));
//        System.out.println("Math.cos(2 * delta(zyM[i][j - 1].getZ(), zyM[i][j - 1].getY())) - " + Math.cos(2 * delta(zyM[i][j - 1].getZ(), zyM[i][j - 1].getY())));
//        System.out.println("delta(zyM[i][j - 1].getZ(), zyM[i][j - 1].getY())- " + delta(zyM[i][j - 1].getZ(), zyM[i][j - 1].getY()));
//        System.out.println("(zyM[i][j - 1].getZ(), zyM[i][j - 1].getY())- " + zyM[i][j - 1].getZ() + " , " + zyM[i][j - 1].getY());

//        System.out.println("----------------------");
        System.out.println("Bij1 {" + i + " : " + j + "} " + Bij1);
        return Bij1;
    }

    double Bi1j(int i, int j) {
        Bi1j = 0.15 * gamma * this.Hi1j(i, j) * A0 / 439 - this.Fi1j(i, j)
                * ((this.delta1_0() - Math.abs(this.n("P")) * 0.667 * this.Fihi_j(zyM[i - 1][j].getY())
                * Math.tan(alfa1)) * Math.sin(2 * zyM[i - 1][j].getDelta())
                + this.n("P") * Math.cos(2 * zyM[i][j - 1].getDelta()))
                * (Fist * Kfi * zyM[i - 1][j].getSigma() * Math.cos(zyM[i - 1][j].getFidin())
                - zyM[i - 1][j].getCdin() * Math.sin(zyM[i - 1][j].getFidin())
                + Cst * Kc * Math.cos(zyM[i - 1][j].getFidin()));
        System.out.println("Bi1j {" + i + " : " + j + "} " + Bi1j);
        return Bi1j;
    }

    double Dij1(int i, int j) {
        Dij1 = 0.04 * gamma * this.Hij1(i, j) * A0 / 439 + this.Fij1(i, j)
                * (this.n("P") * Math.sin(2 * zyM[i][j - 1].getSigma())
                - (this.delta1_0() - this.Fihi_j(zyM[i][j - 1].getY()) * Math.abs(n("P")) * 0.667
                * Math.tan(alfa1)) * Math.cos(2 * zyM[i][j - 1].getDelta()))
                * (Fist * Kfi * (zyM[i][j - 1].getSigma() * Math.cos(zyM[i][j - 1].getFidin())
                - zyM[i][j - 1].getCdin() * Math.sin(zyM[i][j - 1].getFidin()))
                + Cst * Kc * Math.cos(zyM[i][j - 1].getFidin()));
        System.out.println("Dij1 {" + i + " : " + j + "} " + Dij1);
        return Dij1;
    }

    double Di1j(int i, int j) {
        Di1j = 0.04 * gamma * this.Hi1j(i, j) * A0 / 439 + this.Fi1j(i, j)
                * (this.n("P") * Math.sin(2 * zyM[i - 1][j].getSigma())
                - (this.delta1_0() - this.Fihi_j(zyM[i - 1][j].getY()) * Math.abs(n("P")) * 0.667
                * Math.tan(alfa1)) * Math.cos(2 * zyM[i - 1][j].getDelta()))
                * (Fist * Kfi * (zyM[i - 1][j].getSigma() * Math.cos(zyM[i - 1][j].getFidin())
                - zyM[i - 1][j].getCdin() * Math.sin(zyM[i - 1][j].getFidin()))
                + Cst * Kc * Math.cos(zyM[i - 1][j].getFidin()));
        System.out.println("Di1j {" + i + " : " + j + "} " + Di1j);
        return Di1j;
    }

    double sigmaij(int i, int j) {

        sigmaij = 2 * (zyM[i][j - 1].getSigma() * Math.tan(Fidin(zyM[i][j - 1].getZ(), zyM[i][j - 1].getY())) + zyM[i][j - 1].getCdin())
                * (zyM[i][j - 1].getDelta() - this.deltaij(i, j)) + zyM[i][j - 1].getSigma()
                - ((gamma + Bij1(i, j)) * ((zyM[i][j - 1].getZ() - z1z/*zyM[i][j].getZ()*/)
                + (zyM[i][j - 1].getY() - z1y/*zyM[i][j].getY()*/) * Math.tan(Fidin(zyM[i][j - 1].getZ(), zyM[i][j - 1].getY())))
                + this.Dij1(i, j) * ((zyM[i][j - 1].getY() - z1y/*zyM[i][j].getY()*/)
                - (zyM[i][j - 1].getZ() - z1z/*zyM[i][j].getZ()*/) * Math.tan(Fidin(zyM[i][j - 1].getZ(), zyM[i][j - 1].getY()))));
        System.out.println("sigmaij {" + i + " : " + j + "} " + sigmaij);
        return sigmaij;
    }

    double deltaij(int i, int j) {
        deltaij = (((gamma + this.Bi1j(i, j)) * ((zyM[i - 1][j].getZ() - z1z/*zyM[i][j].getZ()*/)
                - (zyM[i - 1][j].getY() - z1y/*zyM[i][j].getY()*/) * Math.tan(Fidin(zyM[i - 1][j].getZ(), zyM[i - 1][j].getY())))
                + Di1j(i, j) * ((zyM[i - 1][j].getY() - z1y/*zyM[i][j].getY()*/)
                + (zyM[i - 1][j].getZ() - z1z/*zyM[i][j].getZ()*/) * Math.tan(Fidin(zyM[i - 1][j].getZ(), zyM[i - 1][j].getY()))))
                - zyM[i - 1][j].getSigma()
                - ((gamma + Bij1(i, j)) * ((zyM[i][j - 1].getZ() - z1z/*zyM[i][j].getZ()*/)
                + (zyM[i][j - 1].getY() - z1y/*zyM[i][j].getY()*/) * Math.tan(Fidin(zyM[i][j - 1].getZ(), zyM[i][j - 1].getY())))
                + Dij1(i, j) * ((zyM[i][j - 1].getY() - z1y/*zyM[i][j].getY()*/)
                - (zyM[i][j - 1].getZ() - z1z/*zyM[i][j].getZ()*/) * Math.tan(Fidin(zyM[i][j - 1].getZ(), zyM[i][j - 1].getY()))))
                + zyM[i][j - 1].getSigma()
                + 2 * zyM[i][j - 1].getDelta() * (zyM[i][j - 1].getSigma() * Math.tan(Fidin(zyM[i][j - 1].getZ(), zyM[i][j - 1].getY())) + zyM[i][j - 1].getCdin())
                + 2 * zyM[i - 1][j].getDelta() * (zyM[i - 1][j].getSigma() * Math.tan(Fidin(zyM[i - 1][j].getZ(), zyM[i - 1][j].getY())) + zyM[i - 1][j].getCdin()))
                / (2 * zyM[i][j - 1].getSigma() * Math.tan(Fidin(zyM[i][j - 1].getZ(), zyM[i][j - 1].getY())) + 2 * zyM[i][j - 1].getCdin()
                + 2 * zyM[i - 1][j].getSigma() * Math.tan(Fidin(zyM[i - 1][j].getZ(), zyM[i - 1][j].getY())) - 2 * zyM[i - 1][j].getCdin());

        System.out.println("deltaij {" + i + " : " + j + "} " + deltaij);
        return deltaij;
    }
    ////////////КОНЕЦ ЗОНЫ 1 
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
