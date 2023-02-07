package K_IMMO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Objects;
import java.nio.file.*;
import java.util.Scanner;

public class Signup extends JFrame implements FilepersonneI{
    private JLabel title;
    private JTextField numcni;
    private JTextField nom;
    private JTextField prenom;
    private JTextField adresse;
    private JTextField tel;
    private JTextField prof;
    private JButton Signup;
    private JButton gologin;
    private JLabel subtiltle;
    private JLabel numcnilabel;
    private JLabel nomlabel;
    private JLabel prenomlabel;
    private JLabel adresselabel;
    private JLabel tellabel;
    private JLabel proflabel;
    private JPanel signup;

    public Signup() {
        setContentPane(signup);
        setSize(900, 450);
        setVisible(true);

        Signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*-----------------------------------------------getting data from array-------------------------------------------------------*/
                Personne[] personnes = new Personne[]{};
                try {
                    personnes = readfile("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\personnel.txt");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                //displayarrpersonne(personnes);

                if(Objects.equals(numcni.getText(), "") || Objects.equals(nom.getText(), "") || Objects.equals(prenom.getText(), "") || Objects.equals(adresse.getText(), "") || Objects.equals(tel.getText(), "") || Objects.equals(prof.getText(), ""))
                {
                    JOptionPane.showMessageDialog(null,"Vous devez remplir tous les champs!!!");
                }
            else
            {
                int count = 0;
                for(int i=0; i<personnes.length; i++) {
                    if (Integer.parseInt(numcni.getText()) == personnes[i].getNumCNI()){
                        count++;
                    }
                }

                if(count == 0){
                    Personne newpers = new Personne(Integer.parseInt(numcni.getText()),nom.getText(),prenom.getText(),adresse.getText(),Integer.parseInt(tel.getText()),prof.getText());
                    personnes = AddNew(newpers,personnes);
                    //displayarrpersonne(personnes);

                    try {
                        writefile("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\personnel.txt",personnes);
                    } catch (IOException ex) {
                        System.out.print(ex.getMessage());
                    }

                    JOptionPane.showMessageDialog(null,"Vous avez ete enregistre avec success");
                    dispose();
                    Home home = new Home();
                    home.my_info(String.valueOf(newpers.getNumCNI()),0);

                }else {
                    JOptionPane.showMessageDialog(null,"Ce numero de CNI a deja ete utiliser");
                }
             }
            }
        });
        gologin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login();
            }
        });
    }

    public Personne[] AddNew(Personne p, Personne parr[]) {
        int i,n=parr.length;

        Personne newarr[] = new Personne[n+1];

        for(i=0; i < n; i++){
          newarr[i] = parr[i];
        }
        newarr[n] = p;

        return newarr;
    }

    /*public void  displayarrpersonne(Personne p[]){
        for(int i = 0; i< p.length; i++)
        {
            System.out.println("New personne : ");
            System.out.println("NumCni : "+p[i].getNumCNI()+"\n Nom : "+p[i].getNom()+
                              "\n Prenom : "+p[i].getPrenom()+"\n Adresse : "+p[i].getAdresse()+
                              "\n Telephone : "+p[i].getTel()+"\n Profession : "+p[i].getProfession());
        }
    }*/

    @Override
    public void writefile(String filename, Personne[] p) throws IOException {
        BufferedWriter ow = new BufferedWriter(new FileWriter(filename));

        //ow.newLine();
        for (int i = 0; i < p.length; i++) {
            if(i == (p.length - 1))
            {
                ow.write(p[i].getNumCNI()+"\n");
                ow.write(p[i].getNom()+"\n");
                ow.write(p[i].getPrenom()+"\n");
                ow.write(p[i].getAdresse()+"\n");
                ow.write(p[i].getTel()+"\n");
                ow.write(p[i].getProfession());
                break;
            }
            ow.write(p[i].getNumCNI()+"\n");
            ow.write(p[i].getNom()+"\n");
            ow.write(p[i].getPrenom()+"\n");
            ow.write(p[i].getAdresse()+"\n");
            ow.write(p[i].getTel()+"\n");
            ow.write(p[i].getProfession()+"\n");
        }
        ow.close();

        System.out.println("Job is donne");
    }

    @Override
    public  Personne[] readfile(String filename) throws IOException {
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

            pers[index] = new Personne(Integer.parseInt(num),nom,prenom,add,Integer.parseInt(tel),prof);
            index = index + 1;
        }

        reader.close();
        return pers;
    }

    /*public  static void main(String[] args)
    {
        Signup p = new Signup();
    }*/

}
