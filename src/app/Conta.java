/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author guiaw
 */
public abstract class Conta {

    protected String nome;
    protected int numConta;
    protected String tpconta;

    public void inserirConta() {
    }

    public void depositar(double vlDeposito, int numContaBanco, int numAgencia, int numConta) {
    }

    public void sacar(double vlDeposito, int numContaBanco, int numAgencia, int numConta) {
    }

    public void extrato(int bancoB, int agenciaB, int contaB) {

    }

}
