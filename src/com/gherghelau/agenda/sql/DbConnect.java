/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gherghelau.agenda.sql;


import com.gherghelau.agenda.models.AEvent;
import com.gherghelau.agenda.models.Owner;
import com.gherghelau.agenda.models.OwnerDetails;
import com.gherghelau.agenda.models.Person;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import org.apache.derby.jdbc.ClientDriver;
/**
 *
 * @author Andrei
 */
public class DbConnect {
    
    private final static String host = "jdbc:derby://localhost:1527/agendaDb";
    private final static String uName = "andrei";
    private final static String uPass = "admin";
    
    private static Connection con ;
    
    private static DbConnect _instance;
    
    public static DbConnect getInstance(){
        if(_instance == null){
            _instance = new DbConnect();
        }
        return _instance;
    }
    
    private DbConnect(){
        runConnection();
    }
    
    private void runConnection(){
        try{
            con = DriverManager.getConnection(host, uName, uPass);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public Vector<Owner> getOwners(){
        
        Vector<Owner> owners = new Vector<Owner>();
        try{
            Statement st = con.createStatement();
            String query = "SELECT * FROM OWNER";
            ResultSet rs  = st.executeQuery(query);
            while(rs.next())
            {
                int id = rs.getInt("ID");
                String user = rs.getString("USERNAME");
                String pwd = rs.getString("PASSWORD");
                
                owners.add(new Owner(user,pwd,id));
            }
        }catch(Exception ex){
             System.out.println(ex.getMessage());
        }
        
        return owners;
    }
    
    public boolean addOwner(Owner owner){
        try{
            Statement statement = con.createStatement();
            int id = owner.getId();
            String sql = "INSERT INTO ANDREI.OWNER " +
                    "VALUES(" +  String.valueOf(id) + ", '" + owner.getUsername() + "' , '" + owner.getPassword() + "')"; 
            int hRes = statement.executeUpdate(sql);
            if(hRes > 0){
                return true;
            }
            return false;
        }catch(Exception ex){
            return false;
        }
    }
    
    public Vector<Person> getPersonFromOwnerId(int id){
        Vector<Person> persons = new Vector<Person>();
        try{
            Statement st = con.createStatement();
            String query = "SELECT * FROM ANDREI.PERSON WHERE OWNER_ID = " + 
                    String.valueOf(id) +
                    " ORDER BY LAST_NAME,FIRST_NAME";
            ResultSet rs  = st.executeQuery(query);
            while(rs.next())
            {
                int _id = rs.getInt("ID");
                String firstname = rs.getString("FIRST_NAME");
                String lastname = rs.getString("LAST_NAME");
                String phone = rs.getString("TELEPHONE");
                String picPath = rs.getString("PICTURE_PATH");
                String email = rs.getString("EMAIL");
                int _oId = rs.getInt("OWNER_ID");
                
                persons.add(new Person(_id,firstname, lastname, picPath, phone, email,_oId));
            }
        }catch(Exception ex){
             System.out.println(ex.getMessage());
        }
        return persons;
    }

    public boolean addPerson(Person person,boolean update) {
        if(update)
        {
            try{
                Statement statement = con.createStatement();
                String sql = "UPDATE ANDREI.PERSON SET " +
                        "FIRST_NAME = " + "'" + person.getFirstname() + "'" +
                        ",LAST_NAME = " + "'" + person.getLastname() + "'" +
                        ",EMAIL = " + "'" + person.getEmail() + "'" +
                        ",PICTURE_PATH = " + "'" + person.getPicturePath() + "'" +
                        ",TELEPHONE = " + "'" + person.getTelephone() + "'" +
                        " WHERE ID = " + String.valueOf(person.getId());
                
                int hRes = statement.executeUpdate(sql);
                
                if(hRes > 0){
                    return true;
                }
                return false;
            }catch(Exception ex){
                return false;
            }
        }
        else
        {
           try{
                Statement statement = con.createStatement();
                String sql = "INSERT INTO ANDREI.PERSON VALUES ( " +
                        String.valueOf(person.getId()) + 
                        ",'" + person.getFirstname() + "'" +
                        ",'" + person.getLastname() + "'" +
                        ",'" + person.getTelephone() + "'" +
                        ",'" + person.getPicturePath() + "'," +
                        String.valueOf(person.getOwnerId()) + ","
                        + "'" + person.getEmail() + "')";
                
                int hRes = statement.executeUpdate(sql);
                if(hRes > 0){
                    return true;
                }
                return false;
            }catch(Exception ex){
                return false;
            }
        }
    }
    
    public boolean removePerson(int id){
        try{
            Statement statement = con.createStatement();
            String delQuery = "DELETE FROM ANDREI.PERSON WHERE ID = " + String.valueOf(id);
            return (statement.executeUpdate(delQuery) > 0) ?
                    true : false;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public OwnerDetails getDetailsFrom(Owner owner){
        
        try{
            Statement pStatement = con.createStatement();
            String selectQuery = "SELECT * FROM ANDREI.OWNER_DETAILS WHERE OWNER_ID = " + String.valueOf(owner.getId());
            ResultSet rs  = pStatement.executeQuery(selectQuery);
            while(rs.next()){
                int id = rs.getInt("ID");
                String firstName = rs.getString("FIRST_NAME");
                String lastName = rs.getString("LAST_NAME");
                String email = rs.getString("EMAIL");
                String picPath = rs.getString("PICTURE_PATH");
                return new OwnerDetails(id, firstName, lastName, email, picPath, owner);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    public boolean addOwnerDetails(OwnerDetails details,boolean update) {
        if(update)
        {
            try{
                Statement statement = con.createStatement();
                String sql = "UPDATE ANDREI.OWNER_DETAILS SET " +
                        "FIRST_NAME = " + "'" + details.getFirstName()+ "'" +
                        ",LAST_NAME = " + "'" + details.getLastName() + "'" +
                        ",EMAIL = " + "'" + details.getEmail() + "'" +
                        ",PICTURE_PATH = " + "'" + details.getPicturePath() + "'" +
                        " WHERE ID = " + String.valueOf(details.getId());
                
                int hRes = statement.executeUpdate(sql);
                
                if(hRes > 0){
                    return true;
                }
                return false;
            }catch(Exception ex){
                return false;
            }
        }
        else
        {
           try{
                Statement statement = con.createStatement();
                String sql = "INSERT INTO ANDREI.OWNER_DETAILS VALUES ( " +
                        String.valueOf(details.getId()) + 
                        ",'" + details.getPicturePath() + "'," +
                        "'" + details.getEmail() + "'" +
                        ",'" + details.getFirstName() + "'" +
                        ",'" + details.getLastName() + "'," +
                        String.valueOf(details.getOwnerId()) + ")";
                
                int hRes = statement.executeUpdate(sql);
                if(hRes > 0){
                    return true;
                }
                return false;
            }catch(Exception ex){
                return false;
            }
        }
    }
    
    public int getIdForNewOwnerDetails(){
        try{
            Statement st = con.createStatement();
            String query = "SELECT MAX(ID) AS MAX_ID FROM OWNER_DETAILS";
            ResultSet rs  = st.executeQuery(query);
            rs.next();
            return rs.getInt("MAX_ID");
        }catch(Exception ex){
            return -1;
        }
    }
    
    public Vector<AEvent> getAllEventFor(Owner owner)
    {
        Vector<AEvent> result = new Vector<AEvent>();
        try
        {
            Statement statement = con.createStatement();
            
            String sql = "SELECT * FROM EVENTS WHERE ID_OWNER = " + owner.getId();
            
            ResultSet rs = statement.executeQuery(sql);
            
            while(rs.next()){
                int _id = rs.getInt("ID");
                int _ownerId = rs.getInt("ID_OWNER");
                //Calendar//
                Date _date = rs.getDate("DATA");
                String _title = rs.getString("TITLE");
                String _description = rs.getString("DESCRIPTION");
                
                AEvent toAdd = new AEvent(_id, _ownerId, _title, _description, _date);
                
                result.add(toAdd);
            }
            
            return result;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public boolean saveEvent(AEvent model){
        if(model == null){
            System.out.println("Don't send null models kid!");
            return false;
        }
        try
        {
            int id = getIdForEvent(model);
            //Update not INSERT!
            if(model.getId() != 0){
                return updateEvent(model);
            }
            
            Statement statement = con.createStatement();
            
            String insertSql = "INSERT INTO EVENTS VALUES("
                    + String.valueOf(id)
                    + " , '" + model.getSqlDate() + "' ,"
                    + "'" + model.getTitle() + "' , " 
                    + "'"+ model.getDescription() + "',"
                    + String.valueOf(model.getOwnerId()) + ")";
            
            return statement.executeUpdate(insertSql) > 0 ? true : false;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
        
    }

    private int getIdForEvent(AEvent model) {
        
        if(model.getId() == 0){
            try{
                Statement statement = con.createStatement();
                String sql = "SELECT MAX(ID) AS MAX_ID FROM EVENTS";
                ResultSet rs = statement.executeQuery(sql);
                rs.next();
                int idToRet = rs.getInt("MAX_ID");
                return (idToRet + 1);
            }
            catch(Exception ex){
                System.out.print(ex.getMessage());
            }
        }
        return model.getId();
    }

    private boolean updateEvent(AEvent model) {
        try
        {
            Statement statement = con.createStatement();
            String sql = "UPDATE EVENTS "
                    + "SET \"DATA\" = '" + model.getSqlDate() + "',"
                    + "TITLE = '" + model.getTitle() + "',"
                    + "DESCRIPTION = '" + model.getDescription() + "' "
                    + "WHERE ID = " + String.valueOf(model.getId());
            
            int res = statement.executeUpdate(sql);
            return res > 0 ;
        }
        catch(Exception ex){
            
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
}
