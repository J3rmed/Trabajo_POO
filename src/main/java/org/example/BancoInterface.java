package org.example;

import java.util.List;

public interface BancoInterface {
    Cuenta buscarPorNumeroCuenta(String numeroCuenta);
    List<Cuenta> buscarPorSaldoMayor(double saldoMinimo);
    List<Cuenta> buscarPorTipoCuenta(String tipo);
    void agregarCuenta();
    void guardarCuentas(String archivoSalida);
}
