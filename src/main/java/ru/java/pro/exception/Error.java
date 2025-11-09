package ru.java.pro.exception;

public enum Error {
   ERROR_001( "ERROR 001");

   final String errorCode;

   Error(String errorCode) {
       this.errorCode = errorCode;
   }
   public String getErrorCode() {
       return errorCode;
   }
}
