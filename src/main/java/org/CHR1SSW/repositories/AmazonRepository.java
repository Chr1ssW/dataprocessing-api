package org.CHR1SSW.repositories;

import org.CHR1SSW.tables.AmazonTitles;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface AmazonRepository extends PagingAndSortingRepository<AmazonTitles, Integer>
{
    AmazonTitles findById(int id);

    @Override
    List<AmazonTitles> findAll();
}
