package org.CHR1SSW.services.DisneyPlus;

import org.CHR1SSW.repositories.DisneyPlus.DisneyPlusTitlesRepository;
import org.CHR1SSW.tables.DisneyPlus.DisneyPlusTitles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisneyPlusTitlesService {
    @Autowired
    DisneyPlusTitlesRepository disneyPlusTitlesRepository;

    public void createDisneyPlusTitle(DisneyPlusTitles disneyPlusTitles) {
        disneyPlusTitlesRepository.save(disneyPlusTitles);
    }

    public DisneyPlusTitles getDisneyPlusTitle(String imdbId) {
        return disneyPlusTitlesRepository.findByImdbId(imdbId);
    }

    public List<DisneyPlusTitles> getDisneyPlusTitles() {
        return disneyPlusTitlesRepository.findAll();
    }

    public void updateDisneyPlusTitles(DisneyPlusTitles disneyPlusTitles)
    {
        disneyPlusTitlesRepository.save(disneyPlusTitles);
    }

    public void deleteDisneyPlusTitle(String imdbId)
    {
        disneyPlusTitlesRepository.delete(imdbId);
    }

    public List<DisneyPlusTitles> findAllByTitle(String title)
    {
        return disneyPlusTitlesRepository.findAllByTitle(title);
    }
}
