package K_IMMO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

public class Chose extends JFrame implements ParseI{
    private JButton desiste;
    private JButton acheter;
    private JLabel title;
    private JLabel tetl1;
    private JButton all;
    private JLabel infopers;
    private JLabel infoappart;
    private JPanel chose;
    private JLabel infoprom;

    String id = infoprom.getText();
    public Chose() {

        setContentPane(chose);
        setSize(700, 450);
        setVisible(true);

        acheter.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //achat
            dispose();
            Achat a = new Achat();
            a.my_info(infoappart.getText(),1);
            a.my_info(infopers.getText(),2);
        }
    });
    desiste.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //desistement
            dispose();
            try {
                Desistement d;
                d = new Desistement();
                d.my_info(infoprom.getText(),3);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    });
}

    @Override
    public void my_info(String str, int val) {
        if(val == 1){
            infoappart.setText(str);
            //infoappart.setVisible(false);
        }else if(val == 2){
            infopers.setText(str);
            //infopers.setVisible(false);
        } else if (val == 3) {
            infoprom.setText(str);
            //infoprom.setVisible(false);
        }
    }
}
