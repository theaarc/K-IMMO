package K_IMMO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ParseI {
    private JLabel Title;
    private JPanel home;
    private JPanel jpanel1;
    private JPanel jpanel2;
    private JPanel jpanel3;
    private JPanel jpanel4;
    private JLabel prixappart1;
    private JLabel numappartlabel1;
    private JButton visite1;
    private JButton visite2;
    private JButton visite3;
    private JButton visite4;
    private JLabel numappartlabel2;
    private JLabel prixappart2;
    private JLabel numappartlabel3;
    private JLabel prixappart3;
    private JLabel numappartlabel4;
    private JLabel prixappart4;
    private JLabel numappart1;
    private JLabel numappart2;
    private JLabel numappart3;
    private JLabel numappart4;
    private JPanel infos;
    private JLabel infopers;

    Appartement[] appartements = new Appartement[]{new Appartement(21,58.45,4,50000,false),
                                                   new Appartement(22,35.55,2,35000,false),
                                                   new Appartement(23,70.80,6,70000,false),
                                                   new Appartement(24,50.00,3,40000,false)};

    public Home() {
        setContentPane(home);
        setSize(600, 450);
        setVisible(true);

        String numtxt,prixtxt;

        numappart1.setText(String.valueOf(appartements[0].getNum()));
        prixtxt = prixappart1.getText();
        prixappart1.setText(prixtxt+" "+String.valueOf(appartements[0].getPrix()));

        numappart2.setText(String.valueOf(appartements[1].getNum()));
        prixtxt = prixappart2.getText();
        prixappart2.setText(prixtxt+" "+String.valueOf(appartements[1].getPrix()));


        numappart3.setText(String.valueOf(appartements[2].getNum()));
        prixtxt = prixappart3.getText();
        prixappart3.setText(prixtxt+" "+String.valueOf(appartements[2].getPrix()));


        numappart4.setText(String.valueOf(appartements[3].getNum()));
        prixtxt = prixappart4.getText();
        prixappart4.setText(prixtxt+" "+String.valueOf(appartements[3].getPrix()));

        /*
        List panels = new ArrayList();

        for(int i = 0; i < appartements.length; i ++)
        {
            if(appartements[i].getisvendu() == false){

                JPanel panel = new JPanel();
                panel.setBounds(40,80,200,200);

                JLabel label1 = new JLabel(Integer.toString(appartements[i].getNum()));
                JLabel label2 = new JLabel(String.valueOf(appartements[i].getPrix()));

                JPanel orderList = new JPanel();
                orderList.setLayout(new BoxLayout(orderList, BoxLayout.PAGE_AXIS));

                JPanel orderListRow = new JPanel(); //Creating the orderListRow JPanel
                orderListRow.add(label1);

                JPanel additionalPanel = new JPanel(); //Creating the additionalPanel JPanel
                additionalPanel.add(label2);

                orderList.add(orderListRow); //Adding orderListRow into orderList
                orderList.add(additionalPanel); //Adding additionalPanel into orderList

                this.setLayout(new GridLayout(1,2));
                this.add(orderList); //Setting orderList into JFrame

                this.pack(); //Setting JFrame size. This will only take required space
                this.setVisible(true); //Making JFrame Visible
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }*/


        visite1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                VisitEst v = new VisitEst();
                v.my_info(numappart1.getText(),1);
                v.getinfoappart();
                v.my_info(infopers.getText(),2);
                v.setVisible(true);
            }
        });
        visite2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                VisitEst v = new VisitEst();
                v.my_info(numappart2.getText(),1);
                v.getinfoappart();
                v.my_info(infopers.getText(),2);
                v.setVisible(true);
            }
        });
        visite3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                VisitEst v = new VisitEst();
                v.my_info(numappart3.getText(),1);
                v.getinfoappart();
                v.my_info(infopers.getText(),2);
                v.setVisible(true);
            }
        });
        visite4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                VisitEst v = new VisitEst();
                v.my_info(numappart4.getText(),1);
                v.getinfoappart();
                v.my_info(infopers.getText(),2);
                v.setVisible(true);
            }
        });

    }

    public void my_info(String str, int val) {
        if (val == 0) {
            infopers.setText(str);
            infopers.setVisible(false);
        }
    }
    //public  static void main(String[] args) {Home h = new Home();}
}
