package program;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import me.tongfei.progressbar.ProgressBar;
import org.apache.commons.io.IOUtils;
import org.jline.utils.InfoCmp;
import program.JenaTDB1;

public class Menu {

    static Scanner sc = new Scanner(System.in);

    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option : ");
    }
    public static void main(String[] args) {
        clrscr();
        String[] options = {
                "PROGRAMA PRINCIPAL",
                "1- Listar por tipo de funcionario",
                "2- Listar todos os funcionarios",
                "3- Verificar existência de nome de um funcionario",
                "4- Listar funcionarios constituidos pela string a inserir",
                "5- Registar novo funcionario",
                "6- Registar novo hospital",
                "7- Listar hospitais",
                "8- Registar medico num hospital",
                "9- Listar medicos a partir de uma data de nascimento e de um determinado hospital",
                "10- Listar tipo de funcionario a partir de uma data de nascimento e de um determinado hospital",
                "11- Contar todos os funcionarios que sao medicos que trabalham no Hospital Infante D. Pedro e sao especialistas em cardiologia",
                "99- Exit",
        };
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        while (option!=99){
            printMenu(options);
            try {
                option = scanner.nextInt();
                switch (option){
                    case 1: option1(); break;
                    case 2: option2(); break;
                    case 3: option3(); break;
                    case 4: option4(); break;
                    case 5: option5(); break;
                    case 6: option6(); break;
                    case 7: option7(); break;
                    case 8: option8(); break;
                    case 9: option9(); break;
                    case 10: option10(); break;
                    case 11: option11(); break;
                    case 99: System.exit(0);
                }
            }
            catch (Exception ex){
                System.out.println("Please enter an integer value between 1 and " + options.length);
                scanner.next();
            }
        }
    }

    public static void clrscr(){
        //Clears Screen in java
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    private static void pressAnyKeyToContinue()
    {
        System.out.println("");
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }

    // Listar Médicos
    private static void option1() throws InterruptedException {
        System.out.println("Escolheu 'listar por tipo de funcionario'");
        System.out.print("Insira o tipo que pretende procurar (Medico -> 1 | Enfermeiro -> 2 | Auxiliar -> 3 | Dirigente -> 4): ");
        int num = sc.nextInt();
        String str1 = sc.nextLine();
        String word = "";
        if(num == 1){
            word = "Medico";
        } else if(num == 2){
            word = "Enfermeiro";
        } else if(num == 3){
            word = "Auxiliar_de_Saude";
        } else if(num == 4){
            word = "Dirigente";
        }

        //progressBar();
        System.out.println("A pesquisar...");
        JenaTDB1.opcao1(word);
        pressAnyKeyToContinue();
        clrscr();
    }

    private static void option2() throws InterruptedException {
        System.out.println("Escolheu 'listar todos os funcionarios'");
        System.out.println("A pesquisar...");
        JenaTDB1.opcao2();
        pressAnyKeyToContinue();
        clrscr();
    }

    private static void option3() throws InterruptedException {
        System.out.println("Escolheu 'verificar existência de nome de um funcionario'");
        System.out.print("Insira o nome que pretende procurar se existe: ");
        String word = sc.nextLine();
        System.out.println("A pesquisar...");
        JenaTDB1.opcao3(word);
        pressAnyKeyToContinue();
        clrscr();
    }

    private static void option4() throws InterruptedException {
        System.out.println("Escolheu 'listar funcionarios constituidos pela string a inserir'");
        System.out.print("Insira a parte do nome que pretende procurar: ");
        String word = sc.nextLine();
        System.out.println("A pesquisar...");
        JenaTDB1.opcao4(word);
        pressAnyKeyToContinue();
        clrscr();
    }

    private static void option5() throws InterruptedException {
        System.out.println("Escolheu 'registar novo funcionario'");
        System.out.print("Insira o nome do funcionario: ");
        String name = sc.nextLine();

        System.out.print("Insira o tipo que pretende registar (Medico -> 1 | Enfermeiro -> 2 | Auxiliar -> 3 | Dirigente -> 4): ");
        int num = sc.nextInt();
        String str1 = sc.nextLine();
        String word = "";
        if(num == 1){
            word = "Medico";
        } else if(num == 2){
            word = "Enfermeiro";
        } else if(num == 3){
            word = "Auxiliar_de_Saude";
        } else if(num == 4){
            word = "Dirigente";
        }

        //sc.nextLine();
        System.out.print("Insira a data de nascimento: ");
        String date = sc.nextLine();

        JenaTDB1.opcao5(name, word, date);
        pressAnyKeyToContinue();
        clrscr();
    }

    private static void option6() throws InterruptedException {
        System.out.println("Escolheu 'criar novo hospital'");
        System.out.print("Insira o nome do hospital: ");
        String name = sc.nextLine();
        JenaTDB1.opcao6(name);
        pressAnyKeyToContinue();
        clrscr();
    }

    private static void option7() throws InterruptedException {
        System.out.println("Escolheu 'listar hospitais'");
        System.out.println("A pesquisar...");
        JenaTDB1.opcao7();
        pressAnyKeyToContinue();
        clrscr();
    }

    private static void option8() throws InterruptedException {
        System.out.println("Escolheu 'inserir medico num hospital'");
        System.out.print("Insira o nome do funcionario: ");
        String name = sc.nextLine();
        System.out.print("Insira o hospital: ");
        String hospital = sc.nextLine();

        JenaTDB1.opcao8(name, hospital);
        pressAnyKeyToContinue();
        clrscr();
    }

    private static void option9() throws InterruptedException {
        System.out.println("Escolheu 'listar medicos a partir de uma data de nascimento e de um determinado hospital'");
        System.out.print("Insira a data de nascimento que pretende procurar: ");
        String date = sc.nextLine();
        System.out.print("Insira o hospital que pretende procurar: ");
        String hospital = sc.nextLine();

        System.out.println("A pesquisar...");
        JenaTDB1.opcao9(date, hospital);
        pressAnyKeyToContinue();
        clrscr();
    }

    private static void option10() throws InterruptedException {
        System.out.println("Escolheu 'listar tipo de funcionario a partir de uma data de nascimento e de um determinado hospital'");

        System.out.print("Insira o tipo que pretende procurar (Medico -> 1 | Enfermeiro -> 2 | Auxiliar -> 3 | Dirigente -> 4): ");
        int num = sc.nextInt();
        String str1 = sc.nextLine();
        String word = "";
        if(num == 1){
            word = "Medico";
        } else if(num == 2){
            word = "Enfermeiro";
        } else if(num == 3){
            word = "Auxiliar_de_Saude";
        } else if(num == 4){
            word = "Dirigente";
        }
        //sc.nextLine();

        System.out.print("Insira a data de nascimento que pretende procurar: ");
        String date = sc.nextLine();

        System.out.print("Insira o hospital que pretende procurar: ");
        String hospital = sc.nextLine();

        System.out.println("A pesquisar...");
        JenaTDB1.opcao10(word, date, hospital);
        pressAnyKeyToContinue();
        clrscr();
    }

    private static void option11() throws InterruptedException {
        System.out.println("Escolheu 'contar todos os funcionarios que sao medicos que trabalham no Hospital Infante D. Pedro e sao especialistas em cardiologia'");
        System.out.println("A pesquisar...");
        JenaTDB1.opcao11();
        pressAnyKeyToContinue();
        clrscr();
    }

    /*
    public static void progressBar() throws InterruptedException {
        System.out.println("\n");
        try (ProgressBar pb = new ProgressBar("A procurar informacao:", 2000)) {
            pb.step();
            Thread.sleep(1_000);
            pb.step();
            Thread.sleep(1_000);

            pb.stepBy(248); // step by n
            Thread.sleep(1_000);

            pb.stepTo(600); // step directly to n
            Thread.sleep(1_000);

            pb.maxHint(2500);
            pb.stepTo(1337);
            pb.stepTo(2500);
            pb.setExtraMessage("A procurar..."); // Set extra message to display at the end of the bar
        }
        //System.out.println("Informação localizada lista a baixo ...");
    }


    //Funcao para impedir excepcao responsavel pela progressBar
    static {
        InfoCmp.setDefaultInfoCmp("dumb-color", () -> {
            try {
                return new String(IOUtils.toByteArray(InfoCmp.class.getResourceAsStream("/org/jline/utils/dumb-colors.caps")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
     */

}
