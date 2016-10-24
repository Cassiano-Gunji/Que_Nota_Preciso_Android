package br.unip.quenotapreciso;

/**
 * Created by Cassiano on 23/10/2016.
 */

public class Calculadora {

    static String calculaNp2(double np1, double pim) {

        String saida = "";
        double np2 = (5.0 - np1 * 0.4 - pim * 0.2) / 0.4;

        if (np2 > 10.0) {
            saida = ">10;0";
        } else if (np2 < 0.0) {
            saida = "<0";
        } else {
            saida = String.format("%.1f", np2);
        }

        return saida;
    }
}
