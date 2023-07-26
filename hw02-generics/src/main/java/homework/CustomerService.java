package homework;

import java.util.*;

public class CustomerService {
    public TreeMap<Customer, String> customerDataMap = new TreeMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> result = customerDataMap.firstEntry();
        return new AbstractMap.SimpleEntry<>(new Customer(result.getKey().getId(), result.getKey().getName(), result.getKey().getScores()), result.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        for (Map.Entry<Customer, String> entry : customerDataMap.entrySet()) {
            if (entry.getKey().getScores() > customer.getScores()) {
                return new AbstractMap.SimpleEntry<>(new Customer(entry.getKey().getId(), entry.getKey().getName(), entry.getKey().getScores()), entry.getValue());
            }
        }

        return null;
    }

    public void add(Customer customer, String data) {
        customerDataMap.put(customer, data);
    }
}