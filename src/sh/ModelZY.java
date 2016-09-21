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
public class ModelZY {

    private double z;
    private double y;
    private double sigma;
    private double delta;
    private double Cdin;
    private double Fidin;
    private double mju;

    public ModelZY() {
    }

    public ModelZY(double z, double y, double sigma, double delta, double Cdin, double Fidin, double mju) {
        this.z = z;
        this.y = y;
        this.sigma = sigma;
        this.delta = delta;
        this.Cdin = Cdin;
        this.Fidin = Fidin;
        this.mju = mju;
    }

    @Override
    public String toString() {
//        return "{" + "z=" + z + ", y=" + y + ", sigma=" + sigma + ", delta=" + delta + ", Cdin=" + Cdin + ", Fidin=" + Fidin + "}";
//        return "{" + z + ", " + y + ", " + sigma + ", " + delta + ", " + Cdin + ", " + Fidin + "}";
//        return "{" + z + ", " + y + ", " + sigma + ", " + delta + "}";
        return "{" + z + ", " + y + "}";
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getSigma() {
        return sigma;
    }

    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getCdin() {
        return Cdin;
    }

    public void setCdin(double Cdin) {
        this.Cdin = Cdin;
    }

    public double getFidin() {
        return Fidin;
    }

    public void setFidin(double Fidin) {
        this.Fidin = Fidin;
    }

    public double getMju() {
        return mju;
    }

    public void setMju(double mju) {
        this.mju = mju;
    }

}
