package backend.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "joinedTime")
    private Timestamp joinedTime;

    @Column(name = "lastActiveTime")
    private Timestamp lastActiveTime;

    @Column(name = "emailValidated")
    private boolean emailValidated;

    @Column(name = "friends")
    @OneToMany
    private List<User> friends;

    @Column(name = "blackList")
    @OneToMany
    private List<User> blackList;

    @Column(name = "phone")
    private String phone;

    @Column(name = "profile")
    private StudentProfile profile;

    public User() {
    }

    public User(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.joinedTime = new Timestamp(System.currentTimeMillis());
        this.lastActiveTime = ;
        this.emailValidated = false;
        this.friends = new ArrayList<User>();
        this.blackList = new ArrayList<User>();
        this.profile = new StudentProfile();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;
        return this.id.equals(user.id);
    }
}
