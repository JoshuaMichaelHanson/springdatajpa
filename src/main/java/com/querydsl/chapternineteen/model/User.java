package com.querydsl.chapternineteen.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Getter
    private Long id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private LocalDate registrationDate;

    @Embedded
    @Getter
    @Setter
    private Address address;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private int level;

    @Getter
    @Setter
    private boolean active;

    @ToString.Exclude
    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn
    private Set<Bid> bids = new HashSet<>();

    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addBid(Bid bid) {
        bids.add(bid);
    }

    public Set<Bid> getBids() {
        return Collections.unmodifiableSet(bids);
    }
}
