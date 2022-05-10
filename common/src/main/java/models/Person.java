package models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Person implements Serializable {
    private final Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно
    // быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long height; //Поле не может быть null, Значение поля должно быть больше 0
    private float weight; //Значение поля должно быть больше 0
    private final EyesColor eyesColor; //Поле не может быть null
    private HairsColor hairsColor; //Поле не может быть null
    private Location location; //Поле не может быть null

    public Person(String name, Coordinates coordinates, Long height, float weight, EyesColor eyesColor,
                  HairsColor hairsColor, Location location) throws IllegalArgumentException {
        this.id = this.uniqueId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.height = height;
        this.weight = weight;
        this.eyesColor = eyesColor;
        this.hairsColor = hairsColor;
        this.location = location;
    }

    private int uniqueId() {
        UUID uniqueKey = UUID.randomUUID();
        return Math.abs(uniqueKey.hashCode());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getHeight() {
        return height;
    }

    public Location getLocation() {return location;}

    public Float getWeight() {return weight;}

    public HairsColor getColor() {return this.hairsColor;}

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public EyesColor getEyesColor() {
        return eyesColor;
    }

    public HairsColor getHairsColor() {
        return hairsColor;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHairsColor(HairsColor hairsColor) {
        this.hairsColor = hairsColor;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "{\n" +
                "   id: " + id + ";\n" +
                "   name: '" + name + '\'' + ";\n" +
                "   coordinates: " + coordinates.toString() + ";\n" +
                "   creationDate: " + creationDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + ";\n" +
                "   height: " + height + ";\n" +
                "   weight: " + weight + ";\n" +
                "   eyeColor: " + eyesColor.toString() + ";\n" +
                "   hairColor: " + hairsColor.toString() + ";\n" +
                "   location: " + location.toString() + ";\n" +
                "}";
    }
}
