package tech.plateauu.angulardemo.beer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Component
class BeerCommandLineRunner implements CommandLineRunner {

    private final BeerRepository repository;

    BeerCommandLineRunner(BeerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        List<String> bears = Arrays.asList(
                "Kentucky Brunch Brand Stout",
                "Good Morning",
                "Heady Topper",
                "Hunahpu's Imperial Stout - Double Barrel Aged",
                "Barrel-Aged Abraxas",
                "King Julius",
                "Pliny The Younger",
                "Mornin' Delight",
                "Bourbon County Brand Coffee Stout",
                "Tyskie",
                "Budwaiser",
                "Lech");

        Stream.of(bears)
                .flatMap(Collection::stream)
                .forEach(name -> repository.save(new Beer(name)));

        repository.findAll().forEach(System.out::println);
    }
}
