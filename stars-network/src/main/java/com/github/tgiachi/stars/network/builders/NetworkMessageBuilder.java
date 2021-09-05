package com.github.tgiachi.stars.network.builders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tgiachi.stars.network.base.BaseMessage;
import com.github.tgiachi.stars.network.base.UdpNetworkMessage;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

public class NetworkMessageBuilder {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T extends Serializable> UdpNetworkMessage buildMessage(T message) throws Exception {
        var byteMessage= objectMapper.writeValueAsBytes(message);
        byteMessage = compress(byteMessage);
        byteMessage =  Base64.getEncoder().encode(byteMessage);

        return UdpNetworkMessage.builder()
                .messageTypeClass(message.getClass().getName())
                .data(byteMessage)
                .build();
    }
    public static  <T extends Serializable> String buildMessageString(T message) throws Exception
    {
        return objectMapper.writeValueAsString(buildMessage(message));
    }

    public static UdpNetworkMessage parseMessage(String message) throws  Exception {
        var udpMessage = objectMapper.readValue(message, UdpNetworkMessage.class);
        udpMessage.setData(Base64.getDecoder().decode(udpMessage.getData()));
        udpMessage.setData(decompress(udpMessage.getData()));

        return udpMessage;
    }

    public static byte[] compress(byte[] in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            DeflaterOutputStream defl = new DeflaterOutputStream(out);
            defl.write(in);
            defl.flush();
            defl.close();

            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static byte[] decompress(byte[] in) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InflaterOutputStream infl = new InflaterOutputStream(out);
            infl.write(in);
            infl.flush();
            infl.close();

            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
