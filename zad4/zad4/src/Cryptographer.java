import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;

public class Cryptographer{

	public static void cryptfile(String path_to_file_in, String path_to_file_out, Algorithm algorithm){
		try{
			BufferedReader fileIn = new BufferedReader(new FileReader(new File(path_to_file_in)));
			FileWriter fileOut = new FileWriter(new File(path_to_file_out));
			String line;

			while ((line = fileIn.readLine()) != null){
				fileOut.write(algorithm.crypt(line));
			}
			fileIn.close();
			fileOut.close();
		} catch (IOException e){
			System.out.println("Missing file");
		}
	}

	public static void decryptfile(String path_to_file_in, String path_to_file_out, Algorithm algorithm){
		try{
			BufferedReader fileIn = new BufferedReader(new FileReader(new File(path_to_file_in)));
			FileWriter fileOut = new FileWriter(new File(path_to_file_out));
			String line;
			
			while ((line = fileIn.readLine()) != null){
				fileOut.write(algorithm.decrypt(line));
			}
			fileIn.close();
			fileOut.close();
		} catch (IOException e){
			System.out.println("Missing file");
		}
	}
}