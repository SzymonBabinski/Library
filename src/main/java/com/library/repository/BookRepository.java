package com.library.repository;

import com.library.dao.AuthorsAndAvgRatingOnly;
import com.library.domain.Book;
import com.library.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    @Query("select b from Book b join b.categories c on(c.name = :category)")
    List<Book> findBooksByCategory(String category);

    @Query("select AVG (b.averageRating) as averageRating, a.name as name from Book b " +
            "inner join b.authors a GROUP BY a.name HAVING b.averageRating > 0 ORDER BY b.averageRating DESC")
    List<AuthorsAndAvgRatingOnly> findAuthorsByRatings();

    Optional<Book> findTopBookByPageCountGreaterThan(int pageCount);

    @Query("select b from Book b " +
            "where b.pageCount <= :pagesMonthly AND b.pageCount <> 0 ORDER BY b.averageRating DESC")
    List<Book> findBooksByPagesMonthly(int pagesMonthly);

    @Query("select distinct c.name from Category c")
    List<Category> findAllCategories();

}
