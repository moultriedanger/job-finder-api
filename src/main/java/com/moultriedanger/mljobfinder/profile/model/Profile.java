package com.moultriedanger.mljobfinder.profile.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.moultriedanger.mljobfinder.user.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "profiles")
@Entity
public class Profile {


    @Id
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @Setter(AccessLevel.NONE)
    @JsonBackReference
    private User user;

    private String fullName;
    private String bio;
    private String profilePictureURL;


    protected Profile() {}
    public Profile(String fullName, String bio, String profilePictureURL){
        this.fullName = fullName;
        this.bio = bio;
        this.profilePictureURL = profilePictureURL;
    }


    public void setUser(User user) {
        this.user = user;
        if (user != null && user.getProfile() != this) {
            user.setProfile(this);
        }
    }
}
