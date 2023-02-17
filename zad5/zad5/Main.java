import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			Delay.delay(args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
		} catch(IndexOutOfBoundsException ex) {
			System.out.println("Missing arguments");						/* błąd - niewystrczająca ilość podanych argumentów przy uruchamianiu programu */
		} catch(IOException ex) {
			System.out.println("I/O error");								/* błąd - wejście/wyjście */
		} catch(NumberFormatException ex) {
			System.out.println("Incorrect arguments");						/* błąd - nieodpowiedni argumenty podane przy uruchamianiu programu */
		} catch(IncorrectFrameException ex) {
			System.out.println("File error");								/* błąd - niepoprawne dane w pliu */
			System.out.println(ex);
		}
	}
}