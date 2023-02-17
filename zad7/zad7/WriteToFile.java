import java.util.ArrayList;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile{

	private ArrayList<String> beeData;

	public WriteToFile() {
		beeData = new ArrayList<String>();
	}

	public synchronized void addBeeData(String str) {
		beeData.add(str);
	}

	public void writeDataToFile() throws IOException {

		File file = new File("BeeThreads.txt");
        FileWriter fw = new FileWriter(file, false);
	    BufferedWriter bw = new BufferedWriter(fw);

	    try {
	    	for(String str : beeData){
			    bw.write(str);
			    bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
}