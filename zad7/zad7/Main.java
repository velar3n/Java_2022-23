import java.io.IOException;

public class Main {

	public static void main(String[] args) {

		if(args.length < 1){
			System.out.println("Missing arguments");
			return;
		}

		int n = Integer.parseInt(args[0]);

		Entrances entrances = new Entrances();
		WriteToFile writeToFile = new WriteToFile();
		entrances.symulate(n, writeToFile);
		
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		entrances.getExecut().shutdownNow();
	}
}