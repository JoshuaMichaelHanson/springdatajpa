package com.querydsl.chapternineteen.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Getter
    @Setter
    private BigDecimal amount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter
    @Setter
    private User user;

    public Long getId() {
        return id;
    }

    public Bid(BigDecimal amount) {
        this.amount = amount;
    }

}
