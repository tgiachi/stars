package com.github.tgiachi.stars.network.base;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Data
public abstract class BaseMessage implements Serializable {
    private String uuid;
    private long createEpoch;

    public BaseMessage() {
        uuid = UUID.randomUUID().toString();
        createEpoch = LocalDate.now().toEpochSecond(LocalTime.now(), ZoneOffset.UTC);
    }
}
