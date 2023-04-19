import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class picerija {

    static ArrayList<pica> _picas = new ArrayList<>();
    static Boolean EXIT=false;
    static ArrayList<Integer> _rejtingi = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {

        SwitchLogin();

    }

    static void SwitchKlients() throws IOException {
        int opcija;
        ImageIcon icon = new ImageIcon("photo3.jpg");
        String[] izvelesKlients={"Izveidot picu","Apskatit esosas Picas","Dzest picu","Iesniegt Pasutijumu","Atpakal"};
        do {
            opcija = JOptionPane.showOptionDialog(null, "Ko tu gribi izdarit?","Klients", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
                    icon, izvelesKlients, izvelesKlients[0]);

            switch(opcija) {
                case 0:
                    IzveidotPicu();
                    break;
                case 1:
                    ApskatitPicas();
                    break;
                case 2:
                    DzestPicu();
                    break;
                case 3:
                    IesniegtPasutijumu();
                    break;
                default:SwitchLogin();
            }


        }while(EXIT!=true);
    }

    static void SwitchDarbinieks() throws IOException {
        int opcija;
        ImageIcon icon = new ImageIcon("photo2.jpg");
        String[] izvelesDarbinieks= {"Apskatit nodotos pasutijumus","Apskatit Rejtingu","Atpakal"};
        do {
            opcija = JOptionPane.showOptionDialog(null, "Ko tu gribi izdarit?","Darbinieks", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
                    icon, izvelesDarbinieks, izvelesDarbinieks[0]);

            switch(opcija) {
                case 0:
                    ApskatitPasutijumus();
                    break;
                case 1:
                	ApskatitRejtingus();
                	break;
                default:SwitchLogin();
            }

        }while(EXIT!=true);
    }

    static void SwitchLogin() throws IOException {
        ImageIcon icon = new ImageIcon("photo1.jpg");
        int opcija;
        String[] izvelesLogin= {"Klients","Darbinieks","Aizvert programmu"};
        opcija = JOptionPane.showOptionDialog(null, "Kas tu esi","LOGIN", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,
                icon, izvelesLogin, izvelesLogin[0]);

        switch(opcija) {
            case 0://KLIENTS
                SwitchKlients();
                break;
            case 1://DARBINIEKS
                SwitchDarbinieks();
                break;
            default:EXIT=true;

        }
    }



    static void IzveidotPicu(){
        double cenaPicai=0;
        String piedevumi="";
        String lielumi[] = {"20cm-10$","40cm-25$","1m-40$"};
        String merces[] = {"Ketcups","Majoneze","sinepes"};
        String nosaukums = JOptionPane.showInputDialog("Kads nosaukums bus tavai picai");
        String lielums  = (String) JOptionPane.showInputDialog(null,"Kadu lielumu tu gribi savai picai?",
                "Izveide",JOptionPane.QUESTION_MESSAGE,null,lielumi,lielumi[0]);
        int izveletaisIndeks = Arrays.asList(lielumi).indexOf(lielums);
        String merce = (String) JOptionPane.showInputDialog(null, "Kadu merci gribi picca?","Izveide", JOptionPane.QUESTION_MESSAGE,
                null, merces,merces[0]);
        switch(izveletaisIndeks) {
            case 0: cenaPicai = 10;lielums="20cm"; break;
            case 1: cenaPicai = 25;lielums="40cm"; break;
            case 2: cenaPicai = 40;lielums="1m"; break;
        }

        JPanel panel = new JPanel();
        JCheckBox Siers = new JCheckBox("Siers-0.50€");
        JCheckBox Tomati = new JCheckBox("Tomati-0.30€");
        JCheckBox Bekons = new JCheckBox("Bekons-0.80€");
        JCheckBox Senes = new JCheckBox("Senes-0.30€");
        panel.add(Siers);
        panel.add(Tomati);
        panel.add(Bekons);
        panel.add(Senes);
        Object[] options = { "izveleties", panel };
        JOptionPane.showOptionDialog(null, "Izvelies piedevumus:", "Piedevumi",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        if(Siers.isSelected()) {
            piedevumi+="Siers ";
            cenaPicai+=0.50;
        }
        if(Tomati.isSelected()) {
            piedevumi+="Tomati ";
            cenaPicai+=0.50;
        }
        if(Bekons.isSelected()) {
            piedevumi+="Bekons ";
            cenaPicai+=0.50;
        }
        if(Senes.isSelected()) {
            piedevumi+="Senes ";
            cenaPicai+=0.50;
        }

        pica PICA = new pica(lielums, cenaPicai, merce, nosaukums, piedevumi);
        _picas.add(PICA);
    }



    static void ApskatitPicas(){

        if(_picas.size()!=0) {
            String dati = "";
            for(int i=0;i<_picas.size();i++){
                dati+=_picas.get(i).Izvade();
            }
            JOptionPane.showMessageDialog(null, dati);

        }else JOptionPane.showMessageDialog(null,"Tev nav PICAS!!!","EROR", JOptionPane.ERROR_MESSAGE);
    }



    static void DzestPicu() {

        if(_picas.size()!=0) {
            String[] PicuSaraksts = new String[_picas.size()];

            for(int i=0;i<_picas.size();i++) {
                PicuSaraksts[i] = _picas.get(i).nosaukums;
            }

            String izveletaPica = (String) JOptionPane.showInputDialog(
                    null,
                    "Kadu picu tu gribi izdsest?",
                    "Dzesana",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    PicuSaraksts,
                    PicuSaraksts[0]);

            int izveletaisIndeks = Arrays.asList(PicuSaraksts).indexOf(izveletaPica);
            JOptionPane.showMessageDialog(null, "Tu izdsesi: "+izveletaPica);
            _picas.remove(izveletaisIndeks);
        }else JOptionPane.showMessageDialog(null,"Tev nav PICAS!!!","EROR", JOptionPane.ERROR_MESSAGE);
    }



    static void IesniegtPasutijumu() throws IOException {
        if(_picas.size()!=0) {
            BufferedWriter bw = new BufferedWriter(new FileWriter("pasutijumi.txt",true));

            bw.write("\n\tCILVEKS INFO\n");

            boolean piegade=false;
            String vards;
            Object[] options = {"Ja", "Ne"};
            double cenaPasutijam=0;
            vards = JOptionPane.showInputDialog("Ka jus sauc?");
            String talrunis = JOptionPane.showInputDialog("Kads jums ir telefona numurs?");
            String dati="",adrese;

            int n = JOptionPane.showOptionDialog(null,"Edisiet uz vietas?","Piegade",JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
            if (n == JOptionPane.YES_OPTION) {
                piegade=false;
                bw.write("\t"+vards+" | "+talrunis+" | Uz vietas");

            } else if (n == JOptionPane.NO_OPTION) {
                piegade=true;
                cenaPasutijam+=3;
                adrese = JOptionPane.showInputDialog("Uz kuro adresi atvest picu?");
                bw.write("\t"+vards+" | "+talrunis+" | "+adrese);
            }

            for(int i=0;i<_picas.size();i++) {
                cenaPasutijam+=_picas.get(i).cena;
                dati+=_picas.get(i).Izvade();
            }

            dati += (piegade==true) ? "\tpiegade-3€":"\tUz vietas";
            dati +="\n\tCena pasutijam: "+cenaPasutijam+"€";

            JTextArea textArea = new JTextArea(dati);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(300, 250));
            JOptionPane.showMessageDialog(null, scrollPane,"CEKS",JOptionPane.PLAIN_MESSAGE);


            bw.write(dati+"\n\n\txxxxxxxxxxxxxxxxxxxxxxx\n");
            bw.close();
            _picas.clear();
        }else JOptionPane.showMessageDialog(null,"Tev nav PICAS!!!","EROR", JOptionPane.ERROR_MESSAGE);
        
        String[] opcijasRejting = {"1","2","3","4","5"};
        
        String rejtings  = (String) JOptionPane.showInputDialog(null,"Kadu Rejtingu tu gribi ielikt?",
                "Izveide",JOptionPane.QUESTION_MESSAGE,null,opcijasRejting,opcijasRejting[0]);
        
        
        int izveletaisIndeks = Arrays.asList(opcijasRejting).indexOf(rejtings)+1;
        _rejtingi.add(izveletaisIndeks);
    }



    static void ApskatitPasutijumus() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("pasutijumi.txt"));

        String izvade;
        String dati = "";

        while((izvade=br.readLine())!=null) {
            dati+=izvade+"\n";
        }

        JTextArea textArea = new JTextArea(dati);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 300));
        JOptionPane.showMessageDialog(null, scrollPane,"PASUTIJUMI",JOptionPane.PLAIN_MESSAGE);

        br.close();
    }
    
    static void ApskatitRejtingus() {
    	
    	double average = 0;
    	if (_rejtingi.size() > 0) {
    	    double sum = 0;
    	    for (int j = 0; j < _rejtingi.size(); j++) {
    	        sum += _rejtingi.get(j);
    	    }
    	    average = sum / _rejtingi.size();
    	}
    	
    	if(average==0) {
    		JOptionPane.showMessageDialog(null, "Sodien tavai picerijai nebija pasutijumus!!!","eror",JOptionPane.ERROR_MESSAGE);
    	}else JOptionPane.showMessageDialog(null,"Sodien rejtings tavai picerijai ir: "+average,"Rejtings",JOptionPane.INFORMATION_MESSAGE);
    }

}
