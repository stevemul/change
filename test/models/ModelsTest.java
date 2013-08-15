package models;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;
import java.util.*;
import play.libs.*;
import com.avaje.ebean.Ebean;

public class ModelsTest extends WithApplication 
{    
    @Before
    public void setUp() 
    {
        start(fakeApplication(inMemoryDatabase()));
    }

    @Test
    public void createAndRetrieveUser() 
    {
        new User("bob@gmail.com", "Bob", "secret").save();
        User bob = User.find.where().eq("userid", "bob@gmail.com").findUnique();
        assertNotNull(bob);
        assertEquals("Bob", bob.name);
    }
  
    @Test
    public void tryAuthenticateUser() 
    {
        new User("bob@gmail.com", "Bob", "secret").save();
        
        assertNotNull(User.authenticate("bob@gmail.com", "secret"));
        assertNull(User.authenticate("bob@gmail.com", "badpassword"));
        assertNull(User.authenticate("tom@gmail.com", "secret"));
    }

    @Test
    public void fullTest() {
        Ebean.save((List) Yaml.load("test-data.yml"));

        assertEquals(3, User.find.findRowCount());
        assertEquals(1, Change.find.findRowCount());
        assertEquals(1, Outage.find.findRowCount());
	assertEquals(1, System.find.findRowCount());


        assertNotNull(User.authenticate("bob@example.com", "secret"));
        assertNotNull(User.authenticate("jane@example.com", "secret"));
        assertNull(User.authenticate("jeff@example.com", "badpassword"));
        assertNull(User.authenticate("tom@example.com", "secret"));
       
    }
}
