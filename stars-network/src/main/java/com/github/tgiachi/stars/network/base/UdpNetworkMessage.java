package com.github.tgiachi.stars.network.base;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UdpNetworkMessage implements Serializable {
    private String messageTypeClass;
    private byte[] data;
}
