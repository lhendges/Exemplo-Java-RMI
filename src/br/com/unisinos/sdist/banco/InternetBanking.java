package br.com.unisinos.sdist.banco;

import java.io.Serializable;
import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InternetBanking extends Remote, Serializable {

    void depositarValor(BigDecimal valor) throws RemoteException;

    BigDecimal sacarValor(BigDecimal valor) throws RemoteException;

    BigDecimal consultarSaldo() throws RemoteException;
}
