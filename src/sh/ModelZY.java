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

    public ModelZY() {
    }

    public ModelZY(double z, double y, double sigma, double delta) {
        this.z = z;
        this.y = y;
        this.sigma = sigma;
        this.delta = delta;
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

}
