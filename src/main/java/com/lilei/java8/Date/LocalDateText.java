package com.lilei.java8.Date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

/**
 * @description: LocalDate、LocalTime、LocalDateTime、Instant、Duration、Period、format、Parse
 * @author: Mr.Li
 * @date: Created in 2020/1/19 18:28
 * @version: 1.0
 * @modified By:
 * <p>
 * LocalDate: A date without a time-zone in the ISO-8601 calendar system, such as 2007-12-03.
 * This class is immutable and thread-safe.
 * <p>
 * LocalTime: A time without time-zone in the ISO-8601 calendar system, such as 10:15:30.
 * This class is immutable and thread-safe.
 * <p>
 * LocalDateTime: A date-time without a time-zone in the ISO-8601 calendar system, such as 2007-12-03T10:15:30.
 * This class is immutable and thread-safe.
 * <p>
 * Instant: An instantaneous point on the time-line.
 * This class is immutable and thread-safe.
 * <p>
 * Duration: A time-based amount of time, such as '34.5 seconds'.
 * This class is immutable and thread-safe.
 * <p>
 * Period: A date-based amount of time in the ISO-8601 calendar system, such as '2 years, 3 months and 4 days'.
 * This class is immutable and thread-safe.
 */
public class LocalDateText {

    public static void main(String[] args) throws InterruptedException {

        testLocalDate();
        testLocalTime();
        combineLocalDateAndLocalTime();
        testInstant();
        testDuration();
        testPeriod();
        testDateFormat();
        testDateParse();
    }

    /* 日期 */
    private static void testLocalDate() {
        /**
         * of(int year, int month, int dayOfMonth):
         * Obtains an instance of LocalDate from a year, month and day.
         */
        LocalDate localDate = LocalDate.of(2016, 11, 13);
        // 2016
        System.out.println(localDate.getYear());
        // NOVEMBER(十一月)
        System.out.println(localDate.getMonth());
        // 11
        System.out.println(localDate.getMonthValue());
        // 318 ===> getDayOfYear(): Gets the day-of-year field,这一年的多少天
        System.out.println(localDate.getDayOfYear());
        // 13 ===> getDayOfMonth(): Gets the day-of-month field,这一个月的多少天
        System.out.println(localDate.getDayOfMonth());
        // SUNDAY(星期日) ===> getDayOfWeek(): Gets the day-of-week field, which is an enum DayOfWeek.
        System.out.println(localDate.getDayOfWeek());

        System.out.println("=============");

        /**
         * TemporalField: A field of date-time, such as month-of-year or hour-of-minute.
         *                The most commonly used units are defined in ChronoField.
         * ChronoField: A standard set of fields.
         * get(TemporalField field): Gets the value of the specified field from this date as an int.
         */
        System.out.println(localDate.get(ChronoField.DAY_OF_YEAR));
        System.out.println(localDate.get(ChronoField.DAY_OF_MONTH));
        System.out.println(localDate.get(ChronoField.DAY_OF_WEEK));
        System.out.println("=============");
    }

    /* 时间 */
    private static void testLocalTime() {
        LocalTime time = LocalTime.now();
        // getHour(): Gets the hour-of-day field.
        System.out.println(time.getHour());
        // getMinute(): Gets the minute-of-hour field.
        System.out.println(time.getMinute());
        // getSecond(): Gets the second-of-minute field.
        System.out.println(time.getSecond());
        // getNano(): Gets the nano-of-second field.
        System.out.println(time.getNano());
        System.out.println("=============");
    }

    /* LocalDateTime */
    private static void combineLocalDateAndLocalTime() {
        LocalDate localDate = LocalDate.now();
        LocalTime time = LocalTime.now();

        // of(LocalDate date, LocalTime time): Obtains an instance of LocalDateTime from a date and time.
        LocalDateTime localDateTime = LocalDateTime.of(localDate, time);
        System.out.println(localDateTime.toString());

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println("=============");
    }

    /* 时间点 */
    private static void testInstant() throws InterruptedException {
        // now(): Obtains the current instant from the system clock.
        Instant start = Instant.now();
        TimeUnit.MILLISECONDS.sleep(1000L);
        Instant end = Instant.now();
        // between(Temporal startInclusive, Temporal endExclusive):
        // Obtains a representing the duration between two temporal objects.
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
        System.out.println("=============");
    }

    /* 时间段 */
    private static void testDuration() {
        LocalTime time = LocalTime.now();
        // minus(long amountToSubtract, TemporalUnit unit): Returns a copy of this time with the specified amount subtracted.
        LocalTime beforeTime = time.minus(1, ChronoUnit.HOURS);
        Duration duration = Duration.between(time, beforeTime);
        // toHours(): Gets the number of hours in this duration.
        System.out.println(duration.toHours());

        // plus与minus相反
        // plus(long amountToAdd, TemporalUnit unit): Returns a copy of this time with the specified amount added.
        LocalTime afterTime = time.plus(1, ChronoUnit.HOURS);
        System.out.println(Duration.between(time, afterTime).toHours());
        System.out.println("=============");
    }

    /* 时代、周期 */
    private static void testPeriod() {
        Period period = Period.between(LocalDate.of(2014, 1, 10),
                LocalDate.of(2016, 2, 11));
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(period.getYears());
        System.out.println("=============");
    }

    /* 时间格式 */
    private static void testDateFormat() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String format1 = localDateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
        String format2 = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String format3 = localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println("format1: " + format1);
        System.out.println("format2: " + format2);
        System.out.println("format3: " + format3);

        // ofPattern(String pattern): Creates a formatter using the specified pattern.
        DateTimeFormatter mySelfFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(localDateTime.format(mySelfFormatter));

        LocalDateTime rightNow = LocalDateTime.now();
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(rightNow);
        System.out.println(date);
        System.out.println("=============");
    }

    private static void testDateParse() {
        String date1 = "20190119";
        LocalDate localDate = LocalDate.parse(date1, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(localDate);

        String date2 = "2019-01-19 21:09:30";
        DateTimeFormatter mySelfFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDate2 = LocalDateTime.parse(date2, mySelfFormatter);
        System.out.println(localDate2);
    }
}
