/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

/**
 *
 * @author Anh
 */
public class Dictionary{
   private static TreeMap<String, String> word = new TreeMap<String, String>();
   
   public static void loadData(){
        String [] cols = {"w_eng", "w_vie"};
        ResultSet resultSet = new JDBC().view("words", cols);
        
        try {
            while(resultSet.next()){
                word.put(resultSet.getString(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public static TreeMap<String, String> getData(){
        return word;
    }
}