package com.app.toko.payload.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageResponse {

  private String message;
  private int statusCode;
  private LocalDateTime timeStamp;
}
