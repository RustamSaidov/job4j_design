package ru.job4j.it;

public class Array1111 {
    static int row = 0;
    static int column = 0;
    static int ex =0;
    public static void main(String[] args) {
        //final int[][] data;

        //int[][] in = {{}, {}, {}};
        //int[][] in = {{}, {}, {1,1,5}, {}, {1,1}, {}};
        int[][] in = {{1}, {2, 3, 4}, {}, {5, 6}};
        //int[][] in = {{1,1}, {1,1}, {1,1}};
        //int[][] in = {{}, {1}};
//        int[][] in = {{1}, {}, {}, {}, {2}};
        System.out.println("number of rows: " + in.length);
        System.out.println("number of columns: " + in[0].length);
        //System.out.println(in[2][2]);
        for(int i=row; i<in.length; i++) {
            for (int j=column; j <in[row].length; j++) {
                System.out.println(in[i][j]);
            }
            row++;
        }

        System.out.println("ex: " + ex + " "+ reEx() + " "+ex + " "+ reEx() + " ");
        reEx();
        System.out.println("ex: " + ex + " "+ reEx() + " "+ex + " "+ reEx() + " ");
    }
    static int reEx(){
        //ex=ex+1;
        return ex++;
    }
}