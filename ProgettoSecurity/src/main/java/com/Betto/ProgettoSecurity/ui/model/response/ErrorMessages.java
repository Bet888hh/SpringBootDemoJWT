package com.Betto.ProgettoSecurity.ui.model.response;

public enum ErrorMessages {
  MISSING_REQUIRED_FIELD(
    "C'e' un campo mancante. Si prega di consultare la documentazione "
  ),
  RECORD_ALREADY_EXIST("gia' esiste"),
  NO_RECORD_FOUND("L'utente con l'id fornito non e' stato trovato"),
  AUTHENTICATION_FAILED("Autenticazione fallita"),
  COULD_NOT_UPDATE_RECORD("non ho pouto aggiornare"),
  COULD_NOT_DELETE_RECORD("non ho pouto eliminare"),
  INTERNAL_SERVER_ERROR("Internal server error");

  // TODO Auto-generated constructor stub

  private String errorMessage;

  ErrorMessages(String errorMessage) {
    // TODO Auto-generated constructor stub
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
