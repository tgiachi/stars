package com.github.tgiachi.stars.network.handlers;

import com.github.tgiachi.stars.network.base.UdpNetworkMessage;
import com.github.tgiachi.stars.network.interfaces.network.IIncomingMessageListener;
import com.github.tgiachi.stars.network.interfaces.services.ISessionsService;
import com.github.tgiachi.stars.network.interfaces.services.IVersionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.FilterEvent;

import java.util.ArrayList;
import java.util.List;

// https://mina.apache.org/mina-project/userguide/ch2-basics/ch2.4-sample-udp-server.html
@Slf4j
public class ServerPacketHandler implements IoHandler {

    private final ISessionsService sessionsService;
    private final IVersionService versionService;
    private final List<IIncomingMessageListener> listeners = new ArrayList<>();

    public ServerPacketHandler(ISessionsService sessionsService, IVersionService versionService, IIncomingMessageListener listener) {
        this.sessionsService = sessionsService;
        this.versionService = versionService;
        this.listeners.add(listener);
    }

    public void addListener(IIncomingMessageListener listener) {
        listeners.add(listener);
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {

        // session.
        log.info("User connected");
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

        if (message instanceof UdpNetworkMessage udpNetworkMessage) {
            log.info("Message received: {}", udpNetworkMessage.getMessageTypeClass());
            listeners.parallelStream().forEach(listener -> listener.onMessageReceived(udpNetworkMessage, session));
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {

    }

    @Override
    public void inputClosed(IoSession session) throws Exception {

    }

    @Override
    public void event(IoSession session, FilterEvent event) throws Exception {

    }


}
