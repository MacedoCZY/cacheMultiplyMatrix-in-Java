import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

class sup{
    static float tFim[] = new float[2];
}

public class deepDepression{
    public static double[][] mulM1M2(double[][] matriz, double[][] matriz1){
        double[][] matrizR = new double[matriz.length][matriz1[0].length];
        long tIni = System.currentTimeMillis();
        for(int i = 0; i < matriz.length; i++){
            for(int k = 0; k < matriz1[0].length; k++){
                for(int l = 0; l < matriz1.length; l++){
                    matrizR[i][k] += (matriz[i][l])*(matriz1[l][k]);
                }
            }
        }
        float tFim = System.currentTimeMillis() - tIni;
        System.out.printf("Produto: %fs\n", tFim/1000);
        return matrizR;
    }
    

    public static double[][] genMat(double[][] matriz, int[] aux, BufferedWriter bw, Random random, int j) throws IOException{
        for(int i = 0; i < aux[j]; i++){
            for(int k = 0; k < aux[j+1]; k++){
                matriz[i][k] = random.nextDouble(50000);
                bw.write(Double.toString(matriz[i][k]));
                bw.write(",");
            }
            bw.newLine();
        }bw.newLine();
        return matriz;
    }

    public static double[][] writMat(double[][] matrizR, BufferedWriter bw) throws IOException{
        for(int i = 0; i < matrizR.length; i++){
            for(int k = 0; k < matrizR[0].length; k++){
                bw.write(Double.toString(matrizR[i][k]));
                bw.write(",");
            }
            bw.newLine();
        }bw.newLine();

        return matrizR;
    }

    public static double[][] transpostaM2(double[][] matriz, BufferedWriter bw) throws IOException{
        double[][] matrizT = new double[matriz[0].length][matriz.length];
        long tIni = System.currentTimeMillis();
        for(int i = 0; i < matrizT.length; i++){  
            for(int k = 0; k < matrizT[i].length; k++){  
                if(i != k){  
                    matrizT[i][k] = matriz[k][i];  
                }else{  
                    matrizT[i][k] = matriz[i][k];  
                }
            }
        }
        sup.tFim[0] = System.currentTimeMillis() - tIni;
        System.out.printf("Transposição: %fs\n", sup.tFim[0]/1000);
        for(int i = 0; i < matrizT.length; i++){  
            for(int k = 0; k < matrizT[i].length; k++){  
                if(i != k){  
                    bw.write(Double.toString(matrizT[i][k]));
                    bw.write(",");
                }else{  
                    bw.write(Double.toString(matrizT[i][k]));
                    bw.write(",");
                }
            }
            bw.newLine();
        }bw.newLine();
        
        return matrizT;
    }
    
    public static double[][] mulM1M2T(double[][] matriz, double[][] matrizT, double[][] matriz1){
        double[][] matrizR = new double[matriz.length][matriz1[0].length];
        long tIni1 = System.currentTimeMillis();
        for(int i = 0; i < matriz.length; i++){
            for(int k = 0; k < matrizT.length; k++){
                for(int l = 0; l < matrizT[0].length; l++){
                    matrizR[i][k] += (matriz[i][l])*(matrizT[k][l]);
                }
            }
        }
        sup.tFim[1] = System.currentTimeMillis() - tIni1;
        System.out.printf("Produto transposto: %fs\n", sup.tFim[1]/1000);
        System.out.printf("Produto transposto + Transposição: %fs", (sup.tFim[0]/1000)+(sup.tFim[1]/1000));
        return matrizR;
    }
    public static void main(String[] args){
        Random random = new Random();
        random.setSeed(10);
        if(args.length == 5){
            char g = args[4].charAt(0);
            if(g == 'o'){
                File arq = new File("./matrizO.xls");
                
                try{
                    if(!arq.exists()) {
                        arq.createNewFile();
                    }else{
                        arq.delete();
                        arq.createNewFile();
                    }

                    FileWriter fw = new FileWriter(arq, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    
                    int aux[] = new int[args.length - 1];
                    
                    for(int i = 0; i < args.length - 1; i++){
                        aux[i] = Integer.parseInt(args[i]);
                    }
                    int h = 0;
                
                    double[][] matriz = new double[aux[h]][aux[h+1]];
                
                    double[][] matriz1 = new double[aux[h+2]][aux[h+3]];
                    
                    int j = 0;
                    matriz = genMat(matriz, aux, bw, random, j);

                    j = 2;
                    matriz1 = genMat(matriz1, aux, bw, random, j);

                    double[][] matrizR = new double[matriz.length][matriz1[0].length];

                    matrizR = mulM1M2(matriz, matriz1);

                    writMat(matrizR, bw);
                    
                    bw.close();
                    fw.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }   
            
            }else if(g == 't'){
                int x = Integer.parseInt(args[1]);
                int y = Integer.parseInt(args[2]);
                if(x != y){
                    System.out.println("Erro: c1 != l2");
                    System.exit(0);
                }
                
                File arq = new File("./matrizT.xls");
                
                try{
                    if(!arq.exists()) {
                        arq.createNewFile();
                    }else{
                        arq.delete();
                        arq.createNewFile();
                    }

                    FileWriter fw = new FileWriter(arq, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    
                    int aux[] = new int[args.length - 1];
                    
                    for(int i = 0; i < args.length - 1; i++){
                        aux[i] = Integer.parseInt(args[i]);
                    }
                    int h = 0;
                
                    double[][] matriz = new double[aux[h]][aux[h+1]];
                
                    double[][] matriz1 = new double[aux[h+2]][aux[h+3]];
                    
                    int j = 0;
                    matriz = genMat(matriz, aux, bw, random, j);

                    j = 2;
                    matriz1 = genMat(matriz1, aux, bw, random, j);

                    double[][] matrizT = new double[matriz1[0].length][matriz1.length];
                    
                    matrizT = transpostaM2(matriz1, bw);
                    
                    double[][] matrizR = new double[matriz.length][matrizT[0].length];

                    matrizR = mulM1M2T(matriz, matrizT, matriz1);

                    writMat(matrizR, bw);
                    
                    bw.close();
                    fw.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }   
            }else{
                System.out.println("Erro: parametro de opção inválido");
            }

        }else if(((args.length) == 0) || (((args.length)%2) == 0) || ((args.length)) > 5){
            System.out.println("Erro: parametros incorretos");
            System.exit(0);
        }else{
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            if(x != y){
                System.out.println("Erro: c1 != l2");
                System.exit(0);
            }
        }
    }
}