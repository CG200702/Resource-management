/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resourcebookingsystem;

import java.util.ArrayList;

/**
 *
 * @author tim
 */
public class booker {
     private String name;
    private String email;

    public booker (String name, String email) {
        this.name = name;
        this.email = email;
        

    }

    booker(String bookerDetail, String bookerDetail0, String bookerDetail1, String bookerDetail2, String bookerDetail3, String bookerDetail4) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    public String toString(){
        return name+", "+email;
    }

}
