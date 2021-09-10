package br.com.unisinos.sdist.banco;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class InternetBankingImpl extends UnicastRemoteObject implements InternetBanking {

    public static final String HOST_NAME = "NOME_HOST";

    private BigDecimal saldo;

    public InternetBankingImpl() throws RemoteException {
        super();
        this.saldo = BigDecimal.ZERO;
    }

    @Override
    public synchronized void depositarValor(BigDecimal valor) {
        System.out.println("Depositando " + valor + "R$");
        this.saldo = saldo.add(valor);
    }

    @Override
    public synchronized BigDecimal sacarValor(BigDecimal valor) {
        if (this.saldo.compareTo(valor) <= 0) {
            throw new ArithmeticException("Não existe saldo suficiente para realizar o saque!");
        }
        System.out.println("Sacando " + valor + "R$");
        this.saldo = this.saldo.subtract(valor);
        return valor;
    }

    @Override
    public synchronized BigDecimal consultarSaldo() {
        return this.saldo;
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(HOST_NAME);
            registry.rebind("//" + HOST_NAME + "/InternetBankingServer", new InternetBankingImpl());
            System.out.println("Server ready!");
        } catch (Exception ex) {
            System.out.println("Server error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
