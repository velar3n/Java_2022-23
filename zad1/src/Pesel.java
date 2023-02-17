/*Natalia Kiełbasa*/

public class Pesel{                                                                 // klasa Pesel

    private String pesel;

    public Pesel(String pesel){						                                //kostruktor
        this.pesel = pesel;
    }

    public String getPesel(){						                                // getter
        return pesel;
    }



    public boolean compare(Object pesel2){                                          // funkcja compare
        Pesel pesel1 = (Pesel)pesel2;
        return this.pesel.equals(pesel1.getPesel());
    }


    public static boolean check(String pesel){
        if(pesel.length() != 11){                                                   // sprawdzamy poprawność długości pesel - 11 znaków
            System.out.println("Incorrect lenght of pesel " + pesel);
            return false;
        }


        int weight, number_psl, control = 0;

        for(int i=0; i<10; i++){                                                    // ustalenie wagi cyfry do sumy kontrolnej
            if(i==0 || i==4 || i==8){
                weight = 1;
            } else if (i==1 || i==5 || i==9){
                weight = 3;
            } else if (i==2 || i==6){
                weight = 7;
            } else {
                weight = 9;
            }

            char letter_psl = pesel.charAt(i);                                      // zmiana Stringa na cha, a potem inty + sprawdzenie czy nie podano nienumerycznego znaku
            String string_psl = "" + letter_psl;
            try {
                number_psl = Integer.parseInt(string_psl);
            } catch (NumberFormatException nfe) {
                System.out.println("Incorrect charcter in number " + pesel);
                return false;
            }

            control = number_psl*weight + control;                                  // obliczenie sumy kontrolnej
        }

        control = control%10;                                                       // ustalenie liczby kontrolnej (11)
        if(control != 0){
            control = 10-control;
        }


        char letter_psl = pesel.charAt(10);
        String string_psl = "" + letter_psl;
        try {
            number_psl = Integer.parseInt(string_psl);
        } catch (NumberFormatException nfe) {
            System.out.println("Incorrect charcter in number " + pesel);
            return false;
        }

        if(control == number_psl){                                                  // porównanie 11 cyfry i liczby kontrolnej
            System.out.println("Pesel " + pesel + " correct");
            return true;
        }
        else{
            System.out.println("Pesel " + pesel + " control number is incorrect");
            return false;
        }

    }
}