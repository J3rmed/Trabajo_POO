package org.example;

public abstract class Cuenta {
    protected String numeroCuenta;
    protected double saldo;

    public Cuenta(String numeroCuenta, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public abstract void depositar(double monto);
    public abstract void retirar(double monto);

    @Override
    public String toString() {
        return "Número de Cuenta: " + numeroCuenta + ", Saldo: " + saldo;
    }
}
