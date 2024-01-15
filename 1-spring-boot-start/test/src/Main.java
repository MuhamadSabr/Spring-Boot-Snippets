import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        System.out.println(quarter("JuLyy"));

    }

    public static String quarter(String month){
        return switch (month.toLowerCase(Locale.ROOT)){
            case "january", "jan", "feb", "february", "march", "mar" -> "1st";
            case "april", "apr", "may", "jun" -> "2nd";
            case "jul", "july", "august", "aug", "sep", "september" -> "3rd";
            case "oct", "october", "november", "nov", "december", "dec" ->"4th";
            //default -> "Bad";
            default -> {
                String ret = "Bad";
                yield ret;
            }
        };
    }

    public static void printYearsAndDays(long minutes){
        if(minutes<0){
            System.out.println("Invalid Value");
            return;
        }
        long year = minutes / 525600;
        long day  = (minutes % 525600 ) / 24/60; // If  less than 1 year then the number will be returned and will be
        //broken down to days
        System.out.println(minutes + " min = " + year + " y and " + day + " d");
    }



    public static String getDurationString(int seconds){
        if(seconds<0){
            return "Seconds must be greater than or equal to 0";
        }
        // Get total number of minutes that could form the seconds
        // Then get any remaining second that could not form a minute.
        return getDurationString(seconds % 60, seconds / 60);
    }

    public static String getDurationString(int seconds, int minutes){
        if(minutes>=0 && (seconds>=0 && seconds <=59)){
            int hh, mm;
            hh = minutes / 60;
            mm = minutes % 60;
            return ( (hh>=10?hh:"0"+hh) + "h:" + (mm>=10?mm:"0"+mm) + "m:" + (seconds>=10?seconds:"0"+seconds) + "s");
        }
        return "Minutes must be greater than or equal to 0 and \n" +
                "seconds must be between 0 and 59";
    }


    public static double convertToCentimeters(int height){
        return (height*12*2.54);
    }

    public static double convertToCentimeters(int FeetHeight, int InchHeight){
        return (   convertToCentimeters( FeetHeight*12+InchHeight ) );
    }
}