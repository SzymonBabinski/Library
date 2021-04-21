package com.library.repository;

import com.library.domain.Book;
import com.library.domain.Book.AuthorsNameAndAvgRatingOnly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    @Query("select b from Book b join b.categories c where c.name = :category")
    List<Book> findBooksByCategory(String category);

    @Query("select AVG (b.averageRating) as averageRating, a.name as name from Book b " +
            "inner join b.authors a GROUP BY a.name HAVING b.averageRating > 0")
    List<AuthorsNameAndAvgRatingOnly> findAuthorsByRatings();

}
