package org.CHR1SSW.services;

import org.CHR1SSW.repositories.AmazonRepository;
import org.CHR1SSW.tables.AmazonTitles;

import java.util.List;

public class AmazonTitleService
{
    AmazonRepository amazonTitleRepository;

    public AmazonTitles createAmazonTitle(AmazonTitles amazonTitle)
    {
        return amazonTitleRepository.save(amazonTitle);
    }

    public AmazonTitles getAmazonTitle(int id)
    {
        return amazonTitleRepository.findOne(id);
    }

    public List<AmazonTitles> getAmazonTitles()
    {
        return amazonTitleRepository.findAll();
    }

    public void updateAmazonTitle(AmazonTitles amazonTitle)
    {
        amazonTitleRepository.save(amazonTitle);
    }

    public void deleteAmazonTitle(int id)
    {
        amazonTitleRepository.delete(id);
    }
}
