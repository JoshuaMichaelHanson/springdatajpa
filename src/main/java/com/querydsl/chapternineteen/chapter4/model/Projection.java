package com.querydsl.chapternineteen.chapter4.model;

import org.springframework.beans.factory.annotation.Value;

/**
 * How should we approach projections in practice? If we include only methods such asa Ⓐ in listing 4.16, we’ll create
 * a closed projection—this is an interface whose getters all correspond to properties of the target entity.
 * When you’re working with a closed projection, the query execution can be optimized by Spring Data JPA because all
 * the properties needed by the projection proxy are known from the beginning.
 *
 * If we include methods such as Ⓑ, we create an open projection, which is more flexible. However, Spring Data JPA will
 * not be able to optimize the query execution, because the SpEL expression is evaluated at runtime and may include
 * any properties or combination of properties of the entity root.
 *
 * In general, you should use projections when you need to provide limited information and not expose the full entity.
 * For performance reasons, you should prefer closed projections whenever you know from the beginning which information
 */
public class Projection {

    public interface UserSummary {
        //A
        String getUsername();

        // B
        @Value("#{target.username} #{target.email}")
        String getInfo();
    }

    public static class UsernameOnly {
        private String username;

        public UsernameOnly(String username) {
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }
}
