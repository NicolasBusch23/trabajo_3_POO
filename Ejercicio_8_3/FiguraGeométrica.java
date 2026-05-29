package Ejercicio_8_3;

/**
 * Modela una figura geométrica con volumen y superficie.
 */
public class FiguraGeométrica {
    private double volumen;
    private double superficie;

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public double getVolumen() {
        return this.volumen;
    }

    public double getSuperficie() {
        return this.superficie;
    }
}