/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.HeadlessException;

/**
 *
 * @author guiaw
 */
public class App {

    public static void main(String[] args) {
        Conexoes c = new Conexoes();
        ContaCorrente cc = new ContaCorrente();
        ContaPoupanca cp = new ContaPoupanca();
        Transacoes t = new Transacoes();
        try {
            c.conectar();

            /*  
                Comandos utiliazdos para criar as tabelas do banco de dados e inserir usuários
                só deve ser rodada uma vez, caso ocorra erro utilize o método 
                 -- Conexoes c = new Conexoes(); 
                 -- c.deletar(); == esste metodo apaga as tabelas criadas bem como seu conteudo
            
                c.crateTables();
                c.insertBanco(); 
                c.insertAgencia();
                 c.insertConta();
             */
            
            
            int agencia = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("===Entre na conta===\n "
                    + "Digite a sua agencia"));
            int conta = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Digite sua conta"));
            /*
            2 contas
            1- Agencia 100, conta 123, tpconta 'CC'1
            2- Agencia 100, conta 321, tpconta 'CP'
            */
            
                if (c.tpconta(conta).equalsIgnoreCase("cc")) {

                for (int x = 0; x < 10; x++) {

                    int op = Integer.parseInt(javax.swing.JOptionPane.showInputDialog(""
                            + "=======MENU=======\n"
                            + "===Conta Corrente===\n"
                            + "1 - Saque\n"
                            + "2 - Depósito\n"
                            + "3 - Saldo\n"
                            + "4 - Sair"));

                    switch (op) {
                        case 1:
                            double vlsaque = Double.parseDouble(javax.swing.JOptionPane.showInputDialog("Digite o valor a sacar"));

                            cc.sacar(vlsaque, 10, agencia, conta);

                            break;

                        case 2:
                            double vldeposito = Double.parseDouble(javax.swing.JOptionPane.showInputDialog("Digite o valor a depositar"));

                            cc.depositar(vldeposito, 10, agencia, conta);

                            break;

                        case 3:
                            cc.extrato(10, agencia, conta);
                            break;

                        case 4:
                            c.fecharConexao();
                            System.exit(0);
                            break;
                    }

                }
            } else if (c.tpconta(conta).equalsIgnoreCase("cp")) {
                for (int x = 0; x < 10; x++) {
                    int op = Integer.parseInt(javax.swing.JOptionPane.showInputDialog(""
                            + "=======MENU=======\n"
                            + "===Conta Poupança===\n"
                            + "1 - Saque\n"
                            + "2 - Depósito\n"
                            + "3 - Saldo\n"
                            + "4 - Sair"));

                    switch (op) {
                        case 1:
                            double vlsaque = Double.parseDouble(javax.swing.JOptionPane.showInputDialog("Digite o valor a sacar"));

                            cp.sacar(vlsaque, 10, agencia, conta);

                            break;

                        case 2:
                            double vldeposito = Double.parseDouble(javax.swing.JOptionPane.showInputDialog("Digite o valor a depositar"));

                            cp.depositar(vldeposito, 10, agencia, conta);

                            break;

                        case 3:
                            cp.extrato(10, agencia, conta);
                            break;

                        case 4:
                            c.fecharConexao();
                            System.exit(0);
                            break;
                    }

                }
            }

        } catch (HeadlessException | NumberFormatException e) {

            System.out.println(e.getMessage());
        }

    }
}
