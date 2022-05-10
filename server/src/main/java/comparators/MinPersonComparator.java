package comparators;

import models.Person;

import java.util.Comparator;

public class MinPersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getWeight().compareTo(o2.getWeight()) + o1.getHeight().compareTo(o2.getHeight());
    }
}
