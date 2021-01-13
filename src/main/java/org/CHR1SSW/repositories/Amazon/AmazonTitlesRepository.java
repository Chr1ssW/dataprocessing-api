package org.CHR1SSW.repositories.Amazon;

import org.CHR1SSW.tables.Amazon.AmazonTitles;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AmazonTitlesRepository extends PagingAndSortingRepository<AmazonTitles, Integer>
{
    AmazonTitles findById(int seriesNumber);

    @Override
    List<AmazonTitles> findAll();
}
