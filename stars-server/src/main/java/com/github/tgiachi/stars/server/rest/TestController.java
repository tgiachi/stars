package com.github.tgiachi.stars.server.rest;

import com.github.tgiachi.stars.network.client.StarsNetworkClient;
import com.github.tgiachi.stars.network.interfaces.network.IStarsClientListener;
import com.github.tgiachi.stars.network.messages.WelcomeMessageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController implements IStarsClientListener {

    private StarsNetworkClient client;

    @GetMapping
    public ResponseEntity<Boolean> testClient() {
        client = new StarsNetworkClient("localhost", 5678);
        client.addListener(this);
        client.connect();

        return ResponseEntity.ok(true);
    }

    @Override
    public void onConnected() {
        try {
            client.sendMessage(WelcomeMessageRequest.builder().clientVersion("1.0.0").build());
        } catch (Exception ex) {

        }
    }
}
