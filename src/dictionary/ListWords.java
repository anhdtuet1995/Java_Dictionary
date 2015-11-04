/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;

import java.awt.Container;
import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Anh
 */
public class ListWords extends JFrame{
    
    public ListWords(){
        //JFrame frame=new JFrame();
        super("Danh sách các từ");
        setSize(100,100);

        Container c = getContentPane();

        Vector headings = new Vector();
        headings.add("Tiếng Anh");
        headings.add("Tiếng Việt");
        //headings.add("Sửa");
    
        String [] cols = {"w_eng", "w_vie"};
        ResultSet resultSet = new JDBC().view("words", cols);
        Dictionary.loadData();
        TreeMap<String, String> word = Dictionary.getData();
      
        Vector rows = new Vector();
        
        for (Map.Entry<String, String> entry : word.entrySet()) {
            Vector row= new Vector();
            row.add(entry.getKey());
            row.add(entry.getValue());
            row.add("Sửa");
            rows.add(row);
        }
        
        
        JTable wordstable = new JTable(rows,headings);
        
        JScrollPane sp = new JScrollPane(wordstable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        c.add(sp);
        
        setLocationRelativeTo(null);
        setVisible(true);
        pack(); // get requried size based on components
    }
    
    
}
