package demo.controller;

import demo.domain.Fruit;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FruitProviderController {

    private static Map<String, List<Fruit>> fruitGroup = new HashMap<>();

    static {

        List<Fruit> fruits = new ArrayList<>();
        Fruit e = new Fruit("Kiwi");
        fruits.add(e);
        e = new Fruit("Banana");
        fruits.add(e);
        fruitGroup.put("GROUP-1", fruits);

        fruits = new ArrayList<>();
        e = new Fruit("Lemon");
        fruits.add(e);
        fruitGroup.put("GROUP-2", fruits);

    }

    @RequestMapping(value = "/fruit-list/{groupId}", method = RequestMethod.GET)
    public List<Fruit> getEmployees(@PathVariable String groupId) {
        List<Fruit> list = fruitGroup.get(groupId);
        if (list == null) {
            list = new ArrayList<Fruit>();
        }
        return list;
    }

}