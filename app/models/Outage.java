package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;

@Entity
public class Outage extends Model {

    @Id
    public Long id;
    public System system;
    public int length;
    public String description;

    public Outage(System system, int length, String desc) {
        this.system = system;
        this.length = length;
        this.description = desc;
    }

    public static Model.Finder<Long,Outage> find = new Model.Finder(Long.class, Outage.class);

    public static Outage create(Long system, int length, String desc) {
        Outage outage = new Outage(System.find.ref(system), length, desc);
        outage.save();
        return outage;
    }

    public static List<Outage> findAffecting(String system) {
        return find.where()
            .eq("System.name", system)
            .findList();
    }
}
