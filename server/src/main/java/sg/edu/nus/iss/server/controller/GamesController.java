package sg.edu.nus.iss.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import sg.edu.nus.iss.server.models.GameSummary;
import sg.edu.nus.iss.server.service.GamesService;

@Controller
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class GamesController {
    
    @Autowired
    private GamesService gamesService;

    @GetMapping(path="/games")
    @ResponseBody
    public ResponseEntity<String> getGames(@RequestParam(defaultValue = "10") int limit, @RequestParam(defaultValue = "0") int offset) {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        List<GameSummary> games = gamesService.getGames(limit, offset);
        games.stream()
            .map(game -> Json.createObjectBuilder().add("gid", game.gid()).add("name", game.name()).build())
            .forEach(json -> jsonArrayBuilder.add(json));
        return ResponseEntity.ok(jsonArrayBuilder.build().toString());
    }
}
