/*Natalia Kiełbasa*/

public class Main{
    public static void main(String[] args){
        if(args.length<1){												// sprawdzenie czy wgl mamy jakieś podane dany/nr PESEL
            System.out.println("Missing input");
            return;
        }
        if(args.length>3){
            System.out.println("Please do not add more than two pesel numbers to the program");
        }

        Pesel pesel1 = new Pesel(args[0]);                              // bierzemy argument 1 jako pesel z wiersza poleceń i go sprawdzamy
        Pesel.check(args[0]);

        if(args.length == 2){                                           // jeśli 2 numery w wierszu poleceń - też je porównujemy
            Pesel pesel2 = new Pesel(args[1]);                          // bierzemy argument 2 jako pesel z wiersza poleceń i go sprawdzamy
            Pesel.check(args[1]);
            if(pesel1.compare(pesel2) == false){
                System.out.println("The given pesel numbers are not equal");
            } else{
                System.out.println("The given numbers are equal");
            }
        }
    }
}