package com.github.tgiachi.stars.network.server;


import com.github.tgiachi.stars.network.base.BaseMessage;
import com.github.tgiachi.stars.network.handlers.ServerPacketHandler;
import com.github.tgiachi.stars.network.interfaces.network.IIncomingMessageListener;
import com.github.tgiachi.stars.network.interfaces.services.ISessionsService;
import com.github.tgiachi.stars.network.interfaces.services.IStarsNetworkServerService;
import com.github.tgiachi.stars.network.interfaces.services.IVersionService;
import com.github.tgiachi.stars.network.protocol.StarsProtocolFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

@Service
@Slf4j
public class StarsNetworkServer implements IStarsNetworkServerService {


    @Value("${stars.server.port:5678}")
    private int listenPort;

    private final ISessionsService sessionsService;
    private final IVersionService versionService;
    private final NioDatagramAcceptor acceptor = new NioDatagramAcceptor();
    private ServerPacketHandler serverPacketHandler;
    private IIncomingMessageListener listener;

    public StarsNetworkServer(ISessionsService sessionsService, IVersionService versionService) {
        this.versionService = versionService;
        this.sessionsService = sessionsService;
    }

    public void setListener(IIncomingMessageListener listener) {
        this.listener = listener;
    }

    public <T extends BaseMessage> void sendMessage(T message, IoSession session) {
        session.write(message);
    }


    @Override
    public void start() throws Exception {
        this.serverPacketHandler = new ServerPacketHandler(sessionsService, versionService, listener);
        acceptor.setHandler(serverPacketHandler);
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();

        chain.addLast("protocol", new ProtocolCodecFilter(new StarsProtocolFactory()));

        DatagramSessionConfig dcfg = acceptor.getSessionConfig();
        dcfg.setReuseAddress(true);
        acceptor.bind(new InetSocketAddress(listenPort));
        log.info("Server is ready  on {} port", listenPort);

    }

}
