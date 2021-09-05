package com.github.tgiachi.stars.network.server;

import com.github.tgiachi.stars.network.handlers.IncomingPacketHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetAddress;

public class StarsNetworkServer {

    private int listenPort;
    private IncomingPacketHandler incomingPacketHandler;
    private Channel channel;


    public StarsNetworkServer(int listenPort) {
        this.listenPort = listenPort;
        incomingPacketHandler = new IncomingPacketHandler();
    }



    public void start() throws Exception {
        final NioEventLoopGroup group = new NioEventLoopGroup();
        final Bootstrap b = new Bootstrap();

        b.group(group).channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new ChannelInitializer<NioDatagramChannel>() {
                    @Override
                    public void initChannel(final NioDatagramChannel ch) throws Exception {

                        ChannelPipeline p = ch.pipeline();
                        p.addLast(incomingPacketHandler);
                    }
                });

        // Bind and start to accept incoming connections.
        InetAddress address = InetAddress.getLocalHost();

        channel = b.bind(address, listenPort).sync().channel();
        channel.closeFuture().await();

    }
}
