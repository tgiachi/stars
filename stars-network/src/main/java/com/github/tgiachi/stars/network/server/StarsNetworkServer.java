package com.github.tgiachi.stars.network.server;


import com.github.tgiachi.stars.network.handlers.PacketHandler;
import com.github.tgiachi.stars.network.interfaces.services.ISessionsService;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.FilterEvent;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class StarsNetworkServer {


    public StarsNetworkServer(int listenPort) throws Exception {


        NioDatagramAcceptor acceptor = new NioDatagramAcceptor();
        acceptor.setHandler(new PacketHandler(new ISessionsService() {

        }));
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
        chain.addLast("logger", new LoggingFilter());
        DatagramSessionConfig dcfg = acceptor.getSessionConfig();
        dcfg.setReuseAddress(true);
        acceptor.bind(new InetSocketAddress(listenPort));
    }


    public void start() throws Exception {


    }
}
