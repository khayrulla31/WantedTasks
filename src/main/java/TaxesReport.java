import java.time.*;
import java.time.temporal.ChronoField;
import java.util.*;


public class TaxesReport {
    public static final Collection<DayOfWeek> weekends = List.of(
            DayOfWeek.SUNDAY,
            DayOfWeek.SATURDAY
    );
    public static final Collection<LocalDate> holidays = Set.of(
            LocalDate.of(Year.now().get(ChronoField.YEAR), Month.NOVEMBER, 30),
            LocalDate.of(Year.now().get(ChronoField.YEAR), Month.DECEMBER, 31),
            LocalDate.of(Year.now().get(ChronoField.YEAR), Month.NOVEMBER, 4),
            LocalDate.of(Year.now().get(ChronoField.YEAR), Month.DECEMBER, 12),
            LocalDate.of(Year.now().get(ChronoField.YEAR), Month.SEPTEMBER, 1)
    );
    public static final Collection<Integer> daysForReport = Set.of(1, 10, 20);

    private static String checkDate(LocalDateTime localDate) {

        LocalDateTime nextDay = localDate.plusDays(1);

        int currentDate = localDate.get(ChronoField.DAY_OF_MONTH);

        if (daysForReport.contains(currentDate)) {
            if (!isNotWorkingDay(localDate)) {
                return sendReport("on time ", localDate);
            } else {
                return "report has already sent, because " + currentDate + " is holiday";
            }

        } else if (daysForReport.contains(nextDay.get(ChronoField.DAY_OF_MONTH))) {
            if (isNotWorkingDay(nextDay)) {
                return sendReport("in advance", nextDay);
            }
        }
        return "it's not time for a report";

    }
    private static Boolean isNotWorkingDay(LocalDateTime localDate) {
        if (weekends.contains(localDate.getDayOfWeek()) || holidays.contains(localDate)) {
            return true;
        }
        return false;
    }
    private static String sendReport(String s, LocalDateTime localDate) {
        return "Report was sent " + s + " " + localDate.toString();
    }


    public static void main(String[] args) {
        LocalDateTime currentDate = LocalDateTime.now();
        System.out.println(checkDate(currentDate));

        LocalDateTime first = LocalDateTime.of(Year.now().get(ChronoField.YEAR), Month.SEPTEMBER, 1, 18, 0);
        System.out.println(checkDate(first));

        LocalDateTime onTime = LocalDateTime.of(Year.now().get(ChronoField.YEAR), Month.NOVEMBER, 20, 18, 0);
        System.out.println(checkDate(onTime));

    }
}


