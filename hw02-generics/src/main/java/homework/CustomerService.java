package homework;

import java.util.*;

public class CustomerService {
    public Map<Customer, String> customerDataMap = new LinkedHashMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        if (customerDataMap.isEmpty()) {
            return null;
        } else if (customerDataMap.size() == 1) {
            return customerDataMap.entrySet().iterator().next();
        }

        final Map.Entry<Customer, String> result = customerDataMap.entrySet().stream()
                .min(Comparator.comparingLong(e -> e.getKey().getScores())).orElseThrow();

        return new AbstractMap.SimpleEntry<>(new Customer(result.getKey().getId(), result.getKey().getName(), result.getKey().getScores()), result.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        if (customerDataMap.isEmpty() ||
                customerDataMap.entrySet()
                        .stream()
                        .noneMatch(entry -> entry.getKey().getScores() > customer.getScores())) {
            return null;
        }

        final Map.Entry<Customer, String> result = customerDataMap.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getScores() > customer.getScores()).
                min(Comparator.comparingLong(e -> e.getKey().getScores())).orElseThrow();

        return new AbstractMap.SimpleEntry<>(new Customer(result.getKey().getId(), result.getKey().getName(), result.getKey().getScores()), result.getValue());
    }

    public void add(Customer customer, String data) {
        customerDataMap.put(customer, data);
    }
}