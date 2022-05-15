package managers;

import comparators.PersonNameComparator;
import dto.PersonDto;
import models.Person;
import validators.PersonValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//TODO: ADD LOGGER
public class LinkedListCollectionManager {
    private final LinkedList<Person> collection = new LinkedList<>();
    private LocalDateTime initializationTime;
    private LocalDateTime lastSaveTime;
    private final String type = this.collection.getClass().getSimpleName() + " of " + Person.class.getSimpleName();

    public void add(Person person) {
        if (checkUniqueId(person)) {
            throw new SecurityException("Person id must be unique");
        }
        this.collection.add(person);
        this.collection.sort(new PersonNameComparator());
    }

    public void addAll(Collection<Person> collection) {
        if (size() == 0) {
            this.collection.addAll(collection);
            Set<Integer> setOfId = new HashSet<>();
            this.collection.forEach(person -> setOfId.add(person.getId()));
            for (Integer id : setOfId) {
                try {
                    long counter = this.collection.stream().filter(person -> id.equals(person.getId())).count();
//                    for (Person element : this.collection) {
//                        if (id.equals(element.getId()))
//                            counter++;
//                    }
                    if (counter > 1) {
                        throw new SecurityException("Person id must be unique, objects with this id - " + id + " will be removed.\nNumber of " +
                                "objects: " + counter);
                    }
                } catch (SecurityException e) {
                    deleteById(id);
                    System.out.println(e.getMessage());
                }
            }
        } else {
            for (Iterator<Person> iterator = collection.iterator(); iterator.hasNext(); ) {
                try {
                    Person p = iterator.next();
                    if (checkUniqueId(p)) {
                        throw new SecurityException("Person id must be unique, this object will not be added to collection.\n" + p.toString());
                    }
                } catch (SecurityException e) {
                    System.out.println(e.getMessage());
                    iterator.remove();
                }
            }
        }
    }

    public void loadCollection(Person[] collection) {
        try {
            Arrays.stream(collection).forEach(PersonValidator::checkFields);
//            for (Person person : collection) {
//                PersonValidator.checkFields(person);
//            }
            addAll(Arrays.asList(collection));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        initializationTime = LocalDateTime.now();
    }

    public void update(Person person,PersonDto dto){
        if(dto.getName() != null) person.setName(dto.getName());
        if(dto.getCoordinates() != null) person.setCoordinates(dto.getCoordinates());
        if(dto.getHeight() != null) person.setHeight(dto.getHeight());
        if(dto.getWeight() != 0) person.setWeight(dto.getWeight());
        if(dto.getHairsColor() != null) person.setHairsColor(dto.getHairsColor());
        if(dto.getLocation() != null) person.setLocation(dto.getLocation());
    };

    public int size() {
        return collection.size();
    }

    public LinkedList<Person> getCollection() {
        return collection;
    }

    public String getInitializationTime() {
        return initializationTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public String getLastSaveTime() {
        return lastSaveTime == null ? null : lastSaveTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public Person getElementById(Integer id) {
        Person person = null;
        for (Person element : collection) {
            if (element.getId().equals(id)) {
                person = element;
            }
        }
        return person;
    }

    public void setLastSaveTime() {
        lastSaveTime = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    private boolean checkUniqueId(Person person) {
        for (Person item : collection) {
            if (item.getId().equals(person.getId()))
                return true;
        }
        return false;
    }

    public void clearCollection() {
        collection.clear();
    }

    public void deleteById(int id) {
        collection.removeIf(element -> element.getId().equals(id));
        collection.sort(new PersonNameComparator());
    }

    public void deleteObject(Person person) {
        collection.remove(person);
        collection.sort(new PersonNameComparator());
    }

    public Person getFirstElement() {
        return collection.getFirst();
    }

    public void removeFirstElement() {
        collection.remove();
    }

}
