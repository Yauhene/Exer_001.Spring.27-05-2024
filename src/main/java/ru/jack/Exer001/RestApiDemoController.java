package ru.jack.Exer001;

import org.springframework.web.bind.annotation.*;
import ru.jack.Exer001.Coffee.*;

import java.util.*;

@RestController
@RequestMapping
public class RestApiDemoController {
    private List<Coffee> coffees = new ArrayList<>();

    public RestApiDemoController() {
        coffees.addAll(List.of(
            new Coffee("Cafe Cereza"),
            new Coffee("Cafe Ganador"),
            new Coffee("Cafe Lareno"),
            new Coffee("Cafe Tres Pontas")
        ));
    }

//    @RequestMapping(value = "/coffies", method = RequestMethod.GET)
    @GetMapping("/coffees") // ровно то же, что и @RequestMapping(value = "/coffies", method = RequestMethod.GET)
    Iterable<Coffee> getCoffies() {
        return coffees;
    }

    @GetMapping("/coffees/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id) {
        List<Coffee> coffeesD = List.copyOf(coffees);
        for (Coffee c: coffeesD) {
            if (c.getId().equals(id)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @PostMapping("/coffees")
    Coffee postCoffee(@RequestBody Coffee coffee) {
        coffees.add(coffee);
        return coffee;
    }

    @PutMapping("/coffees/{id}")
    Coffee putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
        int coffeeIndex = -1;
        for (Coffee c: coffees) {
            if (c.getId().equals(id)) {
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }
        return (coffeeIndex == -1) ? postCoffee(coffee) : coffee;
    }
}
