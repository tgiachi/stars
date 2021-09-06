package com.github.tgiachi.stars.network.handlers;

import com.github.tgiachi.stars.network.base.UdpNetworkMessage;
import com.github.tgiachi.stars.network.builders.NetworkMessageBuilder;
import com.github.tgiachi.stars.network.interfaces.network.IIncomingMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.FilterEvent;

import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ClientPacketHandler implements IoHandler {

    private final List<IIncomingMessageListener> listeners = new ArrayList<>();

    public void addListener(IIncomingMessageListener listener) {
        this.listeners.add(listener);
    }

    public ClientPacketHandler(IIncomingMessageListener listener) {

    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        log.info("Session created");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        log.info("Session opened");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        log.info("Session close");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {

    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        log.error("Client error", cause);

    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        if (message instanceof IoBuffer buffer) {
            SocketAddress remoteAddress = session.getRemoteAddress();

            var parsedMessage = NetworkMessageBuilder.parseMessage(new String(buffer.array(), StandardCharsets.UTF_8));
            listeners.parallelStream().forEach(listener -> listener.onMessageReceived(parsedMessage, session));
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {

        if (message instanceof UdpNetworkMessage msg) {
            session.write(NetworkMessageBuilder.buildMessageString(msg));
        }

    }

    @Override
    public void inputClosed(IoSession session) throws Exception {

    }

    @Override
    public void event(IoSession session, FilterEvent event) throws Exception {

    }
}
