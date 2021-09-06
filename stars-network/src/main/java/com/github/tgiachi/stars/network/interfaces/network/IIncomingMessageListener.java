package com.github.tgiachi.stars.network.interfaces.network;

import com.github.tgiachi.stars.network.base.UdpNetworkMessage;
import org.apache.mina.core.session.IoSession;

public interface IIncomingMessageListener {
    void onMessageReceived(UdpNetworkMessage message, IoSession session);
}
