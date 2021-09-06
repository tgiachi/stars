package com.github.tgiachi.stars.network.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UdpNetworkMessage implements Serializable {
    private String uuid = UUID.randomUUID().toString();
    private String messageTypeClass;
    private byte[] data;
}
