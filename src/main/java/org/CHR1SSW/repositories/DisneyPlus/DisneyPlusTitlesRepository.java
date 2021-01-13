package org.CHR1SSW.repositories.DisneyPlus;

import org.CHR1SSW.tables.DisneyPlus.DisneyPlusTitles;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DisneyPlusTitlesRepository extends PagingAndSortingRepository<DisneyPlusTitles, String>
{
    DisneyPlusTitles findByImdbId(String imdbId);
    List<DisneyPlusTitles> findAllByTitle(String title);

    @Override
    List<DisneyPlusTitles> findAll();
}
