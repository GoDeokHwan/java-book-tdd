package com.example.auth.javabooktdd.global.utils.date;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateUtil {

    public static ZonedDateTime nowKst() {
        return ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
