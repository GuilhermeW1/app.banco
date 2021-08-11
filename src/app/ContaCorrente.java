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
public class ContaCorrente extends Conta {

    String banco;

    public ContaCorrente() {
        int conta;

    }

    @Override
    public void extrato(int bancoB, int agenciaB, int contaB) {
        System.out.println("");
        System.out.println("======Extrato======");
        Transacoes t = new Transacoes();
        t.extrato(bancoB, agenciaB, contaB);
       

    }

    @Override
    public void depositar(double vlDeposito, int numContaBanco, int numAgencia, int numConta) {
        Transacoes t = new Transacoes();
        t.depositoCC(vlDeposito, numContaBanco, numAgencia, numConta);
        

    }

    @Override
    public void sacar(double vlDeposito, int numContaBanco, int numAgencia, int numConta) {
        Transacoes t = new Transacoes();
        t.saqueCC(vlDeposito, numContaBanco, numAgencia, numConta);
        

    }

}
