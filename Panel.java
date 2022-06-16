import java.util.Hashtable;

public class Panel {
    public static int[] ALGARISMOS = { 6, 2, 5, 5, 4, 5, 6, 4, 7, 6 };
    public static Hashtable<String, Long> MEMORY = new Hashtable<String, Long>();

    public String makeAccount(String values) {
        long dig = Long.parseLong(values.split(" ")[0]);
        long pal = Long.parseLong(values.split(" ")[1]);

        if (dig <= 0)
            return "Não é possível formar um número com zero casas.";
        if (pal < 2)
            return "Para criar um número, é preciso no mínimo dois palitinhos.";

        //int total = makeAccountRec(dig, pal, false);
        long total = makeAccountRecMem(dig, pal, false);
        return "Total: " + total;

    }

    // Versão recursiva
    public static int makeAccountRec(long digitos, long palitinhos, boolean z) {
        int total = 0;

        if (palitinhos > digitos * 7) {
            return 0;
        }

        if (palitinhos > 0 && digitos == 0) {
            return 0;
        }

        if (palitinhos == 0) {
            return 1;
        }

        if (palitinhos < 0 || (digitos == 0 && palitinhos > 0)) {
            return 0;
        }

        for (int j = 0; j < ALGARISMOS.length; j++) {
            if (j == 0 && !z) {
                z = true; // não permite colocar um zero no início caso não tenha um número antes
                j++;
            }
            total += makeAccountRec(digitos - 1, palitinhos - ALGARISMOS[j], z);
        }
        return total;
    }

    // Versão recursiva com memória
    public static long makeAccountRecMem(long digitos, long palitinhos, boolean z) {
        long total = 0;
        String key = digitos + "," + palitinhos;

        if (MEMORY.containsKey(key)) {
            return MEMORY.get(key);
        }

        if (palitinhos > 0 && digitos == 0) {
            MEMORY.put(key, (long) 0);
            return (long) 0;
        }

        if (palitinhos == 0 && digitos >= 0) {
            MEMORY.put(key, (long) 1);
            return (long) 1;
        }

        if (palitinhos < 0) {
            MEMORY.put(key, (long) 0);
            return (long) 0;
        }

        for (int j = 0; j < ALGARISMOS.length; j++) {
            if (j == 0 && !z) {
                z = true;
                j++;
            } // não permite colocar um zero no início caso não tenha um número antes
            total += makeAccountRecMem((digitos - 1), (palitinhos - ALGARISMOS[j]), z);
        }
        MEMORY.put(key, (long) total);
        return total;
    }

    //Versão sem recursão
    public static int makeAccountMatrix(int digitos, int palitinhos) {
        int[][] matrix = new int [digitos+1][palitinhos+1];
        int result = 0;

        //etsrutura casos bases da matriz 
        for(int i = 0; i < matrix[0].length; i++){
            matrix[0][i] = 0;
        }

        for(int i = 0; i < matrix[0].length; i++){
            matrix[1][i] = 0;
            if(i == 0){matrix[1][i] = 1;}
            else if(i == 1){matrix[1][i] = 1;}
            else if(i == 2){matrix[1][i] = 1;}
            else if(i == 3){matrix[1][i] = 1;}
            else if(i == 4){matrix[1][i] = 2;}
            else if(i == 5){matrix[1][i] = 3;}
            else if(i == 6){matrix[1][i] = 3;}
            else if(i == 7){matrix[1][i] = 1;}
            else{
                matrix[1][i] = 0;
            }
        }

        for(int i = 0; i < matrix.length; i++){
            matrix[i][0] = 1;
            matrix[i][1] = 1;
            matrix[i][2] = 1;
            matrix[i][3] = 1;
        }

        for(int i=2; i<=matrix.length-1; i++){
            for(int j=4; j<=matrix[i].length-1; j++){  
                for(int a = 0; a < ALGARISMOS.length; a++){
                    int column = j-ALGARISMOS[a];
                    if(column > 0){
                        result += matrix[i-1][column];
                    }
                }
                matrix[i][j] = result;
                result = 0;
            }
        }
        return matrix[digitos][palitinhos];
    }
}
