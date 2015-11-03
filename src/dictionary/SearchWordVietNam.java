/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;


import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.TreeMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Anh
 */

public class SearchWordVietNam extends JFrame{
    private JTextField eng;
    private JTextField vie;
    private JButton btn;

    public SearchWordVietNam() {
        super("Việt - Anh");
        
        Dictionary.loadData();
        TreeMap<String, String> word = Dictionary.getData();
        
        GridBagLayout gbl  = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.BOTH;

        eng = new JTextField(20);
        vie = new JTextField(20);
        btn = new JButton("Tìm");
        btn.addActionListener((ActionEvent e) -> {
            if (  vie.getText().length() > 0 ) {
                if (word.containsValue(vie.getText())){
                    eng.setText(SearchWordVietNam.getKeyFromValue(word, vie.getText()));
                }
                else{
                    JOptionPane.showMessageDialog( SearchWordVietNam.this, "Không tìm thấy từ này!","Search Word", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else
                JOptionPane.showMessageDialog( SearchWordVietNam.this, "Vui lòng không để trống ô!","Search Word", JOptionPane.ERROR_MESSAGE);
        });

        Container c = getContentPane();
        c.setLayout(gbl);
        
        //add eng text
        gbc.anchor = GridBagConstraints.EAST;
        c.add(new JLabel("Nhập từ tiếng Việt: "), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        c.add(vie);
        
        //add vie text
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        c.add(new JLabel("Dịch sang tiếng Anh: "), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        c.add(eng, gbc);
        
        //add btn
        gbc.gridx  = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        c.add(btn, gbc);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
    }
    
    public static String getKeyFromValue(TreeMap<String, String> hm, String value) {
        for (String o : hm.keySet()) {
          if (hm.get(o).equals(value)) {
            return o;
          }
        }
        return null;
    }
}