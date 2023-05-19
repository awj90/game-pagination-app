package sg.edu.nus.iss.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.server.models.GameSummary;
import sg.edu.nus.iss.server.repository.GamesRepository;

@Service
public class GamesService {
    
    @Autowired
    private GamesRepository gamesRepository;

    public List<GameSummary> getGames(int limit, int offset) {
        return gamesRepository.getGames(limit, offset);
    }
}
