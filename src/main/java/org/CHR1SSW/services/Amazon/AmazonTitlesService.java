package org.CHR1SSW.services.Amazon;

import org.CHR1SSW.repositories.Amazon.AmazonTitlesRepository;
import org.CHR1SSW.tables.Amazon.AmazonTitles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmazonTitlesService
{
    @Autowired
    AmazonTitlesRepository amazonTitleRepository;

    public AmazonTitles createAmazonTitle(AmazonTitles amazonTitle)
    {
        return amazonTitleRepository.save(amazonTitle);
    }

    public AmazonTitles getAmazonTitle(int id)
    {
        return amazonTitleRepository.findById(id);
    }

    public AmazonTitles getAmazonTitleByNameOfTheShow(String title)
    {
        return amazonTitleRepository.findByNameOfTheShow(title);
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
