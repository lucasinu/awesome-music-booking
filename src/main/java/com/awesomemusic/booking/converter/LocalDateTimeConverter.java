package com.awesomemusic.booking.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.awesomemusic.booking.config.Setting;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LocalDateTimeConverter implements Converter<LocalDateTime, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(Setting.DATABASE_DATETIME_FORMAT);

    @Override
    public String convert(@NonNull LocalDateTime source) {
        return source.format(FORMATTER);
    }
}
