/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

/**
 *
 * @author Anh
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
 
public class JDBC {
 
    private Connection connection;
 
    public JDBC(){
        try {
            Class.forName("com.mysql.jdbc.Driver");  // Nạp driver cho việc kết nối
            // Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); dùng cái này nếu là sqlserver của microsoft
 
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
 
        String url = "jdbc:mysql://127.0.0.1:3306/dictionary?useUnicode=true&characterEncoding=UTF-8"; // trong đó ://127.0.0.1:3306/test là tên và đường dẫn tới CSDL.
        try {
            // Kết nối tới CSDL theo đường dẫn url, tài khoản đăng nhập là root, pass là ""
            connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Kết nối thành công");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ResultSet view(String table, String [] cols){
        ResultSet resultSet = null;
        try {
            Statement statement = (Statement) connection.createStatement();
            String sql = "SELECT ";
            if(cols == null || cols.length == 0){
                sql += "* FROM";
            }else{
                for(int i = 0 ; i < cols.length; i++){
                    sql += "`" + cols[i] + "`, ";
                }
                sql += ";";
                sql = sql.replace("`, ;", "` FROM");
            }
            sql += " " + table;
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            return null;
        }
        return resultSet;
    }
     
    public boolean insert(String table, String [] cols, Vector vecto){
        try {
            Statement statement =  (Statement) connection.createStatement();
            String tmp = new String();
            for(int i = 0; i < cols.length; i++){
                tmp += cols[i];
                if( i < cols.length-1){
                    tmp+= ",";
                }
            }
            String sql = "insert into " + table + "(" + tmp + ")" + " values(";
            for(int i = 0; i < vecto.size(); i++){
                sql += "'" + vecto.elementAt(i).toString() + "',";
            }
            sql += ")";
            sql = sql.replace("',)", "')");
             
            if(statement.executeUpdate(sql) >= 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         
        return false;
    }
     
    public boolean update(String table, String[] cols, Vector value, String[] colsWhere, Vector valueWhere){
        try {
            Statement statement = (Statement) connection.createStatement();
            String sql = "update " + table + " set ";
            for(int i = 0 ; i < cols.length; i++){
                sql += "`" + cols[i] + "` = '" + value.elementAt(i) + "', ";
            }
            sql += ";";
            sql = sql.replace("', ;", "' WHERE ");
             
            for(int i = 0 ; i < colsWhere.length; i++){
                sql += "`" + colsWhere[i] + "` = '" + valueWhere.elementAt(i) + "' and ";
            }
            sql += ";";
            sql = sql.replace("' and ;", "'");
            System.out.print(sql);
            statement.executeUpdate(sql);
 
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
     
    public boolean delete(String table, String[] colsWhere, Vector valueWhere){
        try {
            Statement statement = (Statement) connection.createStatement();
            String sql = "DELETE FROM" + table;
             
            if( colsWhere.length > 0){
                sql += " WHERE ";
                for(int i = 0 ; i < colsWhere.length; i++){
                    sql += "`" + colsWhere[i] + "` = '" + valueWhere.elementAt(i) + "' and ";
                }
                sql += ";";
                sql = sql.replace("' and ;", "'");
            }
 
            statement.executeUpdate(sql);
 
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
         
        return false;
    }
}
