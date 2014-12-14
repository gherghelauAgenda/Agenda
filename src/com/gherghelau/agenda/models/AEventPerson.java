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
public class AEventPerson
        extends BaseModel {
    // FK to EVENT TABLE
    private int eventId;
    // FK To PERSON TABLE
    private int personId;
    
    public int getEventId(){
        return this.eventId;
    }
    
    public int getPersonId(){
        return this.personId;
    }
    
}
