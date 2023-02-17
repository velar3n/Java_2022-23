public class Main {

	public static void main(String[] args){

		if(args.length < 4){
			System.out.println("Missing arguments");
			return;
		}

		String fileIn = args[0];
		String fileOut = args[1];
		String chooseCrypt = args[2];
		String chooseAlgo = args[3];

		if(!(((chooseCrypt.equalsIgnoreCase("crypt")) || (chooseCrypt.equalsIgnoreCase("decrypt"))) && ((chooseAlgo.equalsIgnoreCase("rot")) || (chooseAlgo.equalsIgnoreCase("polibiusz"))))){
			System.out.println("Please insert valid arguments");
			return;
		}

		if(chooseCrypt.equalsIgnoreCase("crypt")){
			if(chooseAlgo.equalsIgnoreCase("rot")){
				Cryptographer.cryptfile(fileIn, fileOut, new ROT11());
			}
			else if(chooseAlgo.equalsIgnoreCase("polibiusz")){
				Cryptographer.cryptfile(fileIn, fileOut, new Polibiusz());
			}
		}
		else if(chooseCrypt.equalsIgnoreCase("decrypt")){
			if(chooseAlgo.equalsIgnoreCase("rot")){
				Cryptographer.decryptfile(fileIn, fileOut, new ROT11());
			}
			else if(chooseAlgo.equalsIgnoreCase("polibiusz")){
				Cryptographer.decryptfile(fileIn, fileOut, new Polibiusz());
			}
		}
	}
}