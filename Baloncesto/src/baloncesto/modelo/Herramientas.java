/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baloncesto.modelo;

/**
 *
 * @author inaki
 */
public class Herramientas {
    public static boolean validarFecha(String fecha) {
        String[] fechaDiv = fecha.split("-");
        int ano, mes, dia;
        ano = 0;
        mes = 0;
        dia = 0;
        if (fechaDiv.length != 3) {
            return false;
        } else {
            try {
                ano = Integer.parseInt(fechaDiv[0]);
                mes = Integer.parseInt(fechaDiv[1]);
                dia = Integer.parseInt(fechaDiv[2]);
            } catch (NumberFormatException ex) {
                return false;
            }
            if (mes < 1 || mes > 12) {
                return false;
            } else if (dia < 1 || dia > 31) {
                return false;
            } else if (ano < 1950 || ano > 2050) {
                return false;
            } else {
                return true;
            }
        }
    }

    public static boolean validarTime(String duracion) {
        String[] durDiv = duracion.split(":");
        int horas, min, seg;
        horas = 0;
        min = 0;
        seg = 0;
        if (durDiv.length != 3) {
            return false;
        } else {
            try {
                horas = Integer.parseInt(durDiv[0]);
                min = Integer.parseInt(durDiv[1]);
                seg = Integer.parseInt(durDiv[2]);
            } catch (NumberFormatException ex) {
                return false;
            }
            if (horas < 0 || horas > 12) {
                return false;
            } else if (min < 0 || min > 59) {
                return false;
            } else if (seg < 00 || seg > 59) {
                return false;
            } else {
                return true;
            }
        }
    }
}
