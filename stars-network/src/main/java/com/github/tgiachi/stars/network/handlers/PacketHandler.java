package com.github.tgiachi.stars.network.handlers;

import com.github.tgiachi.stars.network.base.UdpNetworkMessage;
import com.github.tgiachi.stars.network.interfaces.services.ISessionsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.FilterEvent;

import java.net.SocketAddress;

// https://mina.apache.org/mina-project/userguide/ch2-basics/ch2.4-sample-udp-server.html
@Slf4j
public class PacketHandler implements IoHandler {

    private final ISessionsService sessionsService;

    public PacketHandler(ISessionsService sessionsService) {
        this.sessionsService = sessionsService;
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {

    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {

    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {

    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {

    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        if (message instanceof IoBuffer buffer) {
            SocketAddress remoteAddress = session.getRemoteAddress();
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        if (message instanceof UdpNetworkMessage udpNetworkMessage) {

        }
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {

    }

    @Override
    public void event(IoSession session, FilterEvent event) throws Exception {

    }
}
