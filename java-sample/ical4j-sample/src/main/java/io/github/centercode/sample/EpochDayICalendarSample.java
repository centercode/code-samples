package io.github.centercode.sample;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Trigger;
import net.fortuna.ical4j.model.property.TzId;
import net.fortuna.ical4j.model.property.XProperty;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class EpochDayICalendarSample {

    private static final long MILLIS_OF_DAY = 86400_000L;

    private static final long START_DATE = LocalDate.of(2020, 1, 1).toEpochDay();

    private static final long END_DATE = LocalDate.of(2022, 1, 1).toEpochDay();

    private static final DateTime DISABLE_TRIGGER_DATE_TIME = new DateTime(java.util.Date.from(
            ZonedDateTime.of(1976, 4, 1, 0, 55, 45, 0, ZoneId.of("UTC"))
                    .toInstant()
    ));

    public static void main(String[] args) throws IOException {
        Calendar calendar = new Calendar()
                .withDefaults()
                .withProperty(new Description("展示Epoch Day的日历"))
                .withProperty(new TzId("UTC"))
                .getFluentTarget();

        VAlarm vAlarm = new VAlarm()
                .withProperty(new Trigger(DISABLE_TRIGGER_DATE_TIME))
                .withProperty(new XProperty("X-APPLE-LOCAL-DEFAULT-ALARM", "TRUE"))
                .getFluentTarget();

        for (long d = START_DATE; d < END_DATE; d++) {
            Date date = new Date(new java.util.Date(d * MILLIS_OF_DAY));
            VEvent event = new VEvent(date, String.valueOf(d));
            event.getAlarms().add(vAlarm);
            calendar.withComponent(event);
        }
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String outputPath = "/tmp/epoch_day_" + today + ".ics";
        try (FileOutputStream out = new FileOutputStream(outputPath)) {
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(calendar, out);
        }
    }
}
