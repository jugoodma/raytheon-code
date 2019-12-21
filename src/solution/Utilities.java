package solution;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Contains useful utilities for calculating weekday occurrences.
 *
 * @author Justin Goodman
 *
 * Can be extended to include overlapping meeting dates. For example, if we want to count the
 * number of Wednesday meetings, but some holiday falls on a Wednesday, we would want to not include that
 * day occurrence. This depends on another system in the project, but is easy to implement. Simply iterate
 * through the meeting dates that occur within the dates we are testing, and if it falls on the same weekday,
 * then we decrement the count.
 */
class Utilities {
    // contains util methods for calculating occurrences

    /**
     * Method that counts the number of dayIn occurrences between (inclusive) startIn and endIn
     *
     * For example, the number of Wednesdays between December 3rd 2019 and December 25th 2019 is 4.
     * <pre>
     *   DECEMBER - 2019
     * Su|Mo|Tu[We]Th|Fr|Sa
     * --------------------
     * 01|02[03|--|05|06|07
     * 08|09|10|--|12|13|14
     * 15|16|17|--|19|20|21
     * 22|23|24|--]26|27|28
     * 29|30|31|xx|xx|xx|xx
     * </pre>
     *
     * @param startIn the yyyy-MM-dd start date to test weekday occurrences
     * @param endIn the yyyy-MM-dd end date to test weekday occurrences
     * @param dayIn the actual day-of-the-week (Sunday -- Saturday) to hold the recurring meeting
     * @return an integer count of the number of occurrences of dayIn found between startIn and endIn
     */
    static int Count(String startIn, String endIn, String dayIn) {
        // setup
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // tYpO iN wOrD 'yyyy'
        Calendar start = Calendar.getInstance();
        try {
            start.setTime(format.parse(startIn));
        } catch (ParseException e) {
            e.printStackTrace();
            // todo : ensure start < end
        }
        Calendar end = Calendar.getInstance();
        try {
            end.setTime(format.parse(endIn));
        } catch (ParseException e) {
            e.printStackTrace();
            // todo : ensure start < end
        }
        int day = dowStrToInt(dayIn);

        int count = 0;

        // clamp to the day-of-week we care about
        if (start.get(Calendar.DAY_OF_WEEK) > day) {
            start.add(Calendar.WEEK_OF_MONTH, 1);
        }
        start.add(Calendar.DAY_OF_MONTH, day - start.get(Calendar.DAY_OF_WEEK));
        // another clamp
        if (end.get(Calendar.DAY_OF_WEEK) < day) {
            end.add(Calendar.WEEK_OF_MONTH, -1);
        }
        end.add(Calendar.DAY_OF_MONTH, day - end.get(Calendar.DAY_OF_WEEK));

        // todo : make this faster
        while (start.getTimeInMillis() <= end.getTimeInMillis()) {
            count++;
            start.add(Calendar.WEEK_OF_MONTH, 1);
        }
        /*
        ideas to make faster:
        first count the difference in years (+52 per year)
        then count the differences in months (+4 ish per month)
        then count the weeks

        Dates are hard to work with though because we have to
        worry about leap years and other quirks

        and not every month has 4 Mondays (for example). some have 5, some have 3
         */

        return count;
    }

    /**
     *
     * @param dayOfWeek String version of the weekday. It is required to be spelled correctly. It will be converted
     *                  to lower-case
     * @return the Calendar class integer representation of the input weekday
     */
    private static int dowStrToInt(String dayOfWeek) {
        // might be better:
        // https://docs.oracle.com/javase/8/docs/api/java/time/DayOfWeek.html
        dayOfWeek = dayOfWeek.toLowerCase();
        switch (dayOfWeek) {
            case "sunday":    return Calendar.SUNDAY;
            case "monday":    return Calendar.MONDAY;
            case "tuesday":   return Calendar.TUESDAY;
            case "wednesday": return Calendar.WEDNESDAY;
            case "thursday":  return Calendar.THURSDAY;
            case "friday":    return Calendar.FRIDAY;
            case "saturday":  return Calendar.SATURDAY;
        }
        // throw new Exception(); // need to put 'throws' into method signature, then handle in Count()
        return -1; // day of week was improperly formatted. todo: make it do something better?
    }
}
