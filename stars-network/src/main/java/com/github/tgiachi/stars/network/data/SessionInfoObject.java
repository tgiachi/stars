package com.github.tgiachi.stars.network.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.mina.core.session.IoSession;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionInfoObject implements Serializable {
    private long sessionId;
    private IoSession networkSession;
}
