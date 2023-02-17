import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.*;		/* Pattern */

public class Delay {

	public static void delay(String in_file_path, String out_file_path, int delay, int framerate) throws IOException, IncorrectFrameException {
		BufferedReader input;
		FileWriter output;

		try(BufferedReader in = new BufferedReader(new FileReader(new File(in_file_path))); FileWriter out = new FileWriter(new File(out_file_path))) {
			input = in;
			output = out;
			String oldLine;
			String newLine = "";
			Integer last = 0, lineNumber = 0;

			while((oldLine = input.readLine()) != null) {
				lineNumber++;
				String[] line = oldLine.split("}");
				Integer begin, end;
				if((Pattern.matches("\\{[[0-9]]*", line[0])) && (Pattern.matches("\\{[[0-9]]*", line[1]))) {
					line[0] = line[0].substring(1);
					line[1] = line[1].substring(1);
					begin = Integer.parseInt(line[0]);
					end = Integer.parseInt(line[1]);
					if(begin >= end) {											/* błąd - pierwsza klatka większa od drugiej np. {99}{1}*/
						throw new IncorrectFrameException("First frame bigger or equal second frame in line " + lineNumber + ": " + oldLine);
					}
					if(last > begin) {											/* błąd - nachodzące na siebie klatki w kolejnych liniach np. {1}{10} {2}{20}*/
						throw new IncorrectFrameException("Overlapping framerates in line " + lineNumber + ": " + oldLine);
					}
					last = end;
					begin += framerate * delay / 1000;
					end += framerate * delay / 1000;
					newLine = "{" + begin.toString() + "}{" + end.toString() + "}"+ line[2] + "\n";
				} else {														/* błąd - nieodpowiedni znak w lb klatek np. {12ad}{13} */
					throw new IncorrectFrameException("Incorrect symbol in framenumber in line " + lineNumber + ": " + oldLine);
				}
			output.write(newLine);
			}
		}
	}
}