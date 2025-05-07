package com.awesomemusic.booking.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;

import com.awesomemusic.booking.config.Setting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class LocalDateConverter implements Converter<LocalDate, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(Setting.DATABASE_DATE_FORMAT);

    @Override
    public String convert(@NonNull LocalDate source) {
        return source.format(FORMATTER);
    }
}