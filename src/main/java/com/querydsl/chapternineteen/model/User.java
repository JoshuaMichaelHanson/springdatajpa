package com.querydsl.chapternineteen.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
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
