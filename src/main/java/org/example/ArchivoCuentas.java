package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArchivoCuentas {

    public static List<Cuenta> leerCuentas(String archivo) throws IOException {
        List<Cuenta> cuentas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            String tipoCuenta = datos[0];
            String numeroCuenta = datos[1];
            double saldo = Double.parseDouble(datos[2]);

            if (tipoCuenta.equals("Ahorros")) {
                cuentas.add(new CuentaAhorros(numeroCuenta, saldo));
            } else if (tipoCuenta.equals("Corriente")) {
                double limiteSobregiro = Double.parseDouble(datos[3]);
                cuentas.add(new CuentaCorriente(numeroCuenta, saldo, limiteSobregiro));
            }
        }
        br.close();
        return cuentas;
    }
}
