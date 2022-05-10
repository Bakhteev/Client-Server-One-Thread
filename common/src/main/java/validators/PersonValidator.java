package validators;


import dto.PersonDto;
import models.Person;

public class PersonValidator {
    private final int MIN_VALUE = 0;

    public void validateName(String name) {
        if (name.isEmpty())
            throw new IllegalArgumentException("Name can't be empty");
    }

    public void validateCoordinatesX(String strX) {
        if (strX.isEmpty())
            throw new IllegalArgumentException("Coordinate x can't be empty");
    }

    public void validateCoordinatesY(String strY) {
        int MAX_Y = 988;

        if (strY.isEmpty())
            throw new IllegalArgumentException("Coordinate " +
                    "y " +
                    "can't be empty");
        if (Integer.parseInt(strY) > MAX_Y)
            throw new IllegalArgumentException("Max value of coordinate y: 988");
    }

    public void validateHeight(String height) {
        if (height == null)
            throw new IllegalArgumentException("Height can't be empty");
        if (Long.parseLong(height) <= MIN_VALUE)
            throw new IllegalArgumentException("Value of height must be more then 0");
    }

    public void validateWeight(String strWeight) {
        if (strWeight.isEmpty())
            throw new IllegalArgumentException("weight can't be empty");
        if (Float.parseFloat(strWeight) <= MIN_VALUE)
            throw new IllegalArgumentException("Value of weight must be more then 0");
    }

    public void validateLocationX(String strX) {
        if (strX.isEmpty())
            throw new IllegalArgumentException("Location x can't be empty");
    }

    public void validateLocationZ(String strZ) {
        if (strZ.isEmpty())
            throw new IllegalArgumentException("Location Z can't be empty");
    }
    public static void checkDtoFields(PersonDto personDto){
        StringBuilder sb = new StringBuilder();
        if (personDto.getName() == null) {
            sb.append("name can't be null").append("\n");
        } else {
            if (personDto.getName().isEmpty()) {
                sb.append("name can't be empty");
            }
        }
        if (personDto.getCoordinates() == null) {
            sb.append("Coordinates can't be null").append("\n");
        } else {
            if (personDto.getCoordinates().getX() == null) {
                sb.append("Coordinates X can't be null ").append("\n");
            }
            if (personDto.getCoordinates().getY() > 988) {
                sb.append("Coordinates Y can't be bigger then 988").append("\n");
            }
        }
        if (personDto.getHairsColor() == null) {
            sb.append("Hairs' color can't  be null").append("\n");
        }
        if (personDto.getLocation() == null) {
            sb.append("location can't be null").append("\n");
        } else {
            if (personDto.getLocation().getX() == null) {
                sb.append("Location X can't be null").append("\n");
            }
            if (personDto.getLocation().getName() == null) {
                sb.append("Location name can't be null").append("\n");
            }
        }
        if (!(sb.length() == 0)) {
            sb.insert(0, "Во входном файле есть некорректные данные. В следующий раз исправьте все виды ошибок: \n");
            throw new IllegalArgumentException(sb.toString());
        }
    }
    public static void checkFields(Person person) {
        StringBuilder sb = new StringBuilder();
        if (person.getId() <= 0) {
            sb.append("ID can't be less then 0").append("\n");
        }
        if (person.getId() == null)
            sb.append("ID can't be null").append("\n");
        if (person.getName() == null) {
            sb.append("name can't be null").append("\n");
        } else {
            if (person.getName().isEmpty()) {
                sb.append("name can't be empty");
            }
        }
        if (person.getCoordinates() == null) {
            sb.append("Coordinates can't be null").append("\n");
        } else {
            if (person.getCoordinates().getX() == null) {
                sb.append("Coordinates X can't be null ").append("\n");
            }
            if (person.getCoordinates().getY() > 988) {
                sb.append("Coordinates Y can't be bigger then 988").append("\n");
            }
        }
        if (person.getHairsColor() == null) {
            sb.append("Hairs' color can't  be null").append("\n");
        }
        if (person.getLocation() == null) {
            sb.append("location can't be null").append("\n");
        } else {
            if (person.getLocation().getX() == null) {
                sb.append("Location X can't be null").append("\n");
            }
            if (person.getLocation().getName() == null) {
                sb.append("Location name can't be null").append("\n");
            }
        }
        if (!(sb.length() == 0)) {
            sb.insert(0, "Во входном файле есть некорректные данные. В следующий раз исправьте все виды ошибок: \n");
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
