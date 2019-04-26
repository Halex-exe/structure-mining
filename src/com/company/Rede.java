package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rede {

    private static String matriz[][];
    private static ArrayList<Nodo> nodos;

    private static ArrayList<Integer> valores = new ArrayList<Integer>();

    private static int tamanho;

    private static int matrizDijkstra[][];

    public static void main(String[] args) {

        String locaArq;
        locaArq = args[0];

        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(locaArq))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Erro");
        } catch (IOException e) {
            e.printStackTrace();
        }

        matriz = new String[records.size()][records.get(0).size()];
        for (int a = 0; a < records.size(); a++) {
            matriz[a] = records.get(a).toArray(matriz[a]);
        }

        tamanho = records.size();
        matrizDijkstra = new int[tamanho][tamanho];

        for (int b = 0; b < tamanho; b++) {

            djkstra(b);

        }

        //prints!

        System.out.println();
        System.out.println();

        populamatrix(valores);

        System.out.println("Matriz Dijkstra:\n");

        printMatriz();


        System.out.println("\n");


        System.out.println("Centralidade:");

        for (int u = 0; u < tamanho; u++) {
            System.out.println("Nodo "+ u +": " + centralidade(u));

        }

        System.out.println("\nPrestigio:");

        for (int u = 0; u < tamanho; u++) {
            System.out.println("Nodo "+ u +": " + prestigio(u));

        }
    }

    public static void djkstra(int qualNodo) {


        nodos = new ArrayList<>();
        for (int i = 0; i < matriz.length; i++) {
            if (i == qualNodo) {
                Nodo nodoInicial = new Nodo(i, 0);
                nodoInicial.setNodoAnterior(i);
                nodos.add(nodoInicial);
            } else {
                nodos.add(new Nodo(i, Integer.MAX_VALUE));
            }
        }

        ArrayList<Nodo> visitados = new ArrayList<Nodo>();
        ArrayList<Nodo> nvisitados = (ArrayList<Nodo>) nodos.clone();  //acabou de popular

        while (!nvisitados.isEmpty()) {
            int nodoAtual = getMenorNodo(nvisitados);
            for (int i = 0; i < matriz[nodoAtual].length; i++) {    //
                if (matriz[nodoAtual][i].equals("1") && !nodos.get(i).isVisitado()) {
                    int distancia = nodos.get(nodoAtual).getMenordistancia() + 1;
                    if (distancia < nodos.get(i).getMenordistancia()) {
                        nodos.get(i).setMenordistancia(distancia);
                        nodos.get(i).setNodoAnterior(nodoAtual);
                    }
                }
            }
            nodos.get(nodoAtual).setVisitado(true);
            nvisitados.remove(nodos.get(nodoAtual));
            visitados.add(nodos.get(nodoAtual));
        }

        for (int i = 0; i < nodos.size(); i++) {

            valores.add(nodos.get(i).getMenordistancia());

            // System.out.println("Nodo: " + nodos.get(i).getId() + " Menor distäncia: " + nodos.get(i).getMenordistancia() + " Nodo anterior: " + nodos.get(i).getNodoAnterior());
        }
    }


    public static int getMenorNodo(ArrayList<Nodo> nvisitados) {  //pega o atual e seta a maior distancia para os demais.
        int menor = 0;
        int menorDistancia = Integer.MAX_VALUE;
        for (int i = 0; i < nvisitados.size(); i++) {
            if (nvisitados.get(i).getMenordistancia() <= menorDistancia) {
                menor = nvisitados.get(i).getId();
                menorDistancia = nvisitados.get(i).getMenordistancia();
            }
        }

        return menor;

    }


    public static double centralidade(int valor) {
        double contador = 0;
        for (int i = 0; i < matrizDijkstra.length; i++) {
            if (matrizDijkstra[valor][i] != Integer.MAX_VALUE) {
                contador += matrizDijkstra[valor][i];
            }

        }
        contador = (tamanho - 1) / contador;
        return contador;
    }

    public static double prestigio(int valor) {
        double distanciaMedia = 0;
        int contador = 0;
        double prestigio = 0;
        for (int i = 0; i < matrizDijkstra.length; i++) {
            int distancia = matrizDijkstra[i][valor];
            if (distancia != Integer.MAX_VALUE && distancia != 0) {
                distanciaMedia += distancia;
                contador++;
            }
        }
        distanciaMedia = distanciaMedia / contador;
        prestigio = (contador / (tamanho - 1.0)) / distanciaMedia;

        if (contador <= 0){

            return 0;
        }else{
            return prestigio;
        }
    }


    public static void populamatrix(ArrayList<Integer> array) {

        int contador = 0;

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                // read information from somewhere
                //

                if (((array.get(contador) == Integer.MAX_VALUE) || (array.get(contador) <= 0))){   //colocar 0 sem não tiver como chegar no Nodo

                    matrizDijkstra[i][j] = 0;

                }else{

                    matrizDijkstra[i][j] = array.get(contador);
                }

                contador++;

            }
        }
    }

    public static void printMatriz() {

        for (int i = 0; i < tamanho; i++) {

            System.out.println();

            for (int j = 0; j < tamanho; j++) {

                System.out.print(matrizDijkstra[i][j]);

            }

        }

    }
}
