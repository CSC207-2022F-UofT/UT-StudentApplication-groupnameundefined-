package backend.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

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

    @Column(name = "phone")
    private String phone;

    @Column(name = "loginStatus")
    private boolean loginStatus;

    @Column(name = "joinedTime")
    private Timestamp joinedTime;

    @Column(name = "lastActiveTime")
    private Timestamp lastActiveTime;

    @Column(name = "friends")
    @ManyToMany
    private List<User> friends;

    @Column(name = "blackList")
    @ManyToMany
    private List<User> blackList;

//    @Column(name = "profile")
//    private StudentProfile profile;

//    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "from")
//    private List<FriendRequest> sentFriendRequests;
//
//    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "to")
//    private List<FriendRequest> receivedFriendRequests;


    public User() {

    }

    public User(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.loginStatus = false;
        this.joinedTime = new Timestamp(System.currentTimeMillis());
        this.lastActiveTime = new Timestamp(System.currentTimeMillis());
        this.friends = new ArrayList<User>();
        this.blackList = new ArrayList<User>(); // users in the black list should not appear in the friend list.
//        this.profile = new StudentProfile(); // profile is an empty object by default.
    }


    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhone() {
        return this.phone;
    }

    public boolean getLoginStatus() { return this.loginStatus; }

    public Timestamp getJoinedTime() {
        return this.joinedTime;
    }

    public Timestamp getLastActiveTime() {
        return this.lastActiveTime;
    }

    public List<User> getFriends(){
        return this.friends;
    }

    public List<User> getBlackList(){
        return this.blackList;
    }

//    public StudentProfile getProfile() {
//        return this.profile;
//    }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(){
        this.phone = phone;
    }

    public void setLogin() { this.loginStatus = true; }

    public void setLogout() {this.loginStatus = false; }

    public void setLastActiveTime(Timestamp lastActiveTime){
        this.lastActiveTime = lastActiveTime;
    }

    public void setBlackListEmpty(){
        this.blackList = new ArrayList<User>();
    }

    public void setStudentProfile(){
        // overload this method with different arguments to set different options in a profile.
    }

//some methods that would help friendRequest backend:
    public static boolean isFriend(User user1, User user2){
        return user1.friends.contains(user2) & user2.friends.contains(user1);
    } //mutually

    public void addFriend(User user){
        this.blackList.remove(user);
        this.friends.add(user);
        user.friends.add(this);
    } //mutually, add as a friend automatically means not ghosting this user anymore.

    public void removeFriend(User user){
        this.friends.remove(user);
        user.friends.remove(this);
    } //mutually, remove a friend from your friend list automatically remove you from his/her friend list.

    public void ghostFriend(User user){
        this.removeFriend(user);
        this.blackList.add(user);
    }

    public void unghostFriend(User user){
        this.blackList.remove(user);
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
