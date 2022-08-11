package vn.techmaster.storyreadingwebsite.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.techmaster.storyreadingwebsite.entity.Story;

import java.util.List;
import java.util.Optional;


@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

    @Query("select s from story s")
    Page<Story> findAllByPage(String keyword, Pageable pageable);


    @Query("select s from story s where upper(s.title) like upper(concat('%', ?1, '%'))")
    List<Story> findByTitleContainsIgnoreCase(String title);

    @Query("select s from story s inner join s.categories categories where categories.id = ?1")
    List<Story> findAllByCategoryId(Long cId);

    Optional<Story> findById(Long id);

    @Query("SELECT s AS user_id FROM story AS s WHERE s.id = :id")
    Optional<Story> findStoryWithUserById(@Param("id") Long id);

}
