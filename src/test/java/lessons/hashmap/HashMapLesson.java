package lessons.hashmap;

import kotlinx.serialization.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HashMapLesson {

    private Map<String, MyAccount> map;

    public HashMapLesson() {
        map = new HashMap<String, MyAccount>();
    }

    protected void execute() {
        map.put("one", new MyAccount("john", "smith"));
        map.put("one", new MyAccount("john", "lennon"));
        map.get("one");
        System.out.println(map.toString());
    }

    public static void main(String[] args) {
        new HashMapLesson().execute();
    }

    @Serializable
    static class MyAccount {
        private String name;
        private String surname;

        public MyAccount(String name, String surname) {
            super();
            this.name = name;
            this.surname = surname;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MyAccount myAccount)) return false;
            return Objects.equals(name, myAccount.name) && Objects.equals(surname, myAccount.surname);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, surname);
        }
    }
}
