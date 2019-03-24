package com.example.shoppingcart.Model;

public class Users {
  private String mobilenum,name,pass;

  public Users(){

  }

    public Users(String mobilenum1,String name1, String pass1){
          this.mobilenum = mobilenum1;
          this.name = name1;
          this.pass = pass1;


   }

    public String getMobilenum() {

      return this.mobilenum;
    }

    public void setMobilenum(String mobilenum) {
        this.mobilenum = mobilenum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

      this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
