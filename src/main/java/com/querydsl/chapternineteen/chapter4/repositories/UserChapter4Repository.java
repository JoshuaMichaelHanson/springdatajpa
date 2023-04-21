package com.querydsl.chapternineteen.chapter4.repositories;

import com.querydsl.chapternineteen.chapter4.model.Projection;
import com.querydsl.chapternineteen.chapter4.model.UserChapter4;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface UserChapter4Repository extends JpaRepository<UserChapter4, Long> {
    UserChapter4 findByUsername(String username);
    List<UserChapter4> findAllByOrderByUsernameAsc();
    List<UserChapter4> findByRegistrationDateBetween(LocalDate start, LocalDate end);
    List<UserChapter4> findByUsernameAndEmail(String username, String email);
    List<UserChapter4> findByUsernameOrEmail(String username, String email);
    List<UserChapter4> findByUsernameIgnoreCase(String username);
    List<UserChapter4> findByLevelOrderByUsernameDesc(int level);
    List<UserChapter4> findByLevelGreaterThanEqual(int level);
    List<UserChapter4> findByUsernameContaining(String text);
    List<UserChapter4> findByUsernameLike(String text);
    List<UserChapter4> findByUsernameStartingWith(String start);
    List<UserChapter4> findByUsernameEndingWith(String end);
    List<UserChapter4> findByActive(boolean active);
    List<UserChapter4> findByRegistrationDateIn(Collection<LocalDate> dates);
    List<UserChapter4> findByRegistrationDateNotIn(Collection<LocalDate> dates);

    // add paging and sorting
    UserChapter4 findFirstByOrderByUsernameAsc();
    UserChapter4 findTopByOrderByRegistrationDateDesc();
    Page<UserChapter4> findAll(Pageable pageable);
    List<UserChapter4> findFirst2ByLevel(int level, Sort sort);
    List<UserChapter4> findByLevel(int level, Sort sort);
    List<UserChapter4> findByActive(boolean active, Pageable pageable);

    // add some streamables
    Streamable<UserChapter4> findByEmailContaining(String text);
    Streamable<UserChapter4> findByLevel(int level);

    // @Query annotation -- limit, sorting and pagination
    @Query("select count(u) from UserChapter4 u where u.active = ?1")
    int findNumberOfUsersByActivity(boolean active);

    @Query("select u from UserChapter4 u where u.level = :level and u.active = :active")
    List<UserChapter4> findByLevelAndActive(@Param("level") int level, @Param("active") boolean active);

    @Query(value = "SELECT COUNT(*) FROM users_chapter4 WHERE ACTIVE = ?1", nativeQuery = true)
    int findNumberOfUsersByActivityNative(boolean active);

    @Query("select u.username, LENGTH(u.email) as email_length from #{#entityName} u where u.username like %?1%")
    List<Object[]> findByAsArrayAndSort(String text, Sort sort);

    // add some examples of Projections
    List<Projection.UserSummary> findByRegistrationDateAfter(LocalDate date);
    List<Projection.UsernameOnly> findByEmail(String username);

    <T> List<T> findByEmail(String username, Class<T> type);

    // Add some examples of projections with @Modifying
    @Modifying
    @Transactional
    @Query("update UserChapter4 u set u.level = ?2 where u.level = ?1")
    int updateLevel(int oldLevel, int newLevel);

    @Transactional
    int deleteByLevel(int level);

    @Transactional
    @Modifying
    @Query("delete from UserChapter4 u where u.level = ?1")
    int deleteBulkByLevel(int level);
}