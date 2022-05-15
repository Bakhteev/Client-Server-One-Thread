package dto;


import lombok.Getter;
import lombok.Setter;
import models.Coordinates;
import models.EyesColor;
import models.HairsColor;
import models.Location;

import java.io.Serializable;

@Setter
@Getter
public class PersonDto implements Serializable {
    private String name = null; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates = null; //Поле не может быть null
    private Long height = null; //Поле не может быть null, Значение поля должно быть больше 0
    private float weight = 0; //Значение поля должно быть больше 0
    private EyesColor eyesColor = null; //Поле не может быть null
    private HairsColor hairsColor = null; //Поле не может быть null
    private Location location = null; //Поле не может быть null

    public PersonDto() {
    }

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
