import java.util.Scanner;

/*  PROJETO E OTIMIZAÇÃO DE ALGORITMOS
    Prof. João Batista
    Turma 010
    Morgana Weber
    Trabalho Prático 2
*/

public class Main {
    public static void main (String[] args){
        Scanner in = new Scanner(System.in);
        
        //Lê os valores 
        System.out.println("###### O painel #####");
        System.out.println("Digite os dois valores separados por espaço: ");

        String values = in.nextLine();

        Panel p = new Panel();
        System.out.println(p.makeAccount(values));
        in.close();
    }
}