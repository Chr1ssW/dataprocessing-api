package org.CHR1SSW.repositories.Netflix;

import org.CHR1SSW.tables.Netflix.NetflixTitles;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface NetflixTitlesRepository extends PagingAndSortingRepository<NetflixTitles, Integer>
{
    NetflixTitles findByShowId(int showId);
    NetflixTitles findByTitle(String title);

    @Override
    List<NetflixTitles> findAll();
}
