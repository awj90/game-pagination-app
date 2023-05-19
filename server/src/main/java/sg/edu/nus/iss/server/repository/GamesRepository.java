package sg.edu.nus.iss.server.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.server.models.GameSummary;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Repository
public class GamesRepository {
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<GameSummary> getGames(int limit, int offset) {
        Query query = new Query(new Criteria())
                            .with(Sort.by(Direction.ASC, "gid"))
                            .skip(offset)
                            .limit(limit);
        query.fields().exclude("_id").include("gid", "name");

        return mongoTemplate.find(query, Document.class, "game")
                    .stream()
                    .map(doc -> new GameSummary(doc.getInteger("gid"), doc.getString("name")))
                    .collect(Collectors.toList());
    }
}
