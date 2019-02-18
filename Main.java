/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author stefan
 */
import java.util.Scanner;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[]args){
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        Scanner sc = new Scanner(System.in);
        System.out.println("Pinjaman: ");
        double pinjaman = sc.nextDouble();
        System.out.println("Lama Pinjaman (bulan) : ");
        int lamaPinjaman = sc.nextInt();
        System.out.println("Bunga Pinjaman (%/tahun): ");
        double bunga = sc.nextDouble()/100;
        System.out.println("Biaya Administrasi: ");
        double biayaAdmin = sc.nextDouble();
        Simulasi start = new Simulasi(pinjaman, lamaPinjaman, bunga, biayaAdmin);
        start.calcTotalAngsuran();
        start.printSimulasi();
        System.out.println("Total Angsuran: " + df.format(start.sumOfAngsuran()));
        System.out.println("Total Biaya Admin: " + start.biayaAdmin*lamaPinjaman);
        
    }
}

class Simulasi{
    public double pinjaman;
    public int lamaPinjaman;
    public double bunga;
    public double biayaAdmin;
    public double cicilanPokok;
    public double [] totalAngsuran;
    public String [] output;
    
    public Simulasi(double p, int lP, double b, double bA){
        this.pinjaman=p;
        this.lamaPinjaman=lP;
        this.bunga=b;
        this.biayaAdmin=bA;
        this.cicilanPokok=this.pinjaman/this.lamaPinjaman;
        this.totalAngsuran = new double [this.lamaPinjaman];
        this.output = new String [this.lamaPinjaman];
    }
    
    public double calcAngsuranBunga(int bulanKe){
        double result;
        result = (this.pinjaman-(bulanKe*cicilanPokok)) * bunga/12;
        return result + biayaAdmin;
    }
    
    public void calcTotalAngsuran(){
        for(int i=0 ; i<this.lamaPinjaman ; i++){
            this.totalAngsuran[i] = this.cicilanPokok + this.calcAngsuranBunga(i);
        }
    }
    public double sumOfAngsuran(){
        double total = 0;
        for(int i=0 ; i<this.lamaPinjaman ; i++){
            total += this.totalAngsuran[i];
        }
        return (total);
    }
    
    public void printSimulasi(){
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);
        for(int i=0 ; i<this.lamaPinjaman ; i++){
            System.out.println("Bulan: " + i + ". Angsuran Pokok: " + df.format(this.cicilanPokok) + ". Biaya Admin: " + df.format(this.biayaAdmin) + ". Angsuran Bunga: " + df.format(this.calcAngsuranBunga(i)) + ". Total Angsuran: " + df.format(this.totalAngsuran[i]) + ". Sisa Pinjaman: " + df.format((this.pinjaman-((i+1)*this.cicilanPokok))));
        }
    }
}
