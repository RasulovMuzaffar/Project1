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
public class ModelIJ {

    private double i;
    private double j;

    public ModelIJ() {
    }

    public ModelIJ(double i, double j) {
        this.i = i;
        this.j = j;
    }

    public double getI() {
        return i;
    }

    public void setI(double i) {
        this.i = i;
    }

    public double getJ() {
        return j;
    }

    public void setJ(double j) {
        this.j = j;
    }

    @Override
    public String toString() {
        return "IJ{" + "i=" + i + ", j=" + j + '}';
    }

}
