package com.app.toko.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {
    private String message;
    private String to;
    private boolean isSeen;
    private LocalDateTime datetime;
}
