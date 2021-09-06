package com.github.tgiachi.stars.network.protocol;

import com.github.tgiachi.stars.network.builders.NetworkMessageBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
public class StarsProtocolDecoder extends CumulativeProtocolDecoder {

    private String lastMessageUuidState = "lastmessageUUID";

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {


        if (session.getAttribute(lastMessageUuidState) == null) {
            session.setAttribute(lastMessageUuidState, UUID.randomUUID().toString());
        }
        var buffer = in.array();

        var message = NetworkMessageBuilder.parseMessage(new String(buffer, StandardCharsets.UTF_8));
        var lastUid = (String) session.getAttribute(lastMessageUuidState);
        if (!lastUid.equals(message.getUuid())) {
            out.write(message);
            log.info("Decoding message: {}", message.getMessageTypeClass());
            session.setAttribute(lastMessageUuidState, message.getUuid());
            in.position(in.buf().limit());
            return true;
        }
        return false;


    }
}
