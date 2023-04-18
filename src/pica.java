
public class pica {

    double cena;
    String lielums;
    String merces;
    String nosaukums;
    String piedevumi;

    public pica(String lielums,double cena,String merces,String nosaukums,String piedevumi){
        this.lielums = lielums;
        this.merces = merces;
        this.cena = cena;
        this.nosaukums = nosaukums;
        this.piedevumi = piedevumi;
    }


    public String Izvade(){
        return "\n\t----------------------\n\tNosaukums: "+nosaukums+"\n\tLielims:"+lielums+"\n\tMerces: "+merces+
                "\n\tCena:"+cena+" $\n"+"\tPiedevumi: "+piedevumi+"\n\t----------------------\n";
    }
}



