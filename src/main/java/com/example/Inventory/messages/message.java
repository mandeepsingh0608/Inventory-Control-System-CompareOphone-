package com.example.Inventory.messages;

import lombok.AllArgsConstructor;
import lombok.Data;


//this message class to send different messages in response of http request
@AllArgsConstructor
@Data
public class message {
    private String content;
    private String type;

    public message() {
    }
}
