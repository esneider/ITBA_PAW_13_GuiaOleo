package ar.edu.itba.it.paw.domain.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.edu.itba.it.paw.domain.PersistentEntity;
import ar.edu.itba.it.paw.domain.picture.Picture;
import ar.edu.itba.it.paw.domain.restaurant.Rating;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.utils.Utils;


@Entity
@Table(name = "SystemUser")
public class User extends PersistentEntity {

    private String name, surname, email, username, password;

    @Enumerated(EnumType.STRING)
    private UserType type;

    private Date registerDate;

    @OneToOne
    private Picture avatar;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Rating> comments;

    @ManyToMany(mappedBy = "likes")
    Set<Rating> userLikes = new HashSet<Rating>();

    @ManyToMany(mappedBy = "unlikes")
    Set<Rating> userUnlikes = new HashSet<Rating>();

    @OneToMany(mappedBy = "registerUser", cascade = CascadeType.ALL)
    private Set<Restaurant> registeredRestaurants;

    User() {}

    public User(String name, String surname, String email, String username,
                String password, Picture avatar, Date date, UserType type) {

    	setUsername(username);
        setName(name);
        setSurname(surname);
        setEmail(email);
        setPassword(password);
        setAvatar(avatar);
        setType(type);
        setRegisterDate(date);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Picture getAvatar() {
        return avatar;
    }
    
    public boolean checkPassword(String password) {
    	return this.password != null && this.password.equals(password);
    }

    public Set<Restaurant> getRegisteredRestaurants() {
        return registeredRestaurants;
    }

    public UserType getType() {
        return type;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public Set<Rating> getComments() {
        return comments;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    private void setUsername(String username) {

    	username = Utils.normalizeString(username);

        if (username.isEmpty()) {
            throw new IllegalArgumentException("Empty username");
        }

//        if (userRepo.usernameExists(username)) {
//        	throw new IllegalArgumentException("Duplicated username");
//        }

        this.username = username;
    }

    public void setType(UserType type) {

        if (type == null) {
            throw new IllegalArgumentException("Empty user type");
        }

        this.type = type;
    }

    public void setName(String name) {

        name = Utils.normalizeString(name);

        if (name.isEmpty()) {
            throw new IllegalArgumentException("Empty name");
        }

        this.name = name;
    }

    public void setSurname(String surname) {

        surname = Utils.normalizeString(surname);

        if (surname.isEmpty()) {
            throw new IllegalArgumentException("Empty surname");
        }

        this.surname = surname;
    }

    public void setEmail(String email) {

        email = Utils.normalizeString(email);

        if (this.email != null && this.email.equals(email)) {
            return;
        }

        if (email.isEmpty()) {
            throw new IllegalArgumentException("Empty email");
        }

        if (!Utils.isEmail(email)) {
            throw new IllegalArgumentException("Invalid email");
        }

//        if (Utils.emailExists(email)) {
//            throw new IllegalArgumentException("Duplicated email");
//        }

        this.email = email;
    }

    public void setPassword(String password) {

        password = Utils.normalizeString(password);

        if (password.isEmpty()) {
            throw new IllegalArgumentException("Empty password");
        }

        this.password = password;
    }

    public void setAvatar(Picture avatar) {

        if (avatar == null) {
            throw new IllegalArgumentException("Empty picture");
        }

        this.avatar = avatar;
    }

    public boolean isAdmin() {

        return type == UserType.Admin;
    }

    public Set<Rating> getLikes() {

        return userLikes;
    }

    public Set<Rating> getUnlikes() {

        return userUnlikes;
    }

    public void like(Rating r) {

        if (r == null) {
            throw new IllegalArgumentException("Empty rating");
        }

        userUnlikes.remove(r);

        if (userLikes.add(r)) {
            r.like(this);
        }
    }

    public void unlike(Rating r) {

        userLikes.remove(r);

        if (userUnlikes.add(r)) {
            r.unlike(this);
        }
    }

    @Override
    public int hashCode() {

        return super.hashCode() + 31 * ((username == null) ? 0 : username.hashCode());
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!super.equals(obj))
            return false;

        if (getClass() != obj.getClass())
            return false;

        User other = (User) obj;

        if (username == null && other.username != null)
            return false;

        if (username != null && !username.equals(other.username))
            return false;

        return true;
    }
}

