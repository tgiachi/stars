package com.github.tgiachi.stars.network.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UdpNetworkMessage implements Serializable {
    private String messageTypeClass;
    private byte[] data;
}
