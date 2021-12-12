package com.acko.pager.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlertResponse {
    String name;
    String phone;
    String messageSent;
    String alertResponseMessage;
}
