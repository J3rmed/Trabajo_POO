package org.example;

public class CuentaCorriente extends Cuenta {
    private double limiteSobregiro;

    public CuentaCorriente(String numeroCuenta, double saldo, double limiteSobregiro) {
        super(numeroCuenta, saldo);
        this.limiteSobregiro = limiteSobregiro;
    }

    @Override
    public void depositar(double monto) {
        saldo += monto;
    }

    @Override
    public void retirar(double monto) {
        if (saldo + limiteSobregiro >= monto) {
            saldo -= monto;
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }

    // Metodo para obtener el límite de sobregiro
    public double getLimiteSobregiro() {
        return limiteSobregiro;
    }

    @Override
    public String toString() {
        return super.toString() + ", Límite de Sobregiro: " + limiteSobregiro;
    }
}
