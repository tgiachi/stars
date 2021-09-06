package com.github.tgiachi.stars.network.messages;


import com.github.tgiachi.stars.network.base.BaseMessage;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WelcomeMessageRequest extends BaseMessage {
    private String clientVersion;
}
