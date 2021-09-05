package com.github.tgiachi.stars.network.interfaces.network;

import com.github.tgiachi.stars.network.base.UdpNetworkMessage;

import java.net.InetAddress;

public interface IIncomingMessageListener {
    void onMessageReceived(UdpNetworkMessage message, InetAddress address);
}
