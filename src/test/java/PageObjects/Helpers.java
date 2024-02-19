package PageObjects;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class Helpers {
	 private static final String[] NAMES_LIST = {"John", "Emma", "Michael", "Sophia", "Christopher", "Olivia", "William", "Ava", "James", "Isabella","Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};


	    private static final Random random = new Random();

	    
	    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	    public static String generateFirstName() {
	        StringBuilder randomString = new StringBuilder(10);
	        Random random = new Random();

	        for (int i = 0; i < 10; i++) {
	            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
	            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
	            randomString.append(randomChar);
	        }

	        return randomString.toString();
	    }

	    public static String generateLastName() {
	        int randomIndex = random.nextInt(NAMES_LIST.length);
	        return NAMES_LIST[randomIndex];
	    }
	    public static String getDayOfWeek(String specificDateStr) {
	        try {
	            // Parse the specific date
	            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            Date specificDate = dateFormat.parse(specificDateStr);

	            // Get the day of the week
	            Calendar calendar = Calendar.getInstance();
	            calendar.setTime(specificDate);
	            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

	            // Convert dayOfWeek to the day name
	            String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	            return daysOfWeek[dayOfWeek - 1];  // Adjust for array indexing (Sunday is 1 in Calendar)
	        } catch (ParseException e) {
	            System.out.println("Error parsing the specific date. Please provide a valid date format.");
	            return "";  // Return an empty string in case of an error
	        }
	    }
	    public static int getCurrentYear() {
	        return Year.now().getValue();
	    }
	
		public static String getCurrentMonth() {
	        Calendar calendar = Calendar.getInstance();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM"); // Use "MMM" for abbreviated month names
	        String month = dateFormat.format(calendar.getTime());
	
	        // Special case for September to get the first four letters
	        if (month.equals("Sep")) {
	            return "Sept";
	        }
	
	        // Make the first letter uppercase and remove the dot if present
	        return month.substring(0, 1).toUpperCase() + month.substring(1).replace(".", "");
	    }
    
    public String convertMonth(String month, int choose_month) {
        switch (choose_month) {
            case 1:
                month = "Jan";
                break;
            case 2:
                month = "Feb";
                break;
            case 3:
                month = "Mar";
                break;
            case 4:
                month = "Apr";
                break;
            case 5:
                month = "May";
                break;
            case 6:
                month = "Jun";
                break;
            case 7:
                month = "Jul";
                break;
            case 8:
                month = "Aug";
                break;
            case 9:
                month = "Sept";
                break;
            case 10:
                month = "Oct";
                break;
            case 11:
                month = "Nov";
                break;
            case 12:
                month = "Dec";
                break;
            default:
                throw new IllegalArgumentException("Please type the month in integer");
        }
        return month;
    }
    

    public static String getNextMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM");
        String nextMonth = dateFormat.format(calendar.getTime());

        // Special case for September to get the first four letters
        if (nextMonth.equals("Sep")) {
            return "Sept";
        }

        // Make the first letter uppercase and remove the dot if present
        return nextMonth.substring(0, 1).toUpperCase() + nextMonth.substring(1).replace(".", "");
    }

    public static void checkIfFutureDate(String inputDateString) throws ParseException, PastDateException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date inputDate = dateFormat.parse(inputDateString);

        Date currentDate = new Date();

        if (inputDate.before(currentDate)) {
            throw new PastDateException("Input date is in the past!");
        }
    }
    public static class PastDateException extends Exception {
        public PastDateException(String message) {
            super(message);
        }
    }
    public static void verifyDateRange(String inputDate) throws ParseException, YearOutOfRangeException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Parse the input date
        Date parsedDate = dateFormat.parse(inputDate);

        // Get the current date with time components set to zero
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);

        // Set the upper limit as the current date plus 1 year and 1 month
        Calendar upperLimitCalendar = (Calendar) currentDate.clone();
        upperLimitCalendar.add(Calendar.YEAR, 1);
        upperLimitCalendar.add(Calendar.MONTH, 1);

        // Compare the parsed date with the upper limit date
        if (parsedDate.after(upperLimitCalendar.getTime())) {
            throw new YearOutOfRangeException("Input date is beyond the specified range.");
        }
    }

    public static class YearOutOfRangeException extends Exception {
        public YearOutOfRangeException(String message) {
            super(message);
        }
    }
    
    public static boolean isLastMonthInRangeOneYearOneMonth(String inputDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Parse the input date
        Date parsedDate = dateFormat.parse(inputDate);

        // Get the current date with time components set to zero
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);

        // Set the upper limit as the current date plus 1 year and 1 month
        Calendar upperLimitCalendar = (Calendar) currentDate.clone();
        upperLimitCalendar.add(Calendar.YEAR, 1);
        upperLimitCalendar.add(Calendar.MONTH, 1);

        // Check if the parsed date is in the last month of the range
        Calendar lastMonthOfRange = (Calendar) upperLimitCalendar.clone();
        lastMonthOfRange.add(Calendar.MONTH, -1);

        return parsedDate.after(lastMonthOfRange.getTime()) && parsedDate.before(upperLimitCalendar.getTime());
    }
    public static boolean isLastMonthInOneYearRange(String inputDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Parse the input date
        Date parsedDate = dateFormat.parse(inputDate);

        // Get the current date
        Calendar currentDate = Calendar.getInstance();

        // Set the upper limit as the current date plus 1 year
        Calendar upperLimitCalendar = (Calendar) currentDate.clone();
        upperLimitCalendar.add(Calendar.YEAR, 1);

        // Set the last month as the current month minus 1
        Calendar lastMonthCalendar = (Calendar) currentDate.clone();
        lastMonthCalendar.add(Calendar.MONTH, -1);

        // Check if the parsed date is in the last month and within the range
        return parsedDate.after(lastMonthCalendar.getTime()) && parsedDate.before(upperLimitCalendar.getTime());
    }
    
    public static boolean isPenultimateMonthInOneYearRange(String inputDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Parse the input date
        Date parsedDate = dateFormat.parse(inputDate);

        // Get the current date
        Calendar currentDate = Calendar.getInstance();

        // Set the upper limit as the current date plus 1 year and 1 month
        Calendar upperLimitCalendar = (Calendar) currentDate.clone();
        upperLimitCalendar.add(Calendar.YEAR, 1);
        upperLimitCalendar.add(Calendar.MONTH, 1);

        // Set the penultimate month as the current month plus 10 months
        Calendar penultimateMonthCalendar = (Calendar) currentDate.clone();
        penultimateMonthCalendar.add(Calendar.MONTH, 10);

        // Check if the parsed date is in the penultimate month and within the range
        return parsedDate.after(currentDate.getTime()) && parsedDate.before(penultimateMonthCalendar.getTime()) && parsedDate.before(upperLimitCalendar.getTime());
    }
    public static int getMonthTwoMonthsPrior(String inputDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Parse the input date
        Date parsedDate = dateFormat.parse(inputDate);

        // Create a calendar instance and set it to the input date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parsedDate);

        // Subtract 2 months from the current month
        calendar.add(Calendar.MONTH, -2);

        // Get the month as an integer (without leading zeros)
        return calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based, so add 1
    }
    


}
