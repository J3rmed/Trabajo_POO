package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Leer cuentas desde un archivo
            List<Cuenta> cuentas = ArchivoCuentas.leerCuentas("src/main/resources/cuentas.txt");

            BancoInterface banco = new Banco(cuentas);

            // Agregar opción para añadir cuentas nuevas
            Scanner scanner = new Scanner(System.in);
            System.out.println("¿Desea agregar una nueva cuenta? (S/N):");
            String respuesta = scanner.nextLine();

            if (respuesta.equalsIgnoreCase("S")) {
                banco.agregarCuenta();
            }

            // Búsqueda por número de cuenta
            System.out.println("Ingrese el número de cuenta que desea buscar:");
            String numeroCuenta = scanner.nextLine();
            Cuenta cuentaBuscada = banco.buscarPorNumeroCuenta(numeroCuenta);
            if (cuentaBuscada != null) {
                System.out.println("Cuenta encontrada: " + cuentaBuscada);
            } else {
                System.out.println("Cuenta no encontrada.");
            }

            // Guardar las cuentas en el archivo de salida
            System.out.println("Guardando cuentas en el archivo...");
            banco.guardarCuentas("src/main/resources/cuentas.txt");
            System.out.println("Cuentas guardadas exitosamente.");

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
