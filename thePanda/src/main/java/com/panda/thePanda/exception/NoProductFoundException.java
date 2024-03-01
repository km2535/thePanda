package com.panda.thePanda.exception;

public class NoProductFoundException extends Exception {
  /**
   *
   */
  private static final long serialVersionUID = 264844169359085199L;

  public NoProductFoundException() {
    super("No product found");
  }

  public NoProductFoundException(String message) {
    super(message);
  }

  public NoProductFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public NoProductFoundException(Throwable cause) {
    super(cause);
  }
}
