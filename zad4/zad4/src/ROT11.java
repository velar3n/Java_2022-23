public class ROT11 implements Algorithm{

	private static final char[] alphabet ={ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 
			'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 
			'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private final int rot = 11;

	public static int indexOf(char[] arr, char val){
    	for(int i = 0; i < arr.length; i++){
  			if(arr[i] == val){
  				return i;
		    }
		}
		return -1;
	}

	@Override
	public String crypt(String inputWord){
		String result = "";
		int index;

		for (char i : inputWord.toCharArray()){
			if ((i <= 'z' && i >= 'a') || (i <= 'Z' && i >= 'A') || (i <= '9' && i>= '0')){
				index = indexOf(alphabet, i);
				if(index <= 50){
					result = result + (char) alphabet[index + rot];
				} else{
					result = result + (char) alphabet[rot - 61 + index - 1];
				}
			} else{
				result = result + i;
			}
		}
		result = result + '\n';
		return result;
	}

	@Override
	public String decrypt(String inputWord){
		String result = "";
		int index;

		for (char i : inputWord.toCharArray()){
			if ((i <= 'z' && i >= 'a') || (i <= 'Z' && i >= 'A') || (i <= '9' && i>= '0')){
					index = indexOf(alphabet, i);
					if(index >= 11){
						result = result + (char) alphabet[index - rot];
					} else{
						result = result + (char) alphabet[61 + index - rot + 1];
					}
			} else{
				result = result + i;
			}
		}
		result = result + '\n';
		return result;

	}
}