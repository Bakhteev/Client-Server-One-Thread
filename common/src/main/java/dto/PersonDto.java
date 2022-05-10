package dto;


import lombok.Getter;
import lombok.Setter;
import models.Coordinates;
import models.EyesColor;
import models.HairsColor;
import models.Location;

@Setter
@Getter
public class PersonDto {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Long height; //Поле не может быть null, Значение поля должно быть больше 0
    private float weight; //Значение поля должно быть больше 0
    private final EyesColor eyesColor; //Поле не может быть null
    private HairsColor hairsColor; //Поле не может быть null
    private Location location; //Поле не может быть null

    public PersonDto(String name, Coordinates coordinates, Long height, float weight, EyesColor eyesColor,
                  HairsColor hairsColor, Location location) throws IllegalArgumentException {
        this.name = name;
        this.coordinates = coordinates;
        this.height = height;
        this.weight = weight;
        this.eyesColor = eyesColor;
        this.hairsColor = hairsColor;
        this.location = location;
    }
}
