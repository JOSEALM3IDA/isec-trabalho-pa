package jogo.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static List<String> lerLinhasParaLista(String nomeFicheiro) {
        List<String> linhas = new LinkedList<>();

        File fich = new File(nomeFicheiro);

        Scanner sc;
        try { sc = new Scanner(fich); } catch (FileNotFoundException fnfe) { return linhas; }

        while (sc.hasNextLine()) linhas.add(sc.nextLine());

        sc.close();

        return linhas;
    }
}
