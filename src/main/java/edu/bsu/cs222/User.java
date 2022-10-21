package edu.bsu.cs222;

public class User {


    public void createNewUser(String name, String email, String phoneNumber){

    }

    public void displayUserInformationOperator(){
        JSONReader reader = new JSONReader();
        String email = reader.parseEmail("ceendris@bsu.edu");
        System.out.println(email);
    }


//    public String getName() {
//        return name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
}
