package K_IMMO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

public class PromesseEst extends JFrame implements Filepromesse_I, ParseI, FilePersonneRead_I, Clientadd_I, Acquereur_I, ClientRead_I{
    private JPanel promesse;
    private JPanel promesseframe;
    private JTextField prixavance;
    private JButton valideButton;
    private JLabel guide;
    private JLabel title;
    private JPanel acquereur;
    private JLabel infoacq;
    private JLabel labelnum;
    private JLabel nomlabel;
    private JLabel prenomlabel;
    private JLabel addlabel;
    private JLabel tellabel;
    private JLabel proflabel;
    private JLabel avancelabel;
    private JLabel num;
    private JLabel nom;
    private JLabel prenom;
    private JLabel adresse;
    private JLabel tel;
    private JLabel prof;
    private JLabel avance;
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
    private JLabel date;
    private JLabel labelavocat;
    private JLabel avocat;
    private JLabel section2;
    private JLabel infopers;
    private JLabel infoappart;
    private JButton nextButton;
    private JLabel infoprom;

    //*****************************************************data set*******************************************************
    /*Client[] clients = new Client[]{new Client(11,"Sabada","Alex","Claritain",690546399,"Etudiant"),
            new Client(12,"Ambassira","Ryan","irad",658780480,"pilote"),
            new Client(13,"Messomo","Ghislain","Nkolbisson",658171712,"Medecin")
    };*/

    Appartement[] appartements = new Appartement[]{new Appartement(21,58.45,4,50000,false),
            new Appartement(22,35.55,2,35000,false),
            new Appartement(23,70.80,6,70000,false),
            new Appartement(24,50.00,3,40000,false)};

    Avocat[] avocats = new Avocat[]{new Avocat(31,"Goueth","Alexandra","Nkolmesseng",658791346),
            new Avocat(31,"Ekobo","Jordan","Nkolmesseng",699791346)};
    public PromesseEst() throws IOException, ParseException {
        setContentPane(promesseframe);
        setSize(900, 450);
        setVisible(true);

        Client[] clients = new Client[]{null};
        try {
            clients = read_client("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\client.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Client[] finalClients = clients;
        valideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Promesse[] promesses = new Promesse[]{};
                try {
                    promesses = readfilep("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\promesse.txt");

                } catch (IOException | ParseException ex) {
                    System.out.print(ex.getMessage());
                }

                Signup s = new Signup();
                s.setVisible(false);

                Acquereur[] acquereurs = new  Acquereur[]{};
                try {
                    acquereurs = read_Acquereur("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\acquereur.txt");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                double avance1 = Float.parseFloat(prixavance.getText());
                System.out.println(avance1);
                prixavance.setText("");

                /****************selecting client*********************/
                Client clientchoisi = null;
                int id1 = Integer.parseInt(infoappart.getText());
                for(int l = 0; l < finalClients.length; l++){
                    if(finalClients[l].getNumCNI() == id1){
                        clientchoisi = finalClients[l];
                    }
                }

                /****************selecting appartement*********************/
                Appartement appartchoisi = null;
                int id = Integer.parseInt(infopers.getText());
                System.out.println(id);
                for(int l= 0; l < appartements.length; l++){
                    if(appartements[l].getNum() == id){
                        appartchoisi = appartements[l];
                        break;
                    }
                }

                Avocat avocatchoisi = avocats[0];

                if(avance1 < appartchoisi.getPrix())
                {
                    boolean val = verifyavancement(avance1, appartchoisi.getPrix());
                    if(val == true)
                    {
                        JOptionPane.showMessageDialog(null,"Your promis has been registered!! THANKS!!");
                        numero.setText(Integer.toString(appartchoisi.getNum()));
                        superficie.setText(Double.toString((appartchoisi.getSuperficie())));
                        chambre.setText(Integer.toString(appartchoisi.getNbr_chambre()));
                        prix.setText(Double.toString(appartchoisi.getPrix()));

                        num.setText(Integer.toString(clientchoisi.getNumCNI()));
                        nom.setText(clientchoisi.getNom());
                        prenom.setText(clientchoisi.getPrenom());
                        adresse.setText(clientchoisi.getAdresse());
                        tel.setText(Integer.toString(clientchoisi.getTel()));
                        prof.setText(clientchoisi.getProfession());
                        avance.setText(Double.toString(avance1));

                        Date date1 = Calendar.getInstance().getTime();
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                        String strDate = dateFormat.format(date1);

                        date.setText(strDate);
                        avocat.setText(avocatchoisi.getNom());

                        int si = promesses.length - 1;
                        infoprom.setText(String.valueOf(si));
                        infoprom.setVisible(false);

                        Promesse newprom = new Promesse(si,clientchoisi.getNumCNI(),appartchoisi.getNum(),strDate,avocatchoisi.getNumauto(),avance1);
                        promesses = AddNew(newprom,promesses);

                        try {
                            writefilep("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\promesse.txt",promesses);
                        } catch (IOException | ParseException ex) {
                            System.out.print(ex.getMessage());
                        }

                        int count = 0;
                        for(int i=0; i<acquereurs.length; i++) {
                            if (clientchoisi.getNumCNI() == acquereurs[i].getNumCNI()){
                                count++;
                            }
                        }

                        if(count == 0) {
                            Acquereur newacq = new Acquereur(clientchoisi.getNumCNI(), clientchoisi.getNom(), clientchoisi.getPrenom(), clientchoisi.getAdresse(), clientchoisi.getTel(), clientchoisi.getProfession());
                            try {
                                add_acquereur("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\acquereur.txt",newacq);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            } catch (ParseException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Votre avancement n'est pas valide");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Votre avancement est plus grand que le prix de l'appartement");
                }
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Chose c = new Chose();
                c.my_info(infoappart.getText(),1);
                c.my_info(infopers.getText(),2);
                c.my_info(infoprom.getText(), 3);
            }
        });
    }

    /******************verifing avancements******************************/
    public Boolean verifyavancement(double a, double prix)
    {
        double c;
        c = 0.2 * prix;

        if(a > c) {
            return true;
        }else {
            return false;
        }
    }

    /************************adding new promise to array of promises***************/
    public Promesse[] AddNew(Promesse p, Promesse parr[]) {
        int i,n=parr.length;

        Promesse newarr[] = new Promesse[n+1];

        for(i=0; i < n; i++){
            newarr[i] = parr[i];
        }
        newarr[n] = p;

        return newarr;
    }

    @Override
    public void writefilep(String filename, Promesse p[]) throws IOException, ParseException {

        BufferedWriter ow = new BufferedWriter(new FileWriter(filename));

        //ow.newLine();
        for (int i = 0; i < p.length; i++) {
            if(i == (p.length - 1))
            {
                ow.write(p[i].getId_prom()+"\n");
                ow.write(p[i].getId_acq()+"\n");
                ow.write(p[i].getId_app()+"\n");
                ow.write(p[i].getdate()+"\n");
                ow.write(p[i].getId_avocat()+"\n");
                ow.write(String.valueOf(p[i].getAvance()));
                break;
            }
            ow.write(p[i].getId_prom()+"\n");
            ow.write(p[i].getId_acq()+"\n");
            ow.write(p[i].getId_app()+"\n");
            ow.write(p[i].getdate()+"\n");
            ow.write(p[i].getId_avocat()+"\n");
            ow.write(p[i].getAvance()+"\n");
        }
        ow.close();

        System.out.println("Job is donne");
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

    @Override
    public void my_info(String str, int val) {
        if(val == 1){
            infoappart.setText(str);
           // infoappart.setVisible(false);
        } else if (val == 2) {
            infopers.setText(str);
           // infopers.setVisible(false);
        }
    }

    @Override
    public  Personne[] readfile(String filename) throws IOException{

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

    @Override
    public void add_client(String filename, Client client) throws IOException, ParseException {

    }

    @Override
    public void add_visite(String filename, Visite visite) throws IOException, ParseException {

    }

    @Override
    public Client[] read_client(String filename) throws IOException, ParseException {
        BufferedReader readersize = new BufferedReader(new FileReader(filename));
        String line = readersize.readLine();
        long count = Files.lines(Path.of(filename)).count();
        int size = (int) (count/6);

        Client[] clients = new  Client[size];

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

            clients[index] = new Client(Integer.parseInt(num),nom,prenom,add,Integer.parseInt(tel),prof);
            index = index + 1;
        }

        reader.close();

        return clients;
    }

    @Override
    public Acquereur[] read_Acquereur(String filename) throws IOException, ParseException {
        BufferedReader readersize = new BufferedReader(new FileReader(filename));
        String line = readersize.readLine();
        long count = Files.lines(Path.of(filename)).count();
        int size = (int) (count/6);

        Acquereur[] acquereurs = new  Acquereur[size];

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

            acquereurs[index] = new Acquereur(Integer.parseInt(num),nom,prenom,add,Integer.parseInt(tel),prof);
            index = index + 1;
        }

        reader.close();

        return acquereurs;
    }

    @Override
    public void add_acquereur(String filename, Acquereur acquereur) throws IOException, ParseException {
        //--------------------------------reading into file---------------------------------------//
        BufferedReader readersize = new BufferedReader(new FileReader(filename));
        String line = readersize.readLine();
        long count = Files.lines(Path.of(filename)).count();
        int size = (int) (count/6);

        Acquereur[] acquereurs = new  Acquereur[size];

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

            acquereurs[index] = new Acquereur(Integer.parseInt(num),nom,prenom,add,Integer.parseInt(tel),prof);
            index = index + 1;
        }

        reader.close();

        //-------------------------------------adding new client to array----------------------------------------//
        int count1 = 0;
        for(int k=0; k < acquereurs.length; k++)
        {
            if(acquereur == acquereurs[k]){
                count1++;
            }
        }

        int i,n=size;

        Acquereur newarr[] = new Acquereur[n + 1];

        for (i = 0; i < n; i++) {
            newarr[i] = acquereurs[i];
        }
        newarr[n] = acquereur;


        //-----------------------------------whiting new array into file-------------------------------------------//
        BufferedWriter ow = new BufferedWriter(new FileWriter(filename));

        //ow.newLine();
        for (int j = 0; j < newarr.length; j++) {
            if(j == (newarr.length - 1))
            {
                ow.write(newarr[i].getNumCNI()+"\n");
                ow.write(newarr[i].getNom()+"\n");
                ow.write(newarr[i].getPrenom()+"\n");
                ow.write(newarr[i].getAdresse()+"\n");
                ow.write(newarr[i].getTel()+"\n");
                ow.write(newarr[i].getProfession());
                break;
            }
            ow.write(newarr[i].getNumCNI()+"\n");
            ow.write(newarr[i].getNom()+"\n");
            ow.write(newarr[i].getPrenom()+"\n");
            ow.write(newarr[i].getAdresse()+"\n");
            ow.write(newarr[i].getTel()+"\n");
            ow.write(newarr[i].getProfession()+"\n");
        }
        ow.close();
    }
}
