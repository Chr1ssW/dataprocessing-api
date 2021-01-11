package org.CHR1SSW.repositories;

import org.CHR1SSW.tables.AmazonTitles;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AmazonTitlesRepository extends PagingAndSortingRepository<AmazonTitles, Integer>
{
    AmazonTitles findBySeriesNumber(int seriesNumber);

    @Override
    List<AmazonTitles> findAll();
}
