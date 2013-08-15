package models;

import java.util.*;
import javax.persistence.*;
import play.db.ebean.*;

@Entity
public class Change extends Model {

    @Id
    public Long id;
    public Date initiated;
    public String summary;
    public String description;
    public System system;
    public boolean iaoApproved;
    public boolean testApproved;

    @ManyToOne
    public User initiator;

    @ManyToOne
    public User builder;

//    @OneToMany(cascade = CascadeType.PERSIST)
//    public List<Outage> outages = new ArrayList<Outage>();

    public Change(String summary, String description, User initiator, User builder, System system) {
        this.summary = summary;
        this.description = description;
        this.initiator = initiator;
        this.builder = builder;
        this.system = system;
    }

    public static Model.Finder<Long,Change> find = new Model.Finder(Long.class, Change.class);

    public static Change create (String summary, String description, String initiator, String builder, Long system) {
        Change change = new Change(summary, description, User.find.ref(initiator),User.find.ref(builder),System.find.ref(system));
        change.save();
    //    change.saveOneToManyAssociations("initiator");
    //    change.saveOneToManyAssociations("builder");
   //     change.saveManyToOneAssociations("outages");
        return change;
    }

//    public static List<Project> findInvolving(String user) {
//        return find.where().eq("members.email", user).findList();
//    }
}
