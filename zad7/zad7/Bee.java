import java.io.IOException;

public class Bee implements Runnable {

	private Entrances entrances;
	private int ID;
	private WriteToFile writeToFile;
	private int flyIn;
	private int flyOut;
	private double timeWaitingIn;
	private double timeWaitingOut;

	public Bee(Entrances entrances, int ID, WriteToFile writeToFile) {
		this.entrances = entrances;
		this.ID = ID;
		this.writeToFile = writeToFile;
		flyIn = 0;
		flyOut = 0;
		timeWaitingIn = 0;
		timeWaitingOut = 0;
	}

	@Override
	public void run() {
		try {
			while (!Thread.currentThread().isInterrupted()) {
				System.out.println(this + " podlatuje pod przelot 1");										// sprawdzanie wejść i wlot
				if (entrances.checkFirstEntrance()) {
					entrances.passFirstEntrance();
					System.out.println(this + " wlatuje przez przelot 1");
				} else {
					System.out.println(this + " przelot 1 zajęty, sprawdza przelot 2");
					if (entrances.checkSecondEntrance()) {
						entrances.passSecondEntrance();
						System.out.println(this + " wlatuje przez przelot 2");
					} else {
						System.out.println(this + " przelot 2 zajęty, oczekuje przelot 2");
						long timeStart = System.currentTimeMillis();
						entrances.passSecondEntrance();
						timeWaitingIn += System.currentTimeMillis() - timeStart;
						System.out.println(this + " wlatuje przez przelot 2");
					}
				}

				flyIn++;																					// zwiększenie licznika wlotów 

				entrances.waitIn();																				// czekanie w ulu

				System.out.println(this + " podlatuje pod przelot 1");										// sprawdzanie wejść i wylot
				if (entrances.checkSecondEntrance()) {
					entrances.passSecondEntrance();
					System.out.println(this + " wylatuje przez przelot 1");
				} else {
					System.out.println(this + " przelot 1 zajęty, sprawdza przelot 2");
					if (entrances.checkFirstEntrance()) {
						entrances.passFirstEntrance();
						System.out.println(this + " wylatuje przez przelot 2");
					} else {
						System.out.println(this + " przelot 2 zajęty, oczekuje przelot 2");
						long timeStart = System.currentTimeMillis();
						entrances.passFirstEntrance();
						timeWaitingOut += System.currentTimeMillis() - timeStart;
						System.out.println(this + " wylatuje przez przelot 2");
					}
				}

				flyOut++;																					// zwiększenie licznika wylotó

				entrances.waitOut();																				// czekanie poza ulem

			}
		} catch (InterruptedException e) {
			double averageIn = timeWaitingIn / 1000;														// obliczenie średnichczasów czekania na wlot/wylot
			double averageOut = timeWaitingOut / 1000;
			if(flyIn != 0) {
				averageIn = averageIn / flyIn;
			}
			if(flyOut != 0) {
				averageOut = averageOut / flyOut;
			}
			String str = "ID pszczoły: " + ID + " - Ilość wlotów: " + flyIn + " - Średni czas oczekiwania na wlot: " + averageIn 
				+ "s - Ilość wylotów: " + flyOut + " - Średni czas oczekiwania na wylot: " + averageOut + "s";
			writeToFile.addBeeData(str);																	// dodanie danych do pliku
			if(ID == 1) {
				System.out.println("Dodawanie danych do pliku BeeThreads.txt... \n");
				try {
					Thread.sleep(5000);
					writeToFile.writeDataToFile	();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			System.out.println("Dane dodane");
			}
		}
	}

	@Override
	public String toString() {
		return "Pszczoła " + ID;
	}
}