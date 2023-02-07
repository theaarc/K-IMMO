package K_IMMO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VisitEst extends JFrame implements Clientadd_I, ParseI{
    private JLabel infoappart;
    private JPanel visitest;
    private JButton acquerirButton;
    private JLabel numlabel;
    private JLabel superlabel;
    private JLabel nbrClabel;
    private JLabel prixLabel;
    private JLabel num;
    private JLabel superficie;
    private JLabel nbr;
    private JLabel prix;
    private JButton backToHomeButton;
    private JTextArea remarque;
    private JLabel infopers;
    private JPanel infos;

    public VisitEst()
    {
        setContentPane(visitest);
        setSize(600, 450);
        setVisible(true);

        backToHomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                LocalDate dateObj = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String date = dateObj.format(formatter);

                String infop = infopers.getText();

                Visite v = new Visite(Integer.parseInt(infop),date, "NO",remarque.getText());

                Signup s = new Signup();
                s.setVisible(false);
                try {
                    Personne[] p = s.readfile("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\personnel.txt");

                    //cheching for personne in array p
                    for(int i = 0; i < p.length; i++){
                        if(p[i].getNumCNI() == Integer.parseInt(infop)){
                            Client c = new Client(p[i].getNumCNI(),p[i].getNom(),p[i].getPrenom(),p[i].getAdresse(),p[i].getTel(),p[i].getProfession());
                            add_client("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\client.txt",c);
                        }
                    }
                } catch (IOException | ParseException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    add_visite("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\visite.txt",v);
                    Home h = new Home();
                    h.my_info(infop,0);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        acquerirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                String infop = infopers.getText();

                LocalDate dateObj = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String date = dateObj.format(formatter);

                String rem = remarque.getText();
                if(Objects.equals(rem,"")){
                    rem = "vide";
                }
                Visite v = new Visite(Integer.parseInt(infop),date, "Acquerir",rem);

                Signup s = new Signup();
                s.setVisible(false);
                try {
                    Personne[] p = s.readfile("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\personnel.txt");

                    //cheching for personne in array p
                    for(int i = 0; i < p.length; i++){
                        if(p[i].getNumCNI() == Integer.parseInt(infop)){
                            Client c = new Client(p[i].getNumCNI(),p[i].getNom(),p[i].getPrenom(),p[i].getAdresse(),p[i].getTel(),p[i].getProfession());
                            add_client("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\client.txt",c);
                        }
                    }
                } catch (IOException | ParseException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    add_visite("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\visite.txt",v);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                PromesseEst p = null;
                try {
                    p = new PromesseEst();
                    p.my_info(infoappart.getText(),1);
                    p.my_info(infopers.getText(),2);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                p.my_info(infopers.getText(),1);
                p.my_info(infoappart.getText(),2);

            }
        });
    }


    /********************************************************* method for adding a visite to file****************************************************/
    @Override
    public void add_visite(String filename, Visite visite) throws IOException, ParseException {
        //--------------------------------reading into file---------------------------------------//
        BufferedReader readersize = new BufferedReader(new FileReader(filename));
        String line = readersize.readLine();
        long count = Files.lines(Path.of(filename)).count();
        int size = (int) (count/4);

        Visite[] visites = new Visite[size];

        File file = new File(filename);
        Scanner reader = new Scanner(file);

        String id_client,date,decision,remarque;

        int index = 0;

        while (reader.hasNext())
        {
            id_client = reader.nextLine();
            date = reader.nextLine();
            decision = reader.nextLine();
            remarque = reader.nextLine();

            visites[index] = new Visite(Integer.parseInt(id_client),date,decision,remarque);
            index = index + 1;
        }
        reader.close();

        //-------------------------------------adding new visite to array----------------------------------------//
        int i,n=size;

        Visite newarr[] = new Visite[n+1];

        for(i=0; i < n; i++){
            newarr[i] = visites[i];
        }
        newarr[n] = visite;

        //-----------------------------------whiting new array into file-------------------------------------------//
        BufferedWriter ow = new BufferedWriter(new FileWriter(filename));

        //ow.newLine();
        for (int j = 0; j < newarr.length; j++) {
            if(j == (newarr.length - 1))
            {
                ow.write(newarr[j].getId_client()+"\n");
                ow.write(newarr[j].getDate()+"\n");
                ow.write(newarr[j].getDesision()+"\n");
                ow.write(newarr[j].getRemarque());
                break;
            }
            ow.write(newarr[j].getId_client()+"\n");
            ow.write(newarr[j].getDate()+"\n");
            ow.write(newarr[j].getDesision()+"\n");
            ow.write(newarr[j].getRemarque()+"\n");
        }
        ow.close();
    }

    /********************************************************* method for adding a client to file****************************************************/
    @Override
    public void add_client(String filename,Client client) throws IOException, ParseException {
        //--------------------------------reading into file---------------------------------------//
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

        //-------------------------------------adding new client to array----------------------------------------//
        int count1 = 0;
        for(int k=0; k < clients.length; k++)
        {
            if(client == clients[k]){
                count1++;
            }
        }

        int i,n=size;

        Client newarr[] = new Client[n + 1];

        for (i = 0; i < n; i++) {
            newarr[i] = clients[i];
        }
        newarr[n] = client;


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

    /************************************************fetching appartement info********************************************/
    public  void getinfoappart() {
        Appartement[] appartements = new Appartement[]{new Appartement(21, 58.45, 4, 50000, false),
                new Appartement(22, 35.55, 2, 35000, false),
                new Appartement(23, 70.80, 6, 70000, false),
                new Appartement(24, 50.00, 3, 40000, false)};

        int id = Integer.parseInt(infoappart.getText());
        for (int k = 0; k < appartements.length; k++) {
            if (id == appartements[k].getNum()) {
                num.setText(String.valueOf(appartements[k].getNum()));
                superficie.setText(String.valueOf(appartements[k].getSuperficie()));
                nbr.setText(String.valueOf(appartements[k].getNbr_chambre()));
                prix.setText(String.valueOf(appartements[k].getPrix()));
            }
        }
    }

    @Override
    public void my_info(String str, int val) {
        if(val == 1){
            infoappart.setText(str);
            infoappart.setVisible(false);
        }else if(val == 2){
            infopers.setText(str);
            infopers.setVisible(false);
        }
    }
}
