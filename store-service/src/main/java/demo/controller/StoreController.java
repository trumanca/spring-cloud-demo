package demo.controller;

import demo.domain.Fruit;
import demo.domain.OnSaleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StoreController {

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/on-sale-items", method = RequestMethod.GET)
    public List<OnSaleItem> getOnSaleItems() {
        String uri = getFruitProviderServiceInstance().getUri().toString();
        String url = String.format("%s/fruit-list/{groupId}", uri);
        RestTemplate restTemplate = new RestTemplate();
        Fruit[] fruits = restTemplate.getForObject(url, Fruit[].class,"GROUP-1");
        List<OnSaleItem> list = new ArrayList<>();
        if (fruits != null) {
            for(Fruit f : fruits) {
                OnSaleItem item = new OnSaleItem(f.getName(), 10000.00d, 0.02d);
                list.add(item);
            }
        }
        return list;
    }

    private ServiceInstance getFruitProviderServiceInstance() {
        ServiceInstance instance =
                client.getInstances("fruit-provider-service")
                        .stream()
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("fruit-provider-service not found"));
        return instance;
    }

}