package lab02;

import java.lang.Math;

public class Walec {
    private double r, h;

    public Walec(double r, double h) {
        this.r = r;
        this.h = h;
    }

    public Walec() {
    }

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

    public double polePodstawy() {
        return Math.PI * Math.pow(this.r, 2);
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
