import java.util.Hashtable;

public class Panel {
    public static int[] ALGARISMOS = { 6, 2, 5, 5, 4, 5, 6, 4, 7, 6 };
    public static Hashtable<String, Integer> MEMORY = new Hashtable<String, Integer>();

    public static String makeAccount(String values) {
        int dig = Integer.parseInt(values.split(" ")[0]);
        int pal = Integer.parseInt(values.split(" ")[1]);

        if (dig <= 0)
            return "Não é possível formar um número com zero casas.";
        if (pal < 2)
            return "Para criar um número, é preciso no mínimo dois palitinhos.";

        //int totalRec = makeAccountRec(dig, pal, false);
        int totalRecMem = makeAccountRecMem(dig, pal, false);
        //System.out.println(totalRec);
        return "Total recursão com memória: " ;

    }

    public static int makeAccountRec(int digitos, int palitinhos, boolean z) {
        int total = 0;
        // verificar se cabe no visor, se não cabe, nem chama a recursão
        // if (palitinhos > digitos * 7) {
        //     return 0;
        // }

        System.out.println("dig: " + digitos + " pal: " + palitinhos + " total: " + total);
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
                z = true;
                j++;
            } // não permite colocar um zero no início caso não tenha um número antes
            total += makeAccountRec(digitos - 1, palitinhos - ALGARISMOS[j], z);
        }
        System.out.println("total: " +  total);
        return total;
    }

    public static int makeAccountRecMem(int digitos, int palitinhos, boolean z) {
        int total = 0;
        String key = digitos+","+palitinhos;

        // if (palitinhos > digitos * 7) {
        //     MEMORY.put(key, 0);
        //     return 0;
        // }

        if (MEMORY.get(key) != null){
            return MEMORY.get(key);
        }

        if (palitinhos > 0 && digitos == 0) {
            MEMORY.put(key, 0);
            return 0;
        }
        if (palitinhos == 0) {
            MEMORY.put(key, 1);
            return 1;
        }

        if (palitinhos < 0 || (digitos == 0 && palitinhos > 0)) {
            MEMORY.put(key, 0);
            return 0;
        }

        for (int j = 0; j < ALGARISMOS.length; j++) {
            if (j == 0 && !z) {
                z = true;
                j++;
            } // não permite colocar um zero no início caso não tenha um número antes
            total += makeAccountRecMem(digitos - 1, palitinhos - ALGARISMOS[j], z);
        }
        MEMORY.put(key, total);
        return total;
    }

    public static int makeAccountMatrix(int digitos, int palitinhos){
        return 0;
    }

    // public static int makeAccount2(int digitos, int palitinhos){

    // for(int i=1; i<ALGARISMOS.length; i++){
    // int auxPalitinhos = palitinhos - ALGARISMOS[i];
    // System.out.print(i);
    // int count = 0;
    // while(count < AUX.length){
    // if(AUX[count] <= auxPalitinhos){
    // auxPalitinhos -= AUX[count];
    // if(auxPalitinhos < 2){
    // count++;
    // }else{
    // System.out.print(count);
    // }

    // if(auxPalitinhos == 0){
    // auxPalitinhos = palitinhos - ALGARISMOS[i];
    // count++;
    // }

    // }else{
    // if(auxPalitinhos >= 2){
    // auxPalitinhos -= AUX[count];
    // System.out.print(count);
    // count++;
    // }else{
    // count = AUX.length;
    // auxPalitinhos = palitinhos;
    // }
    // }
    // }
    // System.out.println();
    // }

    // return TOTAL;
    // }
}
