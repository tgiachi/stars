package com.github.tgiachi.stars.network.server;


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
        acceptor.setHandler(new IoHandler() {
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
        });
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
        chain.addLast("logger", new LoggingFilter());
        DatagramSessionConfig dcfg = acceptor.getSessionConfig();
        dcfg.setReuseAddress(true);acceptor.bind(new InetSocketAddress(listenPort));
    }



    public void start() throws Exception {


    }
}
