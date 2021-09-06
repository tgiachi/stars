package com.github.tgiachi.stars.network.messages;

import com.github.tgiachi.stars.network.base.BaseMessage;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WelcomeMessageResponse extends BaseMessage {
    private boolean success;
    private String serverVersion;
}
