package com.github.tgiachi.stars.network.client;

import com.github.tgiachi.stars.network.base.BaseMessage;
import com.github.tgiachi.stars.network.base.UdpNetworkMessage;
import com.github.tgiachi.stars.network.handlers.ClientPacketHandler;
import com.github.tgiachi.stars.network.interfaces.network.IIncomingMessageListener;
import com.github.tgiachi.stars.network.interfaces.network.IStarsClientListener;
import com.github.tgiachi.stars.network.protocol.StarsProtocolFactory;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class StarsNetworkClient implements IIncomingMessageListener {

    private final List<IStarsClientListener> listeners = new ArrayList<>();
    private final NioDatagramConnector connector;
    private ConnectFuture connFuture;
    private IoSession session;


    @Getter
    @Setter
    private boolean connected;

    private final String serverAddress;
    private final int serverPort;

    public StarsNetworkClient(String serverAddress, int serverPort) {
        this.connector = new NioDatagramConnector();

        this.connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new StarsProtocolFactory()));
        this.connector.setHandler(new ClientPacketHandler(this));

        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void addListener(IStarsClientListener listener) {
        listeners.add(listener);
    }

    public void sendMessage(BaseMessage message) throws Exception {
        if (isConnected()) {
            session.write(message);
            log.info("Sent message type: {}", message.getClass().getSimpleName());
        } else
            log.warn("Can't send message, not connected");
    }

    public void connect() {
        connFuture = connector.connect(new InetSocketAddress(serverAddress, serverPort));
        connFuture.awaitUninterruptibly();
        connFuture.addListener(future -> {
            ConnectFuture connFuture = (ConnectFuture) future;
            if (connFuture.isConnected()) {
                session = future.getSession();
                log.info("Connected to {}:{}", serverAddress, serverPort);
                setConnected(true);
                listeners.parallelStream().forEach(IStarsClientListener::onConnected);
            } else {
                log.error("Not connected..");
            }
        });
    }

    @Override
    public void onMessageReceived(UdpNetworkMessage message, IoSession ioSession) {

    }
}
