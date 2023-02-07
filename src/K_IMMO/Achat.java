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
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

//open this from liste of promise
public class Achat extends JFrame implements ParseI, PromosseRead_I {
    private JPanel achat;
    private JPanel achatframe;
    private JTextField methode;
    private JButton valideButton;
    private JLabel guide;
    private JLabel title;
    private JPanel apppart;
    private JLabel infoapp;
    private JLabel numerolabel;
    private JLabel superficielabel;
    private JLabel nbrchambrelable;
    private JLabel prixlabel;
    private JLabel numero;
    private JLabel superficie;
    private JLabel chambre;
    private JLabel prix;
    private JPanel infos;
    private JLabel datelebel;
    private JLabel directeur;
    private JLabel labelavocat;
    private JLabel avocat;
    private JLabel section2;
    private JTextField prixfinal;
    private JLabel payelabel;
    private JLabel pricelabel;
    private JLabel prixpayerlabel;
    private JLabel prixpayer;
    private JLabel Methodel;
    private JLabel mtxt;
    private JLabel infopers;
    private JLabel infoappart;

    Appartement[] appartements = new Appartement[]{new Appartement(21,58.45,4,50000,false),
            new Appartement(22,35.55,2,35000,false),
            new Appartement(23,70.80,6,70000,false),
            new Appartement(24,50.00,3,40000,true)};

    Avocat[] avocats = new Avocat[]{new Avocat(31,"Goueth","Alexandra","Nkolmesseng",658791346),
            new Avocat(31,"Ekobo","Jordan","Nkolmesseng",699791346)};

    Directeur[] directeurs = new Directeur[]{new Directeur(40,"Messi", "Lionel")};

public Achat() {

    setContentPane(achat);
    setSize(900, 450);
    setVisible(true);

    valideButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           // replace this with promise collected on click of a promise from promise liste
            LocalDate dateObj = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String date = dateObj.format(formatter);

           //Promesse promesse = new Promesse(12,21,date,31,25000);

            ///valider formulaire
            if(Objects.equals(methode.getText(), "") || Objects.equals(prixfinal.getText(), ""))
            {
                JOptionPane.showMessageDialog(null,"Vous devez remplir tous les champs!!!");
            }
            else{
                double prixf = Float.parseFloat(prixfinal.getText());
                prixfinal.setText("");

                Avocat avocatchoisi = avocats[1];
                Directeur directeur1 = directeurs[0];
                Promesse promchoisi = null;

                try {
                    Promesse[] p = readfilep("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\promesse.txt");
                    int id_app = Integer.parseInt(infopers.getText());

                    for(int t = 0; t < p.length; t++)
                    {
                        if(p[t].getId_app() == id_app){
                            promchoisi = p[t];
                        }
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }


                for(int i = 0; i < appartements.length; i++)
                {
                    if(promchoisi.getId_app() == appartements[i].getNum())
                    {
                        if(prixf == (appartements[i].getPrix() - promchoisi.getAvance())){
                            numero.setText(String.valueOf(appartements[i].getNum()));
                            superficie.setText(String.valueOf(appartements[i].getSuperficie()));
                            chambre.setText(String.valueOf(appartements[i].getNbr_chambre()));
                            prix.setText(String.valueOf(appartements[i].getPrix()));

                            directeur.setText(directeur1.getNom()+" "+directeur1.getPrenom());
                            avocat.setText(avocatchoisi.getNom()+" "+avocatchoisi.getPrenom());
                            prixpayer.setText(String.valueOf(prixf));
                            mtxt.setText(methode.getText());

                            appartements[i].setIsvendu(true);

                            JOptionPane.showMessageDialog(null,"Merci votre achat a ete acte!!!");
                        }else {
                            JOptionPane.showMessageDialog(null,"Votre prix n'est pas valide!!!");
                        }
                    }
                }
            }
        }
    });
}
    /*public  static void main(String[] args)
    {
        Achat a = new Achat();
    }*/

    @Override
    public void my_info(String str, int val) {
        if(val == 1){
            infoappart.setText(str);
            //infoappart.setVisible(false);
        }else if(val == 2){
            infopers.setText(str);
            //infopers.setVisible(false);
        }
    }

    @Override
    public Promesse[] readfilep(String filename) throws IOException, ParseException {
        BufferedReader readersize = new BufferedReader(new FileReader(filename));
        String line = readersize.readLine();
        long count = Files.lines(Path.of(filename)).count();
        int size = (int) (count/6);

        Promesse[] promesses = new Promesse[size];

        File file = new File(filename);
        Scanner reader = new Scanner(file);

        String id_prom,id_acq,id_app,dateest,id_avocat,avance;

        int index = 0;

        while (reader.hasNext())
        {
            id_prom = reader.nextLine();
            id_acq = reader.nextLine();
            id_app = reader.nextLine();
            dateest = reader.nextLine();
            id_avocat = reader.nextLine();
            avance = reader.nextLine();

            promesses[index] = new Promesse(Integer.parseInt(id_prom),Integer.parseInt(id_acq),Integer.parseInt(id_app),dateest,Integer.parseInt(id_avocat),Double.parseDouble(avance));
            index = index + 1;
        }
        reader.close();
        return  promesses;
    }
}
