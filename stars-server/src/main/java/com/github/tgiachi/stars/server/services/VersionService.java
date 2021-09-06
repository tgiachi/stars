package com.github.tgiachi.stars.server.services;

import com.github.tgiachi.stars.network.interfaces.services.IVersionService;
import org.springframework.stereotype.Service;


@Service
public class VersionService implements IVersionService {
    @Override
    public String getServerVersion() {
        return "1.0.0";
    }
}
