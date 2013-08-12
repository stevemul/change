package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;

@Entity
public class System extends Model {

    @Id
    public Long id;
    public String name;
    public User iao;

    public System(String name, User iao){
        this.name = name;
        this.iao = iao;
    }

    public static Model.Finder<Long,System> find = new Model.Finder(Long.class, System.class);

    public static System create(String name, String iao) {
        System system= new System(name, User.find.ref(iao));
        system.save();
        return system;
    }

    
}
