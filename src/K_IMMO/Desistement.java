package K_IMMO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Scanner;

public class Desistement extends JFrame implements DesistementI, Filepromesse_I, AcquereurRead_I, ParseI{
    private JLabel title;
    private JPanel desistement;
    private JLabel desis;
    private JLabel infopromi;
    private JButton Confirmer;

    public Desistement() throws IOException, ParseException {
        setContentPane(desistement);
        setSize(700, 300);
        setVisible(true);

        //int id_p = Integer.parseInt(infopromi.getText());

        Confirmer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Promesse[] promesse = new Promesse[0];
                try {
                    promesse = readfilep("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\promesse.txt");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                Promesse pr = null;

                for (int y=0; y < promesse.length; y++){
                    if(promesse[y].getId_prom() == Integer.parseInt(infopromi.getText())){
                        pr = promesse[y];
                    }
                }

                try {
                    deletepromesse("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\promesse.txt",pr);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                deleteacq(pr);
                desis.setText("Desistement acte avec success");
            }
        });
    }

    @Override
    public void deletepromesse(String filename, Promesse prom) throws IOException, ParseException {

        try {
            Promesse[] p = readfilep(filename);

            for(int i =  0; i < p.length; i++){
                if(prom == p[i]){
                    break;
                }

                if(i < p.length) {
                    int n = p.length - 1;

                    for(int j = i; j < n; j++)
                    {
                        p[j] = p[j+1];
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteacq(Promesse promesse) {
        int t;
        Acquereur[] acquereurs = new  Acquereur[]{};
        try {
            int i = 0;
            acquereurs = read_Acquereur("D:\\the aarc\\ict\\ICT L3\\S1\\ict 301\\tp_EXAMEN_1\\code sources\\K-IMMO\\src\\K_IMMO\\acquereur.txt");

            for(i = 0; i < acquereurs.length; i++){
                if(acquereurs[i].getNumCNI() == promesse.getId_acq()){
                    break;
                }
            }

            if(i < acquereurs.length)
            {
                int n = acquereurs.length - 1;

                for (int j = i; j < n; j++)
                {
                    acquereurs[j] = acquereurs[j+1];
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void writefilep(String filename, Promesse[] p) throws IOException, ParseException {
        BufferedWriter ow = new BufferedWriter(new FileWriter(filename));

        //ow.newLine();
        for (int i = 0; i < p.length; i++) {
            if(i == (p.length - 1))
            {
                ow.write(p[i].getId_acq()+"\n");
                ow.write(p[i].getId_app()+"\n");
                ow.write(p[i].getdate()+"\n");
                ow.write(p[i].getId_avocat()+"\n");
                ow.write(String.valueOf(p[i].getAvance()));
                break;
            }
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
    public void my_info(String str, int val) {
        if (val == 3) {
            infopromi.setText(str);
            //infoprom.setVisible(false);
        }
    }
}
