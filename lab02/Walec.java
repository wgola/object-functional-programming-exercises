package lab02;

import java.lang.Math;

public class Walec {
    private double r, h;

    public void setR(double newR) {
        this.r = newR;
    }

    public void setH(double newH) {
        this.h = newH;
    }

    public double getR() {
        return this.r;
    }

    public double getH() {
        return this.h;
    }

    public Walec(double r, double h) {
        this.r = r;
        this.h = h;
    }

    public Walec() {
    }

    public double polePodstawy() {
        return Math.pow(this.r, this.h) * Math.PI;
    }

    public double poleBoczne() {
        return 2 * Math.PI * this.r * this.h;
    }

    public double poleCalkowite() {
        return 2 * this.polePodstawy() + this.poleBoczne();
    }

    public double objetosc() {
        return this.polePodstawy() * this.h;
    }
}
