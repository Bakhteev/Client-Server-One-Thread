package comparators;

import models.Person;
import java.util.Comparator;
import java.util.Locale;

public class PersonDescendingOrderComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p2.getName()
                .toLowerCase(Locale.ROOT)
                .compareTo(p1.getName()
                        .toLowerCase(Locale.ROOT)) != 0 ?
                p2.getName()
                        .toLowerCase(Locale.ROOT)
                        .compareTo(p1.getName()
                                .toLowerCase(Locale.ROOT)) :
                p2.getHeight()
                        .compareTo(p1.getHeight()) != 0 ?
                        p2.getHeight()
                                .compareTo(p1.getHeight()) :
                        p2.getWeight()
                                .compareTo(p1.getWeight());
    }
}
