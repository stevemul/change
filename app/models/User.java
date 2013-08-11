package models;

import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;

@Entity
public class User extends Model {

    @Id
    public String userid;
    public String name;
    public String password;
    
    public User(String userid, String name, String password) {
      this.userid = userid;
      this.name = name;
      this.password = password;
    }

    public static Finder<String,User> find = new Finder<String,User>(String.class, User.class); 

    public static User authenticate(String userid, String password) 
    {
        return find.where().eq("userid", userid).eq("password", password).findUnique();
    }
}
