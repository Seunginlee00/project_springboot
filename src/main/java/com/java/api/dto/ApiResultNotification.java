package com.java.api.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResultNotification {
    private String notification;	// 구분명
    private String status;	//: notification.status, ('fail', 'error', 'successful' )
    private String status_message;	//: notification.status_message,
    private int status_code;	//: notification.statusCode,
    private String alert_message;	//: notification.console_message,
    private Object data;	//: data

}
