public class Polibiusz implements Algorithm{		// dodane zostały trzy dodatkowe kolumny na J, znaki specjalne i liczby 

	private final char[][] alphabet ={ { 'A', 'B', 'C', 'D', 'E', 'J', '0', '5' }, { 'F', 'G', 'H', 'I', 'K', 'Ą', '1', '6' }, 
		{ 'L', 'M', 'N', 'O', 'P', 'Ę', '2', '7'}, { 'Q', 'R', 'S', 'T', 'U', 'Ł', '3', '8'}, { 'V', 'W', 'X', 'Y', 'Z', 'Ó', '4', '9' } };

	@Override
	public String crypt(String inputWord){
		String result = new String();
		inputWord = inputWord.toUpperCase();
		boolean flag;

		for (Character k : inputWord.toCharArray()){
			flag = false;
			for (int i = 0; i < alphabet.length && !flag; i++){
				for (int j = 0; j < alphabet[0].length && !flag; j++){
					if (k.equals(alphabet[i][j])){
						result += i + 1;
						result += j + 1;
						flag = true;
					}
				}
			}
			if (!flag){
				result += k;
				if (k != ' ')
					result += ' ';
			}
		}
		result = result + '\n';
		return result;
	}

	@Override
	public String decrypt(String inputWord){
		String result = "";
		char[] toDecryptArray = inputWord.toCharArray();
		int step = 1;

		for (int i = 0; i < toDecryptArray.length; i += step){
			step = 1;
			if ((toDecryptArray[i] - '0' >= 0) && (toDecryptArray[i] - '0' < alphabet.length + 1)){
				step = 2;
				if (((toDecryptArray[i + 1] - 1 - '0' >= 0) && (toDecryptArray[i + 1] - 1 - '0' < alphabet[0].length + 1)) && (i + 1 < toDecryptArray.length)){
					result = result + alphabet[toDecryptArray[i] - 1 - '0'][toDecryptArray[i + 1] - 1 - '0'];
				} else{
					result = result + toDecryptArray[i];
					if (i + 1 < inputWord.length()){
						if (inputWord.charAt(i + 1) != ' ') {
							result = result + toDecryptArray[i + 1];
						}
						else{
							if (i + 2 < inputWord.length()){
								if (inputWord.charAt(i + 2) == ' ')
									result = result + toDecryptArray[i + 1];
							}
						}
					}
				}
			} else{
				if (i + 1 < inputWord.length()){
						result = result + toDecryptArray[i];
				}
			}
		}
		result = result + '\n';
		return result;
	}

}