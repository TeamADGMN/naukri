package utility;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.*;
public class Validator {
	private static Scanner scanner = new Scanner(System.in);
	public static boolean validateEmail(String email) {
		Pattern pattern = Pattern.compile("[a-z][a-z0-9]*@[a-z0-9]+[.][com]");
		Matcher matcher = pattern.matcher(email);
		if(matcher.find()) {
			return true;
		}
		return false;
	}
	public static boolean validatePhone(String number) {
		Pattern pattern = Pattern.compile("[6-9][0-9]{9}");
		Matcher matcher = pattern.matcher(number);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}
	public static boolean validateDate(String startDate,String endDate) {
		Pattern  pattern = Pattern.compile("[0-9]{4}+[-]+[0-1][0-9]+[-]+[0-3][0-9]");
		Matcher m1 = pattern.matcher(startDate);
		Matcher m2 = pattern.matcher(endDate);
		if(!(m1.matches()) && m2.matches())
			return false;
		LocalDate  date1 = LocalDate.parse(startDate);
		LocalDate date2  = LocalDate.parse(endDate);
		if(date1.isBefore(LocalDate.now()) || date1.isAfter(date2))
			return false;
		return true;
	}
	public static boolean validateDate(String date) {
		Pattern  pattern = Pattern.compile("[0-9]{4}+[-]+[0-1][0-9]+[-]+[0-3][0-9]");
		Matcher m1 = pattern.matcher(date);
		if(m1.matches()) {
			LocalDate  currDate = LocalDate.parse(date);
			if(currDate.isAfter(LocalDate.now())){
				return true;
			}
			return false;
		}
		return false;
	}
	public static boolean isString(String string) {
		Pattern pattern = Pattern.compile("[a-zA-Z]+");
		Matcher matcher = pattern.matcher(string);
		return matcher.matches() ? true : false;
	}
	public static boolean isStringLine(String string) {
		Pattern pattern = Pattern.compile("[a-zA-Z ]+");
		Matcher matcher = pattern.matcher(string);
		return matcher.matches() ? true : false;
	}
	public static String getString() {
		String string = scanner.next();
		if(!isString(string)) getStringLine();
		return string;
	}
	public static String getStringLine() {
		scanner.nextLine();
		String string = scanner.nextLine();
		if(!isStringLine(string)) getStringLine();
		return string;
	}
	public static LocalDate getDate() {
		String date = scanner.next();
		if(!validateDate(date)) getDate();
		return LocalDate.parse(date);
	}
}
