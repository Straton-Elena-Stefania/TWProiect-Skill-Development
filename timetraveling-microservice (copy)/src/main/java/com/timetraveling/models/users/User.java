package com.timetraveling.models.users;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "description", nullable = false)
    private String description = "";

    @Column(name = "time_travelling", nullable = false)
    private Skill timeTravelling = Skill.UNACTIVATED;

    @Column(name = "cooking", nullable = false)
    private Skill cooking = Skill.UNACTIVATED;

    @Column(name = "first_aid", nullable = false)
    private Skill firstAid = Skill.UNACTIVATED;

    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture = null;

    @Column(name = "profile_picture_type")
    private String profilePictureType = null;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Skill getTimeTravelling() {
        return timeTravelling;
    }

    public void setTimeTravelling(Skill timeTravelling) {
        this.timeTravelling = timeTravelling;
    }

    public Skill getCooking() {
        return cooking;
    }

    public void setCooking(Skill cooking) {
        this.cooking = cooking;
    }

    public Skill getFirstAid() {
        return firstAid;
    }

    public void setFirstAid(Skill firstAid) {
        this.firstAid = firstAid;
    }

    public List<String> getSkills() {
        List<String> skills = new ArrayList<>();
        if (firstAid != Skill.UNACTIVATED) {
            skills.add("First aid");
        }
        if (cooking != Skill.UNACTIVATED) {
            skills.add("Cooking");
        }
        if (timeTravelling != Skill.UNACTIVATED) {
            skills.add("Time travelling");
        }
        return skills;
    }

    public List<String> getFavouriteSkills() {
        List<String> skills = new ArrayList<>();
        if (firstAid == Skill.FAVOURITE) {
            skills.add("First aid");
        }
        if (cooking == Skill.FAVOURITE) {
            skills.add("Cooking");
        }
        if (timeTravelling == Skill.FAVOURITE) {
            skills.add("Time travelling");
        }
        return skills;
    }

    public String getProfilePicture() {
        if (profilePicture == null) {
            return "resources/images/user.png";
        }
        return "data:" + profilePictureType + ";base64," + Base64.getEncoder().encodeToString(profilePicture);
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getProfilePictureType() {
        return profilePictureType;
    }

    public void setProfilePictureType(String profilePictureType) {
        this.profilePictureType = profilePictureType;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}