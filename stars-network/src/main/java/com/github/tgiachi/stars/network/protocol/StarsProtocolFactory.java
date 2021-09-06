package com.github.tgiachi.stars.network.protocol;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class StarsProtocolFactory implements ProtocolCodecFactory {
    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return new StarsProtocolEncoder();
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return new StarsProtocolDecoder();
    }
}
