package com.querydsl.chapternineteen.chapter4.model;

import javax.persistence.*; // needed for queryDSL they have a fix to use the newer jakarta.persistence but
// not sure exactly how to do it yet

import java.time.LocalDate;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "users_chapter4")
public class UserChapter4 {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", length = 255)
    private String username;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "email")
    private String email;

    @Column(name = "level")
    private Integer level;

    @Column(name = "active")
    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserChapter4() {
    }

    public UserChapter4(String username) {
        this.username = username;
    }

    public UserChapter4(String username, LocalDate registrationDate) {
        this.username = username;
        this.registrationDate = registrationDate;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserChapter4)) return false;
        UserChapter4 that = (UserChapter4) o;
        return getId().equals(that.getId()) && getUsername().equals(that.getUsername()) && getRegistrationDate().equals(that.getRegistrationDate()) && getEmail().equals(that.getEmail()) && getLevel().equals(that.getLevel()) && getActive().equals(that.getActive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getRegistrationDate(), getEmail(), getLevel(), getActive());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserChapter4.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("username='" + username + "'")
                .add("registrationDate=" + registrationDate)
                .add("email='" + email + "'")
                .add("level=" + level)
                .add("active=" + active)
                .toString();
    }
}