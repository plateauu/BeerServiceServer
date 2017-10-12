package tech.plateauu.angulardemo.beer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
class BeerController {

    private static Logger log = LoggerFactory.getLogger("BeerController");

    private final BeerRepository repository;

    BeerController(BeerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/good-beers")
    @CrossOrigin("http://localhost:4200")
    Collection<Map<String, String>> beers() {
       return repository.findAll().stream()
//                .filter(this::isGreat)
                .map(beer -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("id", String.valueOf(beer.getId()));
                    map.put("name", beer.getName());
                    return map;
                }).collect(Collectors.toSet());
    }


    @PostMapping("/new")
    @CrossOrigin("http://localhost:4200")
    void next(@RequestBody String body) {
        log.info("hello: " + body);
    }

    private boolean isGreat(Beer beer) {
        return !beer.getName().equals("Tyskie")
                && !beer.getName().equals("Budwaiser")
                && !beer.getName().equals("Lech");
    }
}
