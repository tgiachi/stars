package com.github.tgiachi.stars.server.services;

import com.github.tgiachi.stars.network.base.UdpNetworkMessage;
import com.github.tgiachi.stars.network.interfaces.network.IIncomingMessageListener;
import com.github.tgiachi.stars.network.messages.WelcomeMessageResponse;
import com.github.tgiachi.stars.network.server.StarsNetworkServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class BootstrapService implements IIncomingMessageListener {
    private final StarsNetworkServer starsNetworkServer;

    public BootstrapService(StarsNetworkServer starsNetworkServer) {
        this.starsNetworkServer = starsNetworkServer;
        this.starsNetworkServer.setListener(this);

    }

    @PostConstruct
    private void init() throws Exception {

        starsNetworkServer.start();
    }

    @Override
    public void onMessageReceived(UdpNetworkMessage message, IoSession session) {
        session.write(WelcomeMessageResponse.builder().success(true).serverVersion("1.0.0").build());
    }
}
