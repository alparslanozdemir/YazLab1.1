/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yazlab1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alpar
 */
public class Oyun {

    public static JFrame frame = new JFrame("Altin Kapmaca");
    public static JPanel panel = new JPanel();
    public static Random random = new Random();
    public static int satir = 0;
    public static int counter = 0;
    public static int sutun = 0;
    public static int altinorani = 0;
    public static int gizlialtinorani = 0;
    public static int gizlialtinsayisi = 0;
    public static int altinsayisi = 0;
    public static int baslangicaltini = 0;
    public static int hamlesayisi = 0;
    public static int oyuncu1maliyet = 0;
    public static int oyuncu2maliyet = 0;
    public static int oyuncu3maliyet = 0;
    public static int oyuncu4maliyet = 0;
    public static int hamlemaliyet = 0;
    public static int oyuncu1altin = 0;
    public static int oyuncu2altin = 0;
    public static int oyuncu3altin = 0;
    public static int oyuncu4altin = 0;
    public static int oyuncu1adim = 0;
    public static int oyuncu2adim = 0;
    public static int oyuncu3adim = 0;
    public static int oyuncu4adim = 0;
    public static int oyuncu1harcanan = 0;
    public static int oyuncu2harcanan = 0;
    public static int oyuncu3harcanan = 0;
    public static int oyuncu4harcanan = 0;
    public static ImageIcon oyuncu1ico = new ImageIcon("oyuncu1.png");
    public static ImageIcon oyuncu2ico = new ImageIcon("oyuncu2.png");
    public static ImageIcon oyuncu3ico = new ImageIcon("oyuncu3.png");
    public static ImageIcon oyuncu4ico = new ImageIcon("oyuncu4.png");
    public static ImageIcon altinico = new ImageIcon("altin.png");
    public static ImageIcon gizlialtinico = new ImageIcon("gizlialtin.png");
    public static int[][] oyuncu1konum = new int[2][2]; // 0. indis oyuncu konumunu, 1. indis hedefin konumunu tutar.
    public static int[][] oyuncu2konum = new int[2][2];
    public static int[][] oyuncu3konum = new int[2][2];
    public static int[][] oyuncu4konum = new int[2][2];
    public static boolean altinkaldimi = false;
    public static File dosya = new File("cikti.txt");
    public static BufferedWriter yazici;

    public void baslat(int satir, int sutun, int altinorani, int baslangicaltini, int gizlialtinorani, int hamlesayisi, int oyuncu1maliyet, int oyuncu2maliyet, int oyuncu3maliyet, int oyuncu4maliyet, int hamlemaliyet) throws IOException {
        try {
            this.yazici = new BufferedWriter(new FileWriter(dosya));
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        yazici.write("Oyun log başlangıcı.");
        yazici.newLine();
        this.satir = satir;
        this.sutun = sutun;
        this.altinorani = altinorani;
        this.baslangicaltini = baslangicaltini;
        this.oyuncu1altin = baslangicaltini;
        this.oyuncu2altin = baslangicaltini;
        this.oyuncu3altin = baslangicaltini;
        this.oyuncu4altin = baslangicaltini;
        this.hamlesayisi = hamlesayisi;
        this.gizlialtinorani = gizlialtinorani;
        this.oyuncu1maliyet = oyuncu1maliyet;
        this.oyuncu2maliyet = oyuncu2maliyet;
        this.oyuncu3maliyet = oyuncu3maliyet;
        this.oyuncu4maliyet = oyuncu4maliyet;
        this.hamlemaliyet = hamlemaliyet;
        calistir();
    }

    public void calistir() {
        if (!dosya.exists()) {
            try {
                dosya.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JButton[][] buton = new JButton[satir][sutun];
        System.out.println("Harita olusturuluyor...\n" + "Satır sayısı=" + satir + "\n" + "Sutun sayısı=" + sutun + "\n" + "Altın oranı=" + altinorani);
        gizlialtinsayisi = ((satir * sutun / 100 * altinorani) * gizlialtinorani) / 100;
        altinsayisi = (satir * sutun / 100 * altinorani) - gizlialtinsayisi;
        int i = 0;
        int ScreenWidth = 0;
        int ScreenHeight = 0;
        int[][] harita = new int[satir][sutun];
        int rastgelesatir = 0;
        int rastgelesutun = 0;
        int rastgeledeger = 0;
        for (int j = 0; j < satir; j++) {
            for (int k = 0; k < sutun; k++) {
                harita[j][k] = 0;
            }
        }
        while (i != altinsayisi) {
            rastgelesatir = random.nextInt(satir);
            rastgelesutun = random.nextInt(sutun);
            if (harita[rastgelesatir][rastgelesutun] == 0 || (rastgelesatir != 0 && rastgelesutun != 0) || (rastgelesatir != satir - 1 && rastgelesutun != 0) || (rastgelesatir != satir - 1 && rastgelesutun != sutun - 1) || (rastgelesatir != 0 && rastgelesutun != sutun - 1)) {
                rastgeledeger = random.nextInt(4) + 1;
                harita[rastgelesatir][rastgelesutun] = rastgeledeger;
                i++;
            }
        }
        i = 0;
        while (i != gizlialtinsayisi) {
            rastgelesatir = random.nextInt(satir);
            rastgelesutun = random.nextInt(sutun);
            if (harita[rastgelesatir][rastgelesutun] == 0 || (rastgelesatir != 0 && rastgelesutun != 0) || (rastgelesatir != satir - 1 && rastgelesutun != 0) || (rastgelesatir != satir - 1 && rastgelesutun != sutun - 1) || (rastgelesatir != 0 && rastgelesutun != sutun - 1)) {
                rastgeledeger = random.nextInt(4) + 5;
                harita[rastgelesatir][rastgelesutun] = rastgeledeger;
                i++;
            }
        }
        i = 0;
        harita[0][0] = 9;
        oyuncu1konum[0][0] = 0;
        oyuncu1konum[0][1] = 0;
        oyuncu1konum[1][0] = -1;
        oyuncu1konum[1][1] = -1;
        harita[0][sutun - 1] = 10;
        oyuncu2konum[0][0] = 0;
        oyuncu2konum[0][1] = sutun - 1;
        oyuncu2konum[1][0] = -1;
        oyuncu2konum[1][1] = -1;
        harita[satir - 1][0] = 11;
        oyuncu3konum[0][0] = satir - 1;
        oyuncu3konum[0][1] = 0;
        oyuncu3konum[1][0] = -1;
        oyuncu3konum[1][1] = -1;
        harita[satir - 1][sutun - 1] = 12;
        oyuncu4konum[0][0] = satir - 1;
        oyuncu4konum[0][1] = sutun - 1;
        oyuncu4konum[1][0] = -1;
        oyuncu4konum[1][1] = -1;
        for (int j = 0; j < satir; j++) {
            for (int k = 0; k < sutun; k++) {
                //JButton buton = null;
                if (harita[j][k] == 9) {
                    buton[j][k] = new JButton(oyuncu1ico);
                } else if (harita[j][k] == 10) {
                    buton[j][k] = new JButton(oyuncu2ico);
                } else if (harita[j][k] == 11) {
                    buton[j][k] = new JButton(oyuncu3ico);
                } else if (harita[j][k] == 12) {
                    buton[j][k] = new JButton(oyuncu4ico);
                } else if (harita[j][k] == 0) {
                    buton[j][k] = new JButton();
                } else if (harita[j][k] < 5) {
                    buton[j][k] = new JButton(altinico);
                } else if (harita[j][k] > 4) {
                    buton[j][k] = new JButton(gizlialtinico);
                }
                if (j == satir - 1 && k == sutun - 1) {
                    ScreenWidth = (j + 1) * 40;
                    ScreenHeight = (k + 1) * 40;
                }
                panel.setLayout(null);
                buton[j][k].setBounds(j * 40, k * 40, 40, 40);
                panel.add(buton[j][k]);
            }
        }
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ScreenWidth + 30, ScreenHeight + 100);
        frame.setVisible(true);

        Timer timer1 = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                for (counter = 0; counter < hamlesayisi; counter++) {
                    oyuncu1hamle(harita, buton);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (oyuncu1altin > 0) {
                    //System.out.println("Oyuncu 1 Hamle Yaptı.");
                    oyuncu1altin -= hamlemaliyet;
                    try {
                        yazici.append("Oyuncu 1 Hamle Yaptı.");
                        yazici.newLine();
                        yazici.append("kalan Altın= " + oyuncu1altin);
                        yazici.newLine();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                for (counter = 0; counter < hamlesayisi; counter++) {
                    oyuncu2hamle(harita, buton);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (oyuncu2altin > 0) {
                    //System.out.println("Oyuncu 2 Hamle Yaptı.");
                    oyuncu2altin -= hamlemaliyet;
                    try {
                        yazici.append("Oyuncu 2 Hamle Yaptı.");
                        yazici.newLine();
                        yazici.append("kalan Altın= " + oyuncu2altin);
                        yazici.newLine();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                for (counter = 0; counter < hamlesayisi; counter++) {
                    oyuncu3hamle(harita, buton);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (oyuncu3altin > 0) {
                    //System.out.println("Oyuncu 3 Hamle Yaptı.");
                    oyuncu3altin -= hamlemaliyet;
                    try {
                        yazici.append("Oyuncu 3 Hamle Yaptı.");
                        yazici.newLine();
                        yazici.append("kalan Altın= " + oyuncu3altin);
                        yazici.newLine();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                for (counter = 0; counter < hamlesayisi; counter++) {
                    oyuncu4hamle(harita, buton);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (oyuncu4altin > 0) {
                    //System.out.println("Oyuncu 4 Hamle Yaptı.");
                    oyuncu4altin -= hamlemaliyet;
                    try {
                        yazici.append("Oyuncu 4 Hamle Yaptı.");
                        yazici.newLine();
                        yazici.append("kalan Altın= " + oyuncu4altin);
                        yazici.newLine();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (altinkaldimi == false || (oyuncu1altin < 5 && oyuncu2altin < 5 && oyuncu3altin < 5 && oyuncu4altin < 5)) {
                    try {
                        yazici.append("Oyun bitti.");
                        yazici.flush();
                        yazici.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    timer1.cancel();
                    frame.dispose();
                }
            }
        };
        timer1.schedule(task, 0, 2000);
    }

    public void oyuncu1hamle(int[][] harita, JButton[][] buton) {
        if (oyuncu1altin < 5) {
            return;
        }
        if (oyuncu1konum[1][0] == -1 || !(harita[oyuncu1konum[1][0]][oyuncu1konum[1][1]] < 5 && harita[oyuncu1konum[1][0]][oyuncu1konum[1][1]] > 0) || (oyuncu1konum[0][0] == oyuncu1konum[1][0] && oyuncu1konum[0][1] == oyuncu1konum[1][1]) && counter == 0) {
            oyuncu1hedef(harita);
        } else {
            if (oyuncu1konum[0][0] < oyuncu1konum[1][0]) { //DURUM 1
                oyuncu1konum[0][0]++;
                if (oyuncu1konum[0][0] == oyuncu1konum[1][0] && oyuncu1konum[0][1] == oyuncu1konum[1][1]) {
                    oyuncu1altin += harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] * 5;
                    try {
                        yazici.append("oyuncu1 Altin kazandi, Son altin durumu: " + oyuncu1altin);
                        yazici.newLine();
                        yazici.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("oyuncu1 Altin kazandi, Son altin durumu: " + oyuncu1altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu1konum[0][0] - 1][oyuncu1konum[0][1]] == 9) {
                    harita[oyuncu1konum[0][0] - 1][oyuncu1konum[0][1]] = 0;
                }
                if (harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] == 0 || harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] == harita[oyuncu1konum[1][0]][oyuncu1konum[1][1]]) {
                    harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] = 9;
                }
                if (harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] < 9 && harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] > 4) {
                    harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            } else if (oyuncu1konum[0][1] < oyuncu1konum[1][1]) { //DURUM 2
                oyuncu1konum[0][1]++;
                if (oyuncu1konum[0][0] == oyuncu1konum[1][0] && oyuncu1konum[0][1] == oyuncu1konum[1][1]) {
                    oyuncu1altin += harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] * 5;
                    try {
                        yazici.append("oyuncu1 Altin kazandi, Son altin durumu: " + oyuncu1altin);
                        yazici.newLine();
                        yazici.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("oyuncu1 Altin kazandi, Son altin durumu: " + oyuncu1altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu1konum[0][0]][oyuncu1konum[0][1] - 1] == 9) {
                    harita[oyuncu1konum[0][0]][oyuncu1konum[0][1] - 1] = 0;
                }
                if (harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] == 0 || harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] == harita[oyuncu1konum[1][0]][oyuncu1konum[1][1]]) {
                    harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] = 9;
                }
                if (harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] < 9 && harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] > 4) {
                    harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            } else if (oyuncu1konum[0][0] > oyuncu1konum[1][0]) { //DURUM 3
                oyuncu1konum[0][0]--;
                if (oyuncu1konum[0][0] == oyuncu1konum[1][0] && oyuncu1konum[0][1] == oyuncu1konum[1][1]) {
                    oyuncu1altin += harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] * 5;
                    try {
                        yazici.append("oyuncu1 Altin kazandi, Son altin durumu: " + oyuncu1altin);
                        yazici.newLine();
                        yazici.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("oyuncu1 Altin kazandi, Son altin durumu: " + oyuncu1altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu1konum[0][0] + 1][oyuncu1konum[0][1]] == 9) {
                    harita[oyuncu1konum[0][0] + 1][oyuncu1konum[0][1]] = 0;
                }
                if (harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] == 0 || harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] == harita[oyuncu1konum[1][0]][oyuncu1konum[1][1]]) {
                    harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] = 9;
                }
                if (harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] < 9 && harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] > 4) {
                    harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            } else if (oyuncu1konum[0][1] > oyuncu1konum[1][1]) { // DURUM 4
                oyuncu1konum[0][1]--;
                if (oyuncu1konum[0][0] == oyuncu1konum[1][0] && oyuncu1konum[0][1] == oyuncu1konum[1][1]) {
                    oyuncu1altin += harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] * 5;
                    try {
                        yazici.append("oyuncu1 Altin kazandi, Son altin durumu: " + oyuncu1altin);
                        yazici.newLine();
                        yazici.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("oyuncu1 Altin kazandi, Son altin durumu: " + oyuncu1altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu1konum[0][0]][oyuncu1konum[0][1] + 1] == 9) {
                    harita[oyuncu1konum[0][0]][oyuncu1konum[0][1] + 1] = 0;
                }
                if (harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] == 0 || harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] == harita[oyuncu1konum[1][0]][oyuncu1konum[1][1]]) {
                    harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] = 9;
                }
                if (harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] < 9 && harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] > 4) {
                    harita[oyuncu1konum[0][0]][oyuncu1konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            }
        }

    }

    public void oyuncu1hedef(int[][] harita) {
        int enkisa = 1000;
        for (int i = 0; i < harita.length; i++) {
            for (int j = 0; j < harita[0].length; j++) {
                if (harita[i][j] < 5 && harita[i][j] > 0) {
                    if (Math.abs(oyuncu1konum[0][0] - i) + Math.abs(oyuncu1konum[0][1] - j) < enkisa) {
                        enkisa = Math.abs(oyuncu1konum[0][0] - i) + Math.abs(oyuncu1konum[0][1] - j);
                        oyuncu1konum[1][0] = i;
                        oyuncu1konum[1][1] = j;
                    }
                }
            }
        }
        oyuncu1altin = oyuncu1altin - oyuncu1maliyet;
        try {
            yazici.append("oyuncu1 hedef belirledi, Son altin durumu: " + oyuncu1altin);
            yazici.newLine();
            yazici.append("oyuncu1 hedefi: " + oyuncu1konum[1][0] + " " + oyuncu1konum[1][1]);
            yazici.newLine();
            yazici.flush();
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("1. oyuncu hedef belirlendi.");
        //System.out.println("hedef: " + oyuncu1konum[1][0] + " " + oyuncu1konum[1][1]);
        counter--;
    }

    public void oyuncu2hamle(int[][] harita, JButton[][] buton) {
        if (oyuncu2altin < 5) {
            return;
        }
        if (oyuncu2konum[1][0] == -1 || !(harita[oyuncu2konum[1][0]][oyuncu2konum[1][1]] < 5 && harita[oyuncu2konum[1][0]][oyuncu2konum[1][1]] > 0) || (oyuncu2konum[0][0] == oyuncu2konum[1][0] && oyuncu2konum[0][1] == oyuncu2konum[1][1]) && counter == 0) {
            oyuncu2hedef(harita);
        } else {
            if (oyuncu2konum[0][0] < oyuncu2konum[1][0]) { //DURUM 1
                oyuncu2konum[0][0]++;
                if (oyuncu2konum[0][0] == oyuncu2konum[1][0] && oyuncu2konum[0][1] == oyuncu2konum[1][1]) {
                    oyuncu2altin += harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] * 5;
                    try {
                        yazici.append("oyuncu2 Altin kazandi, Son altin durumu: " + oyuncu2altin);
                        yazici.newLine();
                        yazici.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("oyuncu2 Altin kazandi, Son altin durumu: " + oyuncu2altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu2konum[0][0] - 1][oyuncu2konum[0][1]] == 10) {
                    harita[oyuncu2konum[0][0] - 1][oyuncu2konum[0][1]] = 0;
                }
                if (harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] == 0 || harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] == harita[oyuncu2konum[1][0]][oyuncu2konum[1][1]]) {
                    harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] = 10;
                }
                if (harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] < 9 && harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] > 4) {
                    harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            } else if (oyuncu2konum[0][1] < oyuncu2konum[1][1]) { //DURUM 2
                oyuncu2konum[0][1]++;
                if (oyuncu2konum[0][0] == oyuncu2konum[1][0] && oyuncu2konum[0][1] == oyuncu2konum[1][1]) {
                    oyuncu2altin += harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] * 5;
                    try {
                        yazici.append("oyuncu2 Altin kazandi, Son altin durumu: " + oyuncu2altin);
                        yazici.newLine();
                        yazici.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("oyuncu2 Altin kazandi, Son altin durumu: " + oyuncu2altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu2konum[0][0]][oyuncu2konum[0][1] - 1] == 10) {
                    harita[oyuncu2konum[0][0]][oyuncu2konum[0][1] - 1] = 0;
                }
                if (harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] == 0 || harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] == harita[oyuncu2konum[1][0]][oyuncu2konum[1][1]]) {
                    harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] = 10;
                }
                if (harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] < 9 && harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] > 4) {
                    harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            } else if (oyuncu2konum[0][0] > oyuncu2konum[1][0]) { //DURUM 3
                oyuncu2konum[0][0]--;
                if (oyuncu2konum[0][0] == oyuncu2konum[1][0] && oyuncu2konum[0][1] == oyuncu2konum[1][1]) {
                    oyuncu2altin += harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] * 5;
                    try {
                        yazici.append("oyuncu2 Altin kazandi, Son altin durumu: " + oyuncu2altin);
                        yazici.newLine();
                        yazici.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("oyuncu2 Altin kazandi, Son altin durumu: " + oyuncu2altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu2konum[0][0] + 1][oyuncu2konum[0][1]] == 10) {
                    harita[oyuncu2konum[0][0] + 1][oyuncu2konum[0][1]] = 0;
                }
                if (harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] == 0 || harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] == harita[oyuncu2konum[1][0]][oyuncu2konum[1][1]]) {
                    harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] = 10;
                }
                if (harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] < 9 && harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] > 4) {
                    harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            } else if (oyuncu2konum[0][1] > oyuncu2konum[1][1]) { // DURUM 4
                oyuncu2konum[0][1]--;
                if (oyuncu2konum[0][0] == oyuncu2konum[1][0] && oyuncu2konum[0][1] == oyuncu2konum[1][1]) {
                    oyuncu2altin += harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] * 5;
                    try {
                        yazici.append("oyuncu2 Altin kazandi, Son altin durumu: " + oyuncu2altin);
                        yazici.newLine();
                        yazici.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("oyuncu2 Altin kazandi, Son altin durumu: " + oyuncu2altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu2konum[0][0]][oyuncu2konum[0][1] + 1] == 10) {
                    harita[oyuncu2konum[0][0]][oyuncu2konum[0][1] + 1] = 0;
                }
                if (harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] == 0 || harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] == harita[oyuncu2konum[1][0]][oyuncu2konum[1][1]]) {
                    harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] = 10;
                }
                if (harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] < 9 && harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] > 4) {
                    harita[oyuncu2konum[0][0]][oyuncu2konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            }
        }

    }

    public void oyuncu2hedef(int[][] harita) {
        int enkarli = -200;
        for (int i = 0; i < harita.length; i++) {
            for (int j = 0; j < harita[0].length; j++) {
                if (harita[i][j] < 5 && harita[i][j] > 0) {
                    if ((harita[i][j] * 5) - ((Math.abs((oyuncu2konum[0][0] - i) + Math.abs(oyuncu2konum[0][1] - j)) / hamlesayisi) * hamlemaliyet) > enkarli) {
                        enkarli = (harita[i][j] * 5) - ((Math.abs((oyuncu2konum[0][0] - i) + Math.abs(oyuncu2konum[0][1] - j)) / hamlesayisi) * hamlemaliyet);
                        //System.out.println("2. oyuncu hedef karedeki altin degeri:" + harita[i][j] * 5);
                        //System.out.println("total kar: " + ((harita[i][j] * 5) - ((Math.abs((oyuncu2konum[0][0] - i) + Math.abs(oyuncu2konum[0][1] - j)) / hamlesayisi) * hamlemaliyet)));
                        oyuncu2konum[1][0] = i;
                        oyuncu2konum[1][1] = j;
                    }
                }
            }
        }
        oyuncu2altin = oyuncu2altin - oyuncu2maliyet;
        try {
            yazici.append("oyuncu2 hedef belirledi, Son altin durumu: " + oyuncu2altin);
            yazici.newLine();
            yazici.append("oyuncu2 hedefi: " + oyuncu2konum[1][0] + " " + oyuncu2konum[1][1]);
            yazici.newLine();
            yazici.flush();
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        // System.out.println("2. oyuncu hedef belirlendi.");
        // System.out.println("hedef: " + oyuncu2konum[1][0] + " " + oyuncu2konum[1][1]);
        counter--;
    }

    public void oyuncu3hamle(int[][] harita, JButton[][] buton) {
        if (oyuncu3altin < 5) {
            return;
        }
        if (oyuncu3konum[1][0] == -1 || !(harita[oyuncu3konum[1][0]][oyuncu3konum[1][1]] < 5 && harita[oyuncu3konum[1][0]][oyuncu3konum[1][1]] > 0) || (oyuncu3konum[0][0] == oyuncu3konum[1][0] && oyuncu3konum[0][1] == oyuncu3konum[1][1]) && counter == 0) {
            oyuncu3hedef(harita, buton);
        } else {
            if (oyuncu3konum[0][0] < oyuncu3konum[1][0]) { //DURUM 1
                oyuncu3konum[0][0]++;
                if (oyuncu3konum[0][0] == oyuncu3konum[1][0] && oyuncu3konum[0][1] == oyuncu3konum[1][1]) {
                    oyuncu3altin += harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] * 5;
                    //System.out.println("oyuncu3 Altin kazandi, Son altin durumu: " + oyuncu3altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu3konum[0][0] - 1][oyuncu3konum[0][1]] == 11) {
                    harita[oyuncu3konum[0][0] - 1][oyuncu3konum[0][1]] = 0;
                }
                if (harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] == 0 || harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] == harita[oyuncu3konum[1][0]][oyuncu3konum[1][1]]) {
                    harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] = 11;
                }
                if (harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] < 9 && harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] > 4) {
                    harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            } else if (oyuncu3konum[0][1] < oyuncu3konum[1][1]) { //DURUM 2
                oyuncu3konum[0][1]++;
                if (oyuncu3konum[0][0] == oyuncu3konum[1][0] && oyuncu3konum[0][1] == oyuncu3konum[1][1]) {
                    oyuncu3altin += harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] * 5;
                    try {
                        yazici.append("oyuncu3 Altin kazandi, Son altin durumu: " + oyuncu3altin);
                        yazici.newLine();
                        yazici.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("oyuncu3 Altin kazandi, Son altin durumu: " + oyuncu3altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu3konum[0][0]][oyuncu3konum[0][1] - 1] == 11) {
                    harita[oyuncu3konum[0][0]][oyuncu3konum[0][1] - 1] = 0;
                }
                if (harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] == 0 || harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] == harita[oyuncu3konum[1][0]][oyuncu3konum[1][1]]) {
                    harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] = 11;
                }
                if (harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] < 9 && harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] > 4) {
                    harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            } else if (oyuncu3konum[0][0] > oyuncu3konum[1][0]) { //DURUM 3
                oyuncu3konum[0][0]--;
                if (oyuncu3konum[0][0] == oyuncu3konum[1][0] && oyuncu3konum[0][1] == oyuncu3konum[1][1]) {
                    oyuncu3altin += harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] * 5;
                    try {
                        yazici.append("oyuncu3 Altin kazandi, Son altin durumu: " + oyuncu3altin);
                        yazici.newLine();
                        yazici.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("oyuncu3 Altin kazandi, Son altin durumu: " + oyuncu3altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu3konum[0][0] + 1][oyuncu3konum[0][1]] == 11) {
                    harita[oyuncu3konum[0][0] + 1][oyuncu3konum[0][1]] = 0;
                }
                if (harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] == 0 || harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] == harita[oyuncu3konum[1][0]][oyuncu3konum[1][1]]) {
                    harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] = 11;
                }
                if (harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] < 9 && harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] > 4) {
                    harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            } else if (oyuncu3konum[0][1] > oyuncu3konum[1][1]) { // DURUM 4
                oyuncu3konum[0][1]--;
                if (oyuncu3konum[0][0] == oyuncu3konum[1][0] && oyuncu3konum[0][1] == oyuncu3konum[1][1]) {
                    oyuncu3altin += harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] * 5;
                    try {
                        yazici.append("oyuncu3 Altin kazandi, Son altin durumu: " + oyuncu3altin);
                        yazici.newLine();
                        yazici.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("oyuncu3 Altin kazandi, Son altin durumu: " + oyuncu3altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu3konum[0][0]][oyuncu3konum[0][1] + 1] == 11) {
                    harita[oyuncu3konum[0][0]][oyuncu3konum[0][1] + 1] = 0;
                }
                if (harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] == 0 || harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] == harita[oyuncu3konum[1][0]][oyuncu3konum[1][1]]) {
                    harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] = 11;
                }
                if (harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] < 9 && harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] > 4) {
                    harita[oyuncu3konum[0][0]][oyuncu3konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            }
        }

    }

    public void oyuncu3hedef(int[][] harita, JButton[][] buton) {
        int enkarli = -200;
        int enkisa = 1000;
        int z = 0;
        int x = 0;
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < harita.length; i++) {
                for (int j = 0; j < harita[0].length; j++) {
                    if (harita[i][j] < 9 && harita[i][j] > 4) {
                        if (Math.abs(oyuncu1konum[0][0] - i) + Math.abs(oyuncu1konum[0][1] - j) < enkisa) {
                            enkisa = Math.abs(oyuncu1konum[0][0] - i) + Math.abs(oyuncu1konum[0][1] - j);
                            z = i;
                            x = j;
                        }
                    }
                }
            }
            enkisa = 1000;
            if (harita[z][x] > 4 && harita[z][x] < 9) {
                harita[z][x] -= 4;
            }
        }
        try {
            yazici.append("Oyuncu 3, Gizli altınlardan 2 tanesini görünür yaptı!");
            yazici.newLine();
            yazici.flush();
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("Oyuncu 3, Gizli altınlardan 2 tanesini görünür yaptı!");

        for (int i = 0; i < harita.length; i++) {
            for (int j = 0; j < harita[0].length; j++) {
                if (harita[i][j] < 5 && harita[i][j] > 0) {
                    if ((harita[i][j] * 5) - ((Math.abs((oyuncu3konum[0][0] - i) + Math.abs(oyuncu3konum[0][1] - j)) / hamlesayisi) * hamlemaliyet) > enkarli) {
                        enkarli = (harita[i][j] * 5) - ((Math.abs((oyuncu3konum[0][0] - i) + Math.abs(oyuncu3konum[0][1] - j)) / hamlesayisi) * hamlemaliyet);
                        //System.out.println("3. oyuncu hedef karedeki altin degeri:" + harita[i][j] * 5);
                        //System.out.println("total kar: " + ((harita[i][j] * 5) - ((Math.abs((oyuncu3konum[0][0] - i) + Math.abs(oyuncu3konum[0][1] - j)) / hamlesayisi) * hamlemaliyet)));
                        oyuncu3konum[1][0] = i;
                        oyuncu3konum[1][1] = j;
                    }
                }
            }
        }
        oyuncu3altin = oyuncu3altin - oyuncu3maliyet;
        try {
            yazici.append("oyuncu3 hedef belirledi, Son altin durumu: " + oyuncu3altin);
            yazici.newLine();
            yazici.append("oyuncu3 hedefi: " + oyuncu3konum[1][0] + " " + oyuncu3konum[1][1]);
            yazici.newLine();
            yazici.flush();
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("3. oyuncu hedef belirlendi.");
        //System.out.println("hedef: " + oyuncu3konum[1][0] + " " + oyuncu3konum[1][1]);
        counter--;
        frameguncelle(harita, buton);
    }

    public void oyuncu4hamle(int[][] harita, JButton[][] buton) {
        if (oyuncu4altin < 5) {
            return;
        }
        if (oyuncu4konum[1][0] == -1 || !(harita[oyuncu4konum[1][0]][oyuncu4konum[1][1]] < 5 && harita[oyuncu4konum[1][0]][oyuncu4konum[1][1]] > 0) || (oyuncu4konum[0][0] == oyuncu4konum[1][0] && oyuncu4konum[0][1] == oyuncu4konum[1][1]) && counter == 0) {
            oyuncu4hedef(harita);
        } else {
            if (oyuncu4konum[0][0] < oyuncu4konum[1][0]) { //DURUM 1
                oyuncu4konum[0][0]++;
                if (oyuncu4konum[0][0] == oyuncu4konum[1][0] && oyuncu4konum[0][1] == oyuncu4konum[1][1]) {
                    oyuncu4altin += harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] * 5;
                    try {
                        yazici.append("oyuncu4 Altin kazandi, Son altin durumu: " + oyuncu4altin);
                        yazici.newLine();
                        yazici.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("oyuncu4 Altin kazandi, Son altin durumu: " + oyuncu4altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu4konum[0][0] - 1][oyuncu4konum[0][1]] == 12) {
                    harita[oyuncu4konum[0][0] - 1][oyuncu4konum[0][1]] = 0;
                }
                if (harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] == 0 || harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] == harita[oyuncu4konum[1][0]][oyuncu4konum[1][1]]) {
                    harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] = 12;
                }
                if (harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] < 9 && harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] > 4) {
                    harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            } else if (oyuncu4konum[0][1] < oyuncu4konum[1][1]) { //DURUM 2
                oyuncu4konum[0][1]++;
                if (oyuncu4konum[0][0] == oyuncu4konum[1][0] && oyuncu4konum[0][1] == oyuncu4konum[1][1]) {
                    oyuncu4altin += harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] * 5;
                    try {
                        yazici.append("oyuncu4 Altin kazandi, Son altin durumu: " + oyuncu4altin);
                        yazici.newLine();
                        yazici.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("oyuncu4 Altin kazandi, Son altin durumu: " + oyuncu4altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu4konum[0][0]][oyuncu4konum[0][1] - 1] == 12) {
                    harita[oyuncu4konum[0][0]][oyuncu4konum[0][1] - 1] = 0;
                }
                if (harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] == 0 || harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] == harita[oyuncu4konum[1][0]][oyuncu4konum[1][1]]) {
                    harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] = 12;
                }
                if (harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] < 9 && harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] > 4) {
                    harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            } else if (oyuncu4konum[0][0] > oyuncu4konum[1][0]) { //DURUM 3
                oyuncu4konum[0][0]--;
                if (oyuncu4konum[0][0] == oyuncu4konum[1][0] && oyuncu4konum[0][1] == oyuncu4konum[1][1]) {
                    oyuncu4altin += harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] * 5;
                    try {
                        yazici.append("oyuncu4 Altin kazandi, Son altin durumu: " + oyuncu4altin);
                        yazici.newLine();
                        yazici.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("oyuncu4 Altin kazandi, Son altin durumu: " + oyuncu4altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu4konum[0][0] + 1][oyuncu4konum[0][1]] == 12) {
                    harita[oyuncu4konum[0][0] + 1][oyuncu4konum[0][1]] = 0;
                }
                if (harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] == 0 || harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] == harita[oyuncu4konum[1][0]][oyuncu4konum[1][1]]) {
                    harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] = 12;
                }
                if (harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] < 9 && harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] > 4) {
                    harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            } else if (oyuncu4konum[0][1] > oyuncu4konum[1][1]) { // DURUM 4
                oyuncu4konum[0][1]--;
                if (oyuncu4konum[0][0] == oyuncu4konum[1][0] && oyuncu4konum[0][1] == oyuncu4konum[1][1]) {
                    oyuncu4altin += harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] * 5;
                    try {
                        yazici.append("oyuncu4 Altin kazandi, Son altin durumu: " + oyuncu4altin);
                        yazici.newLine();
                        yazici.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //System.out.println("oyuncu4 Altin kazandi, Son altin durumu: " + oyuncu4altin);
                    counter = hamlesayisi;
                }
                if (harita[oyuncu4konum[0][0]][oyuncu4konum[0][1] + 1] == 12) {
                    harita[oyuncu4konum[0][0]][oyuncu4konum[0][1] + 1] = 0;
                }
                if (harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] == 0 || harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] == harita[oyuncu4konum[1][0]][oyuncu4konum[1][1]]) {
                    harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] = 12;
                }
                if (harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] < 9 && harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] > 4) {
                    harita[oyuncu4konum[0][0]][oyuncu4konum[0][1]] -= 4;
                }
                frameguncelle(harita, buton);
            }
        }

    }

    public void oyuncu4hedef(int[][] harita) {
        int enkarli = -200;
        for (int i = 0; i < harita.length; i++) {
            for (int j = 0; j < harita[0].length; j++) {
                if (harita[i][j] < 5 && harita[i][j] > 0) {
                    if ((harita[i][j] * 5) - ((Math.abs((oyuncu4konum[0][0] - i) + Math.abs(oyuncu4konum[0][1] - j)) / hamlesayisi) * hamlemaliyet) > enkarli) {
                        if (i == oyuncu1konum[1][0] && j == oyuncu1konum[1][1]) {
                            if ((Math.abs((oyuncu1konum[0][0] - i) + Math.abs(oyuncu1konum[0][1] - j)) / hamlesayisi) > (Math.abs((oyuncu4konum[0][0] - i) + Math.abs(oyuncu4konum[0][1] - j)) / hamlesayisi)) {
                                oyuncu4konum[1][0] = i;
                                oyuncu4konum[1][1] = j;
                                enkarli = (harita[i][j] * 5) - ((Math.abs((oyuncu4konum[0][0] - i) + Math.abs(oyuncu4konum[0][1] - j)) / hamlesayisi) * hamlemaliyet);
                            }
                        }
                        if (i == oyuncu2konum[1][0] && j == oyuncu2konum[1][1]) {
                            if ((Math.abs((oyuncu2konum[0][0] - i) + Math.abs(oyuncu2konum[0][1] - j)) / hamlesayisi) > (Math.abs((oyuncu4konum[0][0] - i) + Math.abs(oyuncu4konum[0][1] - j)) / hamlesayisi)) {
                                oyuncu4konum[1][0] = i;
                                oyuncu4konum[1][1] = j;
                                enkarli = (harita[i][j] * 5) - ((Math.abs((oyuncu4konum[0][0] - i) + Math.abs(oyuncu4konum[0][1] - j)) / hamlesayisi) * hamlemaliyet);
                            }
                        }
                        if (i == oyuncu3konum[1][0] && j == oyuncu3konum[1][1]) {
                            if ((Math.abs((oyuncu3konum[0][0] - i) + Math.abs(oyuncu3konum[0][1] - j)) / hamlesayisi) > (Math.abs((oyuncu4konum[0][0] - i) + Math.abs(oyuncu4konum[0][1] - j)) / hamlesayisi)) {
                                oyuncu4konum[1][0] = i;
                                oyuncu4konum[1][1] = j;
                                enkarli = (harita[i][j] * 5) - ((Math.abs((oyuncu4konum[0][0] - i) + Math.abs(oyuncu4konum[0][1] - j)) / hamlesayisi) * hamlemaliyet);
                            }
                        } else {
                            oyuncu4konum[1][0] = i;
                            oyuncu4konum[1][1] = j;
                            enkarli = (harita[i][j] * 5) - ((Math.abs((oyuncu4konum[0][0] - i) + Math.abs(oyuncu4konum[0][1] - j)) / hamlesayisi) * hamlemaliyet);
                        }

                    }
                }
            }
        }
        oyuncu4altin = oyuncu4altin - oyuncu4maliyet;
        try {
            yazici.append("oyuncu4 hedef belirledi, Son altin durumu: " + oyuncu4altin);
            yazici.newLine();
            yazici.append("oyuncu4 hedefi: " + oyuncu4konum[1][0] + " " + oyuncu4konum[1][1]);
            yazici.newLine();
            yazici.flush();
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        // System.out.println("4. oyuncu hedef belirlendi.");
        // System.out.println("hedef: " + oyuncu4konum[1][0] + " " + oyuncu4konum[1][1]);
        counter--;
    }

    public void frameguncelle(int[][] harita, JButton[][] buton) {
        altinkaldimi = false;
        int i = 0;
        int j = 0;
        for (i = 0; i < harita.length; i++) {
            for (j = 0; j < harita[0].length; j++) {
                if (harita[i][j] == 9) {
                    buton[i][j].setIcon(oyuncu1ico);
                } else if (harita[i][j] == 10) {
                    buton[i][j].setIcon(oyuncu2ico);
                } else if (harita[i][j] == 11) {
                    buton[i][j].setIcon(oyuncu3ico);
                } else if (harita[i][j] == 12) {
                    buton[i][j].setIcon(oyuncu4ico);
                } else if (harita[i][j] == 0) {
                    buton[i][j].setIcon(null);
                } else if (harita[i][j] < 5) {
                    altinkaldimi = true;
                    buton[i][j].setIcon(altinico);
                } else if (harita[i][j] > 4) {
                    buton[i][j].setIcon(gizlialtinico);
                }
            }
        }
    }
}
