package com.bizbuzz.dto;

public class CountryCodeDTO {
  
  private String countryName;
  private String numericCode;
  private String alphaNumericCode;

  
  
  public String getCountryName() {
    return countryName;
  }
  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }
  public String getNumericCode() {
    return numericCode;
  }
  public void setNumericCode(String numericCode) {
    this.numericCode = numericCode;
  }
  public String getAlphaNumericCode() {
    return alphaNumericCode;
  }
  public void setAlphaNumericCode(String alphaNumericCode) {
    this.alphaNumericCode = alphaNumericCode;
  }
  
}
