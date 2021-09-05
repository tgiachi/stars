package com.github.tgiachi.stars.network.handlers;

import com.github.tgiachi.stars.network.builders.NetworkMessageBuilder;
import com.github.tgiachi.stars.network.interfaces.network.IIncomingMessageListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.DatagramPacket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class IncomingPacketHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private final List<IIncomingMessageListener> listeners = new ArrayList<>();

    public void addListener(IIncomingMessageListener listener) {
        listeners.add(listener);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {

        var scrAddr = msg.getAddress();
        var byteMessage = msg.getData();
        var parsedMessage = NetworkMessageBuilder.parseMessage(new String(byteMessage, StandardCharsets.UTF_8));
        listeners.parallelStream().forEach(listener -> listener.onMessageReceived(parsedMessage, scrAddr));

        ctx.fireChannelReadComplete();
    }


}
