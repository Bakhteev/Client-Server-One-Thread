package comparators;

import models.Person;

import java.util.Comparator;
import java.util.Locale;

public class PersonNameComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName()
                .toLowerCase(Locale.ROOT)
                .compareTo(p2.getName()
                        .toLowerCase(Locale.ROOT)) != 0 ?
                p1.getName()
                        .toLowerCase(Locale.ROOT)
                        .compareTo(p2.getName()
                                .toLowerCase(Locale.ROOT)) :
                p1.getHeight()
                        .compareTo(p2.getHeight()) != 0 ?
                        p1.getHeight()
                                .compareTo(p2.getHeight()) :
                        p1.getWeight()
                                .compareTo(p2.getWeight());
    }

}
