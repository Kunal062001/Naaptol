package pom;



public class BasePage {
	
	public long removeCommaAndAlphabetFromString(String value) {
		String number = value.replaceAll("[^0-9,]", "").replace(",", "");
		return Long.parseLong(number);
	}
	
	
}
