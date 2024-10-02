package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco implements BancoInterface {
    private List<Cuenta> cuentas;

    // Constructor
    public Banco(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    @Override
    public Cuenta buscarPorNumeroCuenta(String numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    @Override
    public List<Cuenta> buscarPorSaldoMayor(double saldoMinimo) {
        List<Cuenta> cuentasConSaldoMayor = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getSaldo() > saldoMinimo) {
                cuentasConSaldoMayor.add(cuenta);
            }
        }
        return cuentasConSaldoMayor;
    }

    @Override
    public List<Cuenta> buscarPorTipoCuenta(String tipo) {
        List<Cuenta> cuentasPorTipo = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            if ((tipo.equalsIgnoreCase("Ahorros") && cuenta instanceof CuentaAhorros) ||
                    (tipo.equalsIgnoreCase("Corriente") && cuenta instanceof CuentaCorriente)) {
                cuentasPorTipo.add(cuenta);
            }
        }
        return cuentasPorTipo;
    }

    @Override
    public void agregarCuenta() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el número de la cuenta:");
        String numeroCuenta = scanner.nextLine();

        System.out.println("Ingrese el saldo inicial:");
        double saldo = scanner.nextDouble();

        System.out.println("Ingrese el tipo de cuenta (Ahorros/Corriente):");
        String tipoCuenta = scanner.next();

        if (tipoCuenta.equalsIgnoreCase("Ahorros")) {
            cuentas.add(new CuentaAhorros(numeroCuenta, saldo));
            System.out.println("Cuenta de Ahorros agregada con éxito.");
        } else if (tipoCuenta.equalsIgnoreCase("Corriente")) {
            System.out.println("Ingrese el límite de sobregiro:");
            double limiteSobregiro = scanner.nextDouble();
            cuentas.add(new CuentaCorriente(numeroCuenta, saldo, limiteSobregiro));
            System.out.println("Cuenta Corriente agregada con éxito.");
        } else {
            System.out.println("Tipo de cuenta no válido. Por favor, ingrese Ahorros o Corriente.");
        }
    }

    @Override
    public void guardarCuentas(String archivoSalida) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoSalida))) {
            for (Cuenta cuenta : cuentas) {
                if (cuenta instanceof CuentaAhorros) {
                    writer.write("Ahorros," + cuenta.getNumeroCuenta() + "," + cuenta.getSaldo());
                } else if (cuenta instanceof CuentaCorriente) {
                    CuentaCorriente cuentaCorriente = (CuentaCorriente) cuenta;
                    writer.write("Corriente," + cuenta.getNumeroCuenta() + "," + cuenta.getSaldo() + "," + cuentaCorriente.getLimiteSobregiro());
                }
                writer.newLine();  // Para escribir en una nueva línea
            }
            System.out.println("Cuentas guardadas exitosamente en " + archivoSalida);
        } catch (IOException e) {
            System.out.println("Error al guardar las cuentas: " + e.getMessage());
        }
    }
}
