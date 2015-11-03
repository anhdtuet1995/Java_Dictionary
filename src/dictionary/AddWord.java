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
import java.awt.event.ActionListener;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Anh
 */
public class AddWord extends JFrame{
    private JTextField eng;
    private JTextField vie;
    private JButton btn;
        
    public AddWord() {
        super("Thêm từ");
        
        Dictionary.loadData();
        TreeMap<String, String> word = Dictionary.getData();
        String[] cols = {"w_eng", "w_vie"};
        
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        
        eng = new JTextField(30);
        vie = new JTextField(30);
        btn = new JButton("Thêm");
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(eng.getText().length() == 0 && vie.getText().length() == 0){
                    JOptionPane.showMessageDialog( AddWord.this, "Không được để trống!","Add Word", JOptionPane.ERROR_MESSAGE);
                }
                else if(word.containsKey(eng.getText())){
                    JOptionPane.showMessageDialog( AddWord.this, "Từ này đã có trong từ điển!","Add Word", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    Vector value = new Vector();
                    value.add(eng.getText());
                    value.add(vie.getText());
                    new JDBC().insert("words", cols, value);
                    eng.setText("");
                    vie.setText("");
                    eng.requestFocus();
                    JOptionPane.showMessageDialog( AddWord.this, "Thêm từ thành công!","Add Word", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        Container c = getContentPane();
        c.setLayout(gbl);
        
        //add eng text
        gbc.anchor = GridBagConstraints.EAST;
        c.add(new JLabel("Nhập từ tiếng Anh: "), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        c.add(eng);
        
        //add vie text
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        c.add(new JLabel("Nhập nghĩa: "), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        c.add(vie, gbc);
        
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
}
