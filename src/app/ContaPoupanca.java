/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author guiaw
 */
public class ContaPoupanca extends Conta {

    @Override
    public void depositar(double vlDeposito, int numContaBanco, int numAgencia, int numConta) {
        Transacoes t = new Transacoes();
        t.depositoCP(vlDeposito, numContaBanco, numAgencia, numConta);
        

    }

    @Override
    public void sacar(double vlDeposito, int numContaBanco, int numAgencia, int numConta) {
        Transacoes t = new Transacoes();
        t.saqueCP(vlDeposito, numContaBanco, numAgencia, numConta);
        

    }

    @Override
    public void extrato(int bancoB, int agenciaB, int contaB) {
        System.out.println("");
        System.out.println("======Extrato======");
        Transacoes t = new Transacoes();
        t.extrato(bancoB, agenciaB, contaB);
        

    }

}
