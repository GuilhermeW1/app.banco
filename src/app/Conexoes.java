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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexoes {
    
    private Connection conn = null;
    private Statement stm = null;
    private ResultSet rs = null;

    public void conectar() {

        try {

            String url = "jdbc:sqlite:C:\\Users\\guiaw\\banco.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Conectar: Connection to SQLite has been established. \n");
            stm = conn.createStatement();

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void fecharConexao() {
        try {
            System.out.println("FecharConexão: close.. \n");
            rs.close();
            stm.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void crateTables() {
        try {
            String url = "jdbc:sqlite:C:\\Users\\guiaw\\banco.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Create: Connection to SQLite has been established. \n");
            stm = conn.createStatement();

            System.out.println("Criando tabela banco");
            String banco = " create table if not exists banco ("
                    + "cdbanco integer,"
                    + "numbanco integer, "
                    + "nomebanco varchar(100),"
                    + "constraint pk_banco primary key (cdbanco));";
            System.out.println("Create: table banco created");
            stm.executeUpdate(banco);

            System.out.println(" criando agencia ....");
            String agencia = "create table if not exists agencia ("
                    + "cdagencia integer,"
                    + "numagencia integer,"
                    + "cdbanco integer,"
                    + "constraint pk_agencia primary key(cdagencia),"
                    + "constraint fk_agenciaBanco foreign key (cdbanco) references banco)";

            stm.executeUpdate(agencia);
            System.out.println("Agencia feita");
            System.out.println("Criando ciente");

            String cliente = "create table if not exists conta ("
                    + "cdconta integer,"
                    + "cdagencia integer,"
                    + "numconta integer,"
                    + "nmcliente varchar(100),"
                    + "saldo double,"
                    + "tpconta varchar(2),"
                    + "constraint pk_conta primary key(cdconta),"
                    + "constraint fk_contaAgencia foreign key (cdagencia) references agencia)";

            stm.executeUpdate(cliente);
            stm.close();
            rs.close();
            System.out.println("Cliente feito");
            System.out.println("Create: Banco criado");
        } catch (SQLException e) {
            System.out.println("Erro create");
            System.out.println(e.getMessage());
        }

    }

    public void insertBanco() {
        try {
            String url = "jdbc:sqlite:C:\\Users\\guiaw\\banco.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Insert: Banco de dados conectado com sucesso! \n");
            stm = conn.createStatement();

            System.out.println("Insetindo no banco");
            String sql = "insert into banco (cdbanco, numbanco, nomebanco) "
                    + "values (1,10, 'Unibank')";
            stm.executeUpdate(sql);

            System.out.println("Insertions feitas");
            stm.close();
            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println("InsertDB");
            System.exit(0);
        }
        System.out.println("Insert: Registros adicionados com sucesso! \n");
    }

    public void insertAgencia() {

        try {
            String url = "jdbc:sqlite:C:\\Users\\guiaw\\banco.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Insert: Banco de dados conectado com sucesso! \n");
            stm = conn.createStatement();

            System.out.println("Inserindo na agencia ");
            String sql1 = "insert into agencia (cdagencia, numagencia, cdbanco)"
                    + "values (1, 100, 1) ";
            stm.executeUpdate(sql1);

            System.out.println("Insertions feitas");
            stm.close();
            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println("InsertDB");
            System.exit(0);
        }
        System.out.println("Insert: Registros adicionados com sucesso! \n");
    }

    public void insertConta() {
        try {
            String url = "jdbc:sqlite:C:\\Users\\guiaw\\banco.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Insert: Banco de dados conectado com sucesso! \n");
            stm = conn.createStatement();

            System.out.println("Inserindo na conta");
            String sql2 = "insert into conta( cdconta, cdagencia, numconta, nmcliente, saldo, tpconta)"
                    + "values (2, 1, 123, 'Joao', 100.00, 'CC' ), (1, 1, 321, 'Maria', 100.00, 'CP' ) ";
            stm.executeUpdate(sql2);

            System.out.println("Insertions feitas");
            stm.close();
            rs.close();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println("InsertDB");
            System.exit(0);
        }
        System.out.println("Insert: Registros adicionados com sucesso! \n");
    }
    
    public String tpconta( int conta){
        String tpconta ="";
        try{  
        String url = "jdbc:sqlite:C:\\Users\\guiaw\\banco.db";
        conn = DriverManager.getConnection(url);
        System.out.println("Insert: Banco de dados conectado com sucesso! \n");
        stm = conn.createStatement();
        
        
        String sql ="Select conta.tpconta from conta, agencia"
                + " where conta.cdagencia = agencia.cdagencia"
                + " and conta.numconta ="+conta;
        rs = stm.executeQuery(sql);
        tpconta = rs.getString("tpconta");
            System.out.println("cheguei em tp conta ");
        stm.close();
        rs.close();
        }catch(SQLException e){
            
            System.out.println(e.getMessage());}
        return tpconta;
    }   
    
    /* 
    public ResultSet select(int banco, int agencia, int conta){
           try{  
        String url = "jdbc:sqlite:C:\\Users\\guiaw\\banco.db";
            conn = DriverManager.getConnection(url);

            System.out.println("selectDB (200): Banco de dados conectado com sucesso! \n");

            stm = conn.createStatement();
            rs = stm.executeQuery(""
                    + " select banco.nomebanco, agencia.numagencia, conta.numconta, conta.saldo, conta.tpconta"
                    + " from conta, banco, agencia "
                    + " where conta.cdagencia = agencia.cdagencia "
                    + " and agencia.cdbanco = banco.cdbanco "
                    + " and conta.numconta ="+conta +" "
                    + " and agencia.numagencia ="+agencia+" "
                    + " and banco.numbanco ="+banco);
            
           }catch(SQLException e){
               System.out.println(e.getMessage());
           }
            return rs;
    }
    //                 valor         numero banco   numero agencia   numero conta 
    public void depositoCC (double vl, int contaB, int agenciaB, int conta){
        
         try {
            String url = "jdbc:sqlite:C:\\Users\\guiaw\\banco.db";
            conn = DriverManager.getConnection(url);
            System.out.println("updateDB: Banco de dados conectado com sucesso! \n");

            select(contaB, agenciaB, conta);
            
            String tpconta =rs.getString("tpconta");
            if(tpconta.equalsIgnoreCase("CC")){
            
            double saldo = 0;
            saldo = rs.getDouble("saldo");
            saldo = saldo+vl;
             System.out.println("SALDO" +saldo);
            stm = conn.createStatement();    
            
            String sql = "UPDATE conta set saldo =" + saldo + " where numconta =" + conta;
            System.out.println("updateDB: Operacao concluida com sucesso! \n");
            stm.executeUpdate(sql);
            }else {System.out.println("Tipos de conta diferentes");
            System.exit(0);}
            
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println("UpdateDB");
            System.exit(0);
        }
        
        
    
    }
    
    public void saqueCC (double vl, int contaB, int agenciaB, int conta){
        
         try {
            String url = "jdbc:sqlite:C:\\Users\\guiaw\\banco.db";
            conn = DriverManager.getConnection(url);
            System.out.println("updateDB: Banco de dados conectado com sucesso! \n");

            select(contaB, agenciaB, conta);
            
            String tpconta =rs.getString("tpconta");
            if(tpconta.equalsIgnoreCase("CC")){
            
            double saldo = 0;
            saldo = rs.getDouble("saldo");
            saldo = saldo-vl;
            System.out.println("SALDO" +saldo);
            stm = conn.createStatement();    
            if(saldo <= -1001){
                System.out.println("Limite da conta");
                System.exit(0);
            }else{
            
            String sql = "UPDATE conta set saldo =" + saldo + " where numconta =" + conta;
            System.out.println("updateDB: Operacao concluida com sucesso! \n");
            stm.executeUpdate(sql);
            }
            }else {System.out.println("Tipos de conta diferentes");
            System.exit(0);}
            
            
        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println("UpdateDB");
            System.exit(0);
        }
        
        
    
    }
    
    */

    public void deletar() {
        try {
            String url = "jdbc:sqlite:C:\\Users\\guiaw\\banco.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Deletar: Banco de dados conectado com sucesso! \n");
            stm = conn.createStatement();

            String banco = "drop table banco";
            String agencia = "drop table agencia";
            String conta = "drop table conta";
            stm.executeUpdate(banco);
            stm.executeUpdate(agencia);
            stm.executeUpdate(conta);
            System.out.println("Deletar: Deletados");
            stm.close();
            rs.close();

        } catch (SQLException E) {
            System.out.println(E.getMessage());
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStm() {
        return stm;
    }

    public void setStm(Statement stm) {
        this.stm = stm;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    
    
    
    

}
