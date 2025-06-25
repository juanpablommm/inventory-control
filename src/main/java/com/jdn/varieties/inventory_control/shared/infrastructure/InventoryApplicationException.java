package com.jdn.varieties.inventory_control.shared.infrastructure;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InventoryApplicationException extends RuntimeException {

  private final String responseMessage;
  private final String technicalMessage;
  private final HttpStatus httpStatus;

  public InventoryApplicationException(String message, String technicalMessage, HttpStatus httpStatus) {
    super(message);
    this.responseMessage = message;
    this.technicalMessage = technicalMessage;
    this.httpStatus = httpStatus;
  }
}

