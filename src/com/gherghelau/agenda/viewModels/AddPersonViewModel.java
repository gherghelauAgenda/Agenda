/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gherghelau.agenda.viewModels;

import com.gherghelau.agenda.AgendaForm;
import com.gherghelau.agenda.sql.DbConnect;
import com.gherghelau.agenda.models.Person;
import java.util.Vector;

/**
 *
 * @author Andrei
 */
public class AddPersonViewModel {
    
    private Vector<Person> persons;
    private Person person;
    private DbConnect connection;
    private AgendaForm form;
    private int lastId = -1;
    
    public AgendaForm getForm() { return form; }
    
    public Person getPerson() { return person; }
    
    public Vector<Person> getAll() { return persons; }
    
    public AddPersonViewModel(DbConnect conn , Person person , Vector<Person> persons , AgendaForm agForm){
        this.persons = persons;
        this.person = person;
        this.connection = conn;
        this.form = agForm;
    }
    
    public boolean addPersonToDb(int id , String fn , String ln , String picPath , String tel , String email ,int ownerId ,boolean update){
        if(!update){
            getLastId();
        }
        else{
            lastId = id;
        }
        
        person = new Person(lastId, fn, ln, picPath, tel, email, ownerId);
        persons.add(person);
        return connection.addPerson(person,update);
    }
    
    private void getLastId(){
        for(Person p : persons){
            if(p.getId() > this.lastId){
                lastId = p.getId();
            }
        }
        this.lastId ++;
    }
    
}
