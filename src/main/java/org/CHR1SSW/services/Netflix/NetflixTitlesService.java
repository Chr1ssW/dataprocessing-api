package org.CHR1SSW.services.Netflix;

import org.CHR1SSW.repositories.Netflix.NetflixTitlesRepository;
import org.CHR1SSW.tables.Netflix.NetflixTitles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetflixTitlesService
{
    @Autowired
    NetflixTitlesRepository netflixTitlesRepository;

    public void createNetflixTitle(NetflixTitles netflixTitles)
    {
        netflixTitlesRepository.save(netflixTitles);
    }

    public NetflixTitles getNetflixTitle(int showId)
    {
        return netflixTitlesRepository.findByShowId(showId);
    }

    public NetflixTitles getNetflixByTitle(String title)
    {
        return netflixTitlesRepository.findByTitle(title);
    }

    public List<NetflixTitles> getNetflixTitles()
    {
        return netflixTitlesRepository.findAll();
    }

    public void updateNetflixTitles(NetflixTitles netflixTitles)
    {
        netflixTitlesRepository.save(netflixTitles);
    }

    public void deleteNetflixTitle(int showId)
    {
        netflixTitlesRepository.delete(showId);
    }
}
