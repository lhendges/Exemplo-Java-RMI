package br.com.unisinos.sdist.banco;

import javax.swing.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Correntista {

    public static final String HOST_NAME = "NOME_HOST";
    private static InternetBanking internetBanking = null;
    private static String[] operacoes = {"Consultar", "Depositar", "Sacar", "Sair"};

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        Registry registry = LocateRegistry.getRegistry(HOST_NAME);
        internetBanking = (InternetBanking) registry.lookup("//" + HOST_NAME + "/InternetBankingServer");

        boolean continuar = true;
        do {
            BigDecimal valor = null;
            int opcao = JOptionPane.showOptionDialog(null,
                    "Selecione a operação desejada", "Selecione a operação",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, operacoes, 1);
            switch (opcao) {
                case 0: //consultarSaldo
                    JOptionPane.showMessageDialog(null, "Saldo atual: " + internetBanking.consultarSaldo());
                    break;
                case 1: //depositar
                    valor = BigDecimal.valueOf(Long.parseLong(JOptionPane.showInputDialog("Digite o valor")));
                    internetBanking.depositarValor(valor);
                    break;
                case 2: //sacar
                    valor = BigDecimal.valueOf(Long.parseLong(JOptionPane.showInputDialog("Digite o valor")));
                    JOptionPane.showMessageDialog(null, "Valor retirado: " + internetBanking.sacarValor(valor));
                    break;
                case 3: //sair
                    continuar = false;
            }

        } while (continuar);

    }
}
