package com.DoyouEat.DYEat.controller.event;

import lombok.Data;

@Data
public class EventForm {
    private Long id;
    private String title;
    private String text;
    private String picture;
    private int onoff;
}
