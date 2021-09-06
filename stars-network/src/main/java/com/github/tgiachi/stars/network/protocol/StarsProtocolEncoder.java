package com.github.tgiachi.stars.network.protocol;

import com.github.tgiachi.stars.network.base.BaseMessage;
import com.github.tgiachi.stars.network.builders.NetworkMessageBuilder;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.StandardCharsets;

public class StarsProtocolEncoder extends ProtocolEncoderAdapter {
    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        if (message instanceof BaseMessage msg) {
            var buffer = NetworkMessageBuilder.buildMessageString(msg).getBytes(StandardCharsets.UTF_8);
            out.write(IoBuffer.wrap(buffer));
        }
    }

    @Override
    public void dispose(IoSession session) throws Exception {

    }
}
