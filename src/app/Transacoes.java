/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author guiaw
 */
public class Transacoes {

    Connection conn = null;
    Statement stm = null;
    ResultSet rs = null;

    public ResultSet select(int banco, int agencia, int conta) {
        try {
            String url = "jdbc:sqlite:C:\\Users\\guiaw\\banco.db";
            conn = DriverManager.getConnection(url);
            stm = conn.createStatement();
            rs = stm.executeQuery(""
                    + " select banco.nomebanco, agencia.numagencia, conta.numconta, conta.saldo, conta.tpconta"
                    + " from conta, banco, agencia "
                    + " where conta.cdagencia = agencia.cdagencia "
                    + " and agencia.cdbanco = banco.cdbanco "
                    + " and conta.numconta =" + conta + " "
                    + " and agencia.numagencia =" + agencia + " "
                    + " and banco.numbanco =" + banco);
           
          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    //                 valor         numero banco   numero agencia   numero conta 
    public void depositoCC(double vl, int contaB, int agenciaB, int conta) {

        try {

            String url = "jdbc:sqlite:C:\\Users\\guiaw\\banco.db";
            conn = DriverManager.getConnection(url);

            select(contaB, agenciaB, conta);
            String tpconta = rs.getString("tpconta");
           

                double saldo = rs.getDouble("saldo");
                saldo = saldo + vl;

                stm = conn.createStatement();
                System.out.println("Depositando " + vl);
               
                String sql = "UPDATE conta set saldo =" + saldo + " where numconta =" + conta;
                
                stm.executeUpdate(sql);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
                System.out.println("" + dtf.format(LocalDateTime.now()));
                System.out.println("DepositoCC: Operacao concluida com sucesso! \n");
                stm.close();
                rs.close();
          

        } catch (SQLException e) {
            
            System.out.println("DepósitoCC : ERRO");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    public void saqueCC(double vl, int contaB, int agenciaB, int conta) {

        try {
            String url = "jdbc:sqlite:C:\\Users\\guiaw\\banco.db";
            conn = DriverManager.getConnection(url);

            select(contaB, agenciaB, conta);
            String tpconta = rs.getString("tpconta");
           

                double saldo = rs.getDouble("saldo");
                saldo = saldo - vl;

                stm = conn.createStatement();
                if (saldo <= -1001) {
                    System.out.println("Limite da conta");
                    System.exit(0);
                } else {
                    System.out.println("Sacando " + vl);
                    
                    String sql = "UPDATE conta set saldo =" + saldo + " where numconta =" + conta;

                    stm.executeUpdate(sql);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
                    System.out.println("" + dtf.format(LocalDateTime.now()));
                    System.out.println("SaqueCC: Operacao concluida com sucesso! \n");
                }
          
            stm.close();
            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println("SaqueCC ERRO");
            System.exit(0);
        }

    }

    //                 valor         numero banco   numero agencia   numero conta 
    public void depositoCP(double vl, int contaB, int agenciaB, int conta) {

        try {
            String url = "jdbc:sqlite:C:\\Users\\guiaw\\banco.db";
            conn = DriverManager.getConnection(url);

            select(contaB, agenciaB, conta);

            String tpconta = rs.getString("tpconta");
          

                double saldo = rs.getDouble("saldo");
                saldo = saldo + vl;

                stm = conn.createStatement();
                System.out.println("Depositando " + vl);
                String sql = "UPDATE conta set saldo =" + saldo + " where numconta =" + conta;
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        System.out.println("" + dtf.format(LocalDateTime.now()));
                System.out.println("DepósitoCP : Operacao concluida com sucesso! \n");
                stm.executeUpdate(sql);

          
            stm.close();
            rs.close();

        } catch (SQLException e) {
            
            System.out.println("DepósitoCP : ERRO");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    public void saqueCP(double vl, int contaB, int agenciaB, int conta) {

        try {
            String url = "jdbc:sqlite:C:\\Users\\guiaw\\banco.db";
            conn = DriverManager.getConnection(url);

            select(contaB, agenciaB, conta);

            String tpconta = rs.getString("tpconta");
           

                double saldo = rs.getDouble("saldo");
                saldo = saldo - vl;
                System.out.println("SALDO" + saldo);
                stm = conn.createStatement();
                if (saldo <= -1001) {
                    System.out.println("Limite da conta");
                    System.exit(0);
                } else {
                    System.out.println("Scando " + vl);
                    String sql = "UPDATE conta set saldo =" + saldo + " where numconta =" + conta;
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        System.out.println("" + dtf.format(LocalDateTime.now()));
                    System.out.println("SaqueCP: Operacao concluida com sucesso! \n");
                    stm.executeUpdate(sql);
                }
            
            stm.close();
            rs.close();

        } catch (SQLException e) {
            System.out.println("SaqueCP : ERRO");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            
            System.exit(0);
        }

    }

    public void extrato(int bancoB, int agenciaB, int contaB) {
        select(bancoB, agenciaB, contaB);
        try {
            while (rs.next()) {
                System.out.println("Comecando");
                String nomeBanco = rs.getString("nomebanco");
                int conta = rs.getInt("numconta");
                int agencia = rs.getInt("numagencia");
                String tpcontaa = rs.getString("tpconta");
                double saldo = rs.getDouble("saldo");

                System.out.println("Banco : " + nomeBanco);
                System.out.println("Agencia : " + agencia);
                System.out.println("Conta : " + conta);
                System.out.println("TpConta : " + tpcontaa);
                System.out.println("Saldo :  " + saldo);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
                System.out.println("" + dtf.format(LocalDateTime.now()));
                System.out.println();
            }
            stm.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Extrato: ERRO");
            System.out.println(e.getMessage());
           
        }

    }

}
