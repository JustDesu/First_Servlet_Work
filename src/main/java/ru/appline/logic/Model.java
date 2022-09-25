package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instance = new Model();
    private final Map<Integer, User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();
        model.put(1, new User("Abobus", "Pfrney", 1234));
        model.put(2, new User("Elena", "Avil", 3345));
        model.put(3, new User("Maksim", "Kafka", 4636));

    }

    public void add(User user, int id) {
        model.put(id, user);
    }
    public void remove(User user, int id) { model.remove(id); }
    public void update(User user, int id, User newUser) { model.replace(id, user, newUser);}
    public Map<Integer, User> getFromList() {
        return model;
    }
}
