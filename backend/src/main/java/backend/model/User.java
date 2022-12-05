package backend.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

	@Setter(AccessLevel.NONE)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "phone", unique = true)
	private String phone;

	@Column(name = "login_status")
	private Boolean loginStatus;

	@Column(name = "joined_time")
	private Timestamp joinedTime;

	@Column(name = "last_active_time")
	private Timestamp lastActiveTime;

	@Setter(AccessLevel.NONE)
	@Column(name = "friends")
	@ManyToMany
	private Set<User> friends;

	@Setter(AccessLevel.NONE)
	@Column(name = "black_list")
	@ManyToMany
	private Set<User> blackList;

	@OneToOne(mappedBy = "user", cascade = {CascadeType.ALL})
	private StudentProfile studentProfile;

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
	}

	/**
	 * Checks whether the target user is a friend of the current user
	 *
	 * @param user user to check for friend status
	 * @return boolean indicating mutual friendship status
	 */
	public boolean isFriendOf(User user) {
		return this.friends.contains(user) && user.friends.contains(this);
	}

	/**
	 * Adds a user to friend list and removes them from blacklist.
	 *
	 * @param user user to add to friendlist
	 */
	public void addFriend(User user) {
		this.blackList.remove(user);
		this.friends.add(user);
		user.getFriends().add(this);
	}

	/**
	 * Mutually excludes friendship
	 * (i.e. removes the target user from friend list and removes
	 * the current user from the target's friend list)
	 *
	 * @param user user to remove from friend list
	 */
	public void removeFriend(User user) {
		this.friends.remove(user);
		user.getFriends().remove(this);
	}

	/**
	 * Removes the target user from the friend list and
	 * adds them to the black list.
	 *
	 * @param user
	 */
	public void ghostFriend(User user) {
		this.removeFriend(user);
		this.blackList.add(user);
	}

	/**
	 * Removes the target user from the black list
	 *
	 * @param user
	 */
	public void unghostFriend(User user) {
		this.blackList.remove(user);
	}

	@Override
	public String toString() {
		return this.name;
	}

	/**
	 * Checks whether the target object is of instance user and
	 * has the same id as the current user.
	 *
	 * @param o target object to check for equivalence
	 * @return boolean indicating equivalence of target object and current user
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof User user)) {
			return false;
		}

		return this.id.equals(user.id);
	}
}
