package K_IMMO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

public class Login extends JFrame implements FilePersonneRead_I{
    private JTextField num;
    private JTextField tel;
    private JButton loginb;
    private JButton gotoSignupButton;
    private JLabel title;
    private JLabel subtitle;
    private JLabel numcnilabel;
    private JPanel login;
    private JLabel tellabel;

    public  Login()
    {
        setContentPane(login);
        setSize(600, 350);
        setVisible(true);
        gotoSignupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Signup signup = new Signup();
            }
        });
        loginb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(Objects.equals(num.getText(), "") || Objects.equals(tel.getText(), ""))
                {
                    JOptionPane.showMessageDialog(null,"Vous devez remplir tous les champs!!!");
                }
                else {
                    try {
                        Personne[] personnes = readfile("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\personnel.txt");
                        int count = 0;
                        int index = -1;

                        for(int i=0; i<personnes.length; i++) {
                            if (Integer.parseInt(num.getText()) == personnes[i].getNumCNI() && Integer.parseInt(tel.getText()) == personnes[i].getTel()){
                                count++;
                                index = i;
                            }
                        }

                        if(count == 1)
                        {
                            JOptionPane.showMessageDialog(null,"Thanks we have found you!!!");
                            dispose();
                            Home home = new Home();
                            home.my_info(String.valueOf(personnes[index].getNumCNI()),0);
                        }else{
                            JOptionPane.showMessageDialog(null,"Sorry you have not been found go to signup!!!");
                        }
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    @Override
    public Personne[] readfile(String filename) throws IOException {

        BufferedReader readersize = new BufferedReader(new FileReader(filename));
        String line = readersize.readLine();
        long count = Files.lines(Path.of(filename)).count();
        int size = (int) (count/6);

        Personne[] pers = new  Personne[size];

        File file = new File(filename);
        Scanner reader = new Scanner(file);

        String num,nom,prenom,add,tel,prof;

        int index = 0;

        while (reader.hasNext())
        {
            num = reader.nextLine();
            nom = reader.nextLine();
            prenom = reader.nextLine();
            add = reader.nextLine();
            tel = reader.nextLine();
            prof = reader.nextLine();

            pers[index] = new Personne(Integer.parseInt(num.trim()),nom,prenom,add,Integer.parseInt(tel.trim()),prof);
            index = index + 1;
        }

        reader.close();
        return pers;
    }
}
