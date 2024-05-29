package eventwebapp.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidator {
	 public static boolean isValidName(String name) {
			String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ '’àÀèÈéÉìÌòÒùÙáÁíÍóÓúÚâÂêÊîÎôÔûÛäÄëËïÏöÖüÜçÇñÑ ";
	        
	        for (char c : name.toCharArray()) {
	            if (allowedChars.indexOf(c) == -1) {
	                return false;
	            }
	        }
	        return true; 
	    }
	 public static boolean isValid(String name) {
		 String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'’àÀèÈéÉìÌòÒùÙáÁíÍóÓúÚâÂêÊîÎôÔûÛäÄëËïÏöÖüÜçÇñÑ_";

 	    for (char c : name.toCharArray()) {
 	        if (allowedChars.indexOf(c) == -1) {
 	            return false;
 	        }
 	    }
	        return true; 
	    }
	 public static boolean isValidPassword(String val) {
		    // Controlla la lunghezza della stringa
		    if (val.length() < 6 || val.length() > 12) {
		        return false;
		    }

		    boolean containsAlphabetic = false;
		    boolean containsDigit = false;

		    // Scansiona i caratteri della stringa
		    for (char c : val.toCharArray()) {
		        if (Character.isLetter(c)) {
		            containsAlphabetic = true;
		        }
		        if (Character.isDigit(c)) {
		            containsDigit = true;
		        }

		        // Se entrambi i criteri sono soddisfatti, la stringa è valida
		        if (containsAlphabetic && containsDigit) {
		            return true;
		        }
		    }

		    // Se si arriva a questo punto, la stringa non soddisfa i criteri
		    return false;
		}
	 public static boolean isValidDateOfBirth(String inputDate) {
	        if (inputDate == null || inputDate.length() != 10) {
	            return false;
	        }

	        String[] parts = inputDate.split("/");
	        if (parts.length != 3) {
	            return false;
	        }

	        int day, month, year;
	        try {
	            day = Integer.parseInt(parts[0]);
	            month = Integer.parseInt(parts[1]);
	            year = Integer.parseInt(parts[2]);
	        } catch (NumberFormatException e) {
	            return false;
	        }

	        if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1890 || year > 2005) {
	            return false;
	        }

	        int maxDaysInMonth = getMaxDaysInMonth(month, year);
	        if (day > maxDaysInMonth) {
	            return false;
	        }

	        // Verifica dell'età minima di 18 anni
	        LocalDate currentDate = LocalDate.now();
	        LocalDate minAllowedDate = currentDate.minusYears(18);
	        LocalDate inputLocalDate = LocalDate.of(year, month, day);
	        if (inputLocalDate.isAfter(minAllowedDate)) {
	            return false;
	        }

	        return true;
	    }
	 public static boolean isValidDate(String inputDate) {
	        if (inputDate == null || inputDate.length() != 10) {
	            return false;
	        }

	        String[] parts = inputDate.split("/");
	        if (parts.length != 3) {
	            return false;
	        }

	        int day, month, year;
	        try {
	            day = Integer.parseInt(parts[0]);
	            month = Integer.parseInt(parts[1]);
	            year = Integer.parseInt(parts[2]);
	        } catch (NumberFormatException e) {
	            return false;
	        }

	        if (day < 1 || day > 31 || month < 1 || month > 12 || year < 2023) {
	            return false;
	        }

	        int maxDaysInMonth = getMaxDaysInMonth(month, year);
	        if (day > maxDaysInMonth) {
	            return false;
	        }

	        LocalDate currentDate = LocalDate.now();
	        LocalDate inputLocalDate = LocalDate.of(year, month, day);
	        if (inputLocalDate.isBefore(currentDate)) {
	            return false;
	        }

	        return true;
	    }
	    private static int getMaxDaysInMonth(int month, int year) {
	        if (month == 2) {
	            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
	                return 29;
	            } else {
	                return 28;
	            }
	        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
	            return 30;
	        } else {
	            return 31;
	        }
	    }


	  
	 public static boolean isPasswordConfirmed(String password, String confirmPassword) {
	      return password.equals(confirmPassword);
	  }
	 public static boolean isValidEmail(String email) {
		    // Espressione regolare per il formato dell'email
		    String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,8}$";
		    
		    // Creo il pattern dall'espressione regolare
		    Pattern pattern = Pattern.compile(emailRegex);
		    
		    // Corrispondenza dell'email con il pattern
		    Matcher matcher = pattern.matcher(email);
		    
		    // Restituisco true se l'email corrisponde al pattern, altrimenti false
		    return matcher.matches();
		}
	  public static boolean isNonNegativeInteger(String val) {
		    if (val == null || val.isEmpty()) {
		        return false;
		    }

		    try {
		        int num = Integer.parseInt(val);
		        return num > 0;
		    } catch (NumberFormatException e) {
		        return false;
		    }
		}
	  public static boolean isValidTimeFormat(String inputTime) {
		    if (inputTime == null || inputTime.length() != 5) {
		        return false;
		    }

		    String[] parts = inputTime.split(":");
		    if (parts.length != 2) {
		        return false;
		    }

		    int hours, minutes;
		    try {
		        hours = Integer.parseInt(parts[0]);
		        minutes = Integer.parseInt(parts[1]);
		    } catch (NumberFormatException e) {
		        return false;
		    }

		    if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
		        return false;
		    }

		    return true;
		}
	  
}
