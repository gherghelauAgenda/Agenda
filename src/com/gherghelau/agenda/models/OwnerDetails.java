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
public class OwnerDetails
        extends BaseModel
{
    
    private Owner owner; // just to get the model...
    private String firstName;
    private String lastName;
    private String email;
    private String picturePath;
    private int ownerId;
    
    public OwnerDetails(int id,Owner own){
        this.id = id;
        firstName = null;
        lastName = null;
        email = null;
        picturePath = null;
        owner = own;
        ownerId = own.getId();
    }
    
    public OwnerDetails(int id , String fn , String ln, String eM , String pPath , Owner own)
    {
        this.owner = own;
        this.ownerId = own.getId();
        this.firstName = fn;
        this.lastName = ln;
        this.email = eM;
        this.picturePath = pPath;
        this.id = id;
    }
    
    //getters
    public Owner getOwner() {
        return owner;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getPicturePath(){
        return picturePath;
    }
    
    public String getEmail(){
        return email;
    }
    
    public int getOwnerId(){
        return ownerId;
    }
    
    //setters--just for the properties that are changable
    public void setFirstName(String value){
        firstName = value;
    }
    
    public void setLastName(String value){
        lastName = value;
    }
    
    public void setEmail(String value){
        email = value;
    }
    
    public void setPicturePath(String value){
        picturePath = value;
    }
    
}
