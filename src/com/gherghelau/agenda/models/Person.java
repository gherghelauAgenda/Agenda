/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gherghelau.agenda.models;

/**
 *
 * @author Andrei
 */
public class Person 
            extends BaseModel
{
    //toDo : rest of details
    private String firstname;
    private String lastname;
    private String picturePath;
    private String telephone;
    private String email;
    private int ownerId;
    
    public void setFirstname(String value) { firstname = value; }
    public void setLastname(String value) { lastname = value; }
    public void setPicturePath(String value) { picturePath = value; }
    public void setTelephone(String value) { telephone = value; }
    public void setEmail(String value) { email = value; }
    
    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getPicturePath() { return picturePath; }
    public String getTelephone() { return telephone; }
    public String getEmail() { return email; }
    public int getOwnerId() { return ownerId; }
    
    public Person(String fn , String ln , String picPath, String phone , String email,int oId){
        this.firstname = fn;
        this.lastname = ln;
        this.picturePath = picPath;
        this.telephone = phone;
        this.email = email;
        this.ownerId = oId;
    }
    
    public Person(int id,String fn , String ln , String picPath, String phone , String email,int oId){
        this.id = id;
        this.firstname = fn;
        this.lastname = ln;
        this.picturePath = picPath;
        this.telephone = phone;
        this.email = email;
        this.ownerId = oId;
    }
}
