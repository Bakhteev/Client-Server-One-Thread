package maker;

import dto.PersonDto;
import managers.ClientCommandManager;
import models.*;
import validators.PersonValidator;
import workers.ConsoleWorker;

import java.io.Console;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class PersonMaker {

    private Scanner userScanner = null;
    private final PersonValidator validator = new PersonValidator();
    private Console console = null;
    boolean fileMode = ClientCommandManager.fileMode;

    public PersonMaker(Scanner userScanner) {
        this.userScanner = userScanner;
    }

    public PersonMaker(Console console) {
        this.console = console;
//        System.out.println(console.toString());
    }

    private void printArgumentSymbol() {
        System.out.print("> ");
    }


    private void detectArgument(String argument) {
        if (!argument.startsWith(">")) {
            ConsoleWorker.printParam("Wrong argument");
            System.exit(0);
        }
    }

    private String convertStringAsArgument(String argument) {
        return argument.replace("> ", "");
    }

    private String askPersonName() {
        System.out.println("Enter person's name");
        String name;
        if (fileMode) {
            name = userScanner.nextLine().trim();
            detectArgument(name);
            name = convertStringAsArgument(name);
            validator.validateName(name);
            ConsoleWorker.printParam(name);
//            System.out.println("> " + name);
            return name;
        }
        while (true) {
            try {
                printArgumentSymbol();
                name = console.readLine().trim();
                validator.validateName(name);
                return name;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    private Integer askCoordinatesX() {
        String strX = "";
        System.out.println("Enter X coordinate");
        if (fileMode) {
            strX = userScanner.nextLine().trim();
            detectArgument(strX);
            strX = convertStringAsArgument(strX);
            validator.validateCoordinatesX(strX);
            ConsoleWorker.printParam(strX);
//            System.out.println("> " + strX);
            return Integer.parseInt(strX);
        }
        while (true) {
            try {
                printArgumentSymbol();
                strX = console.readLine().trim();
                validator.validateCoordinatesX(strX);
                return Integer.parseInt(strX);
            } catch (NumberFormatException e) {
                System.out.println(strX + " not a number");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private int askCoordinatesY() {
        String strY = "";
        System.out.println("Enter Y coordinate");
        if (fileMode) {
            strY = userScanner.nextLine().trim();
            detectArgument(strY);

            strY = convertStringAsArgument(strY);
            validator.validateCoordinatesY(strY);
            ConsoleWorker.printParam(strY);
            return Integer.parseInt(strY);
        }
        while (true) {
            try {
                printArgumentSymbol();
                strY = console.readLine().trim();
                validator.validateCoordinatesY(strY);
                return Integer.parseInt(strY);
            } catch (NumberFormatException e) {
                System.out.println(strY + " not a number");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Coordinates coordinates() {
        Integer x = askCoordinatesX();
        int y = askCoordinatesY();
        return new Coordinates(x, y);
    }

    private Long askHeight() {
        System.out.println("Enter person's height");
        String strHeight = "";
        if (fileMode) {
            strHeight = userScanner.nextLine().trim();
            detectArgument(strHeight);

            strHeight = convertStringAsArgument(strHeight);
            validator.validateHeight(strHeight);
            ConsoleWorker.printParam(strHeight);
            return Long.parseLong(strHeight);
        }
        while (true) {
            try {
                printArgumentSymbol();
                strHeight = console.readLine().trim();
                validator.validateHeight(strHeight);
                return Long.parseLong(strHeight);
            } catch (NumberFormatException e) {
                System.out.println(strHeight + " not a number");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private float askWeight() {
        System.out.println("Enter person's weight");
        String strWeight = "";
        if (fileMode) {
            strWeight = userScanner.nextLine().trim();
            detectArgument(strWeight);
            strWeight = convertStringAsArgument(strWeight);
            validator.validateWeight(strWeight);
            ConsoleWorker.printParam(strWeight);
            return Float.parseFloat(strWeight);
        }
        while (true) {
            try {
                printArgumentSymbol();
                strWeight = console.readLine().trim();
                validator.validateWeight(strWeight);
                return Float.parseFloat(strWeight);
            } catch (NumberFormatException e) {
                System.out.println(strWeight + " not a number");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private EyesColor askEyesColor() {
        String strColor;
        System.out.println("Choose eyes' color.");
        if (fileMode) {
            strColor = userScanner.nextLine().trim().toUpperCase();
            detectArgument(strColor);
            strColor = convertStringAsArgument(strColor);
            ConsoleWorker.printParam(strColor);
            return EyesColor.valueOf(strColor);

        }
        while (true) {
            try {
                EyesColor.showColorsList();
                printArgumentSymbol();
                strColor = console.readLine().trim().toUpperCase();
                return EyesColor.valueOf(strColor);
            } catch (IllegalArgumentException e) {
                System.out.println("Eyes color wasn't found.");
            }
        }
    }

    private HairsColor askHairColor() {
        System.out.println("Choose hairs' color.");
        String strColor;
        if (fileMode) {
            strColor = userScanner.nextLine().trim().toUpperCase();
            detectArgument(strColor);
            strColor = convertStringAsArgument(strColor);
            ConsoleWorker.printParam(strColor);
            return HairsColor.valueOf(strColor);

        }
        while (true) {
            try {
                HairsColor.showColorsList();
                printArgumentSymbol();
                strColor = console.readLine().trim().toUpperCase();
                return HairsColor.valueOf(strColor);
            } catch (IllegalArgumentException e) {
                System.out.println("Hairs' color wasn't found.");
            }
        }
    }

    private Long askLocationX() {
        System.out.println("Enter Location X");
        String strX = "";
        if (fileMode) {
            strX = userScanner.nextLine().trim();
            detectArgument(strX);
            strX = convertStringAsArgument(strX);
            validator.validateLocationX(strX);
            ConsoleWorker.printParam(strX);
            return Long.parseLong(strX);

        }
        while (true) {
            try {
                printArgumentSymbol();
                strX = console.readLine().trim();
                validator.validateLocationX(strX);
                return Long.parseLong(strX);
            } catch (NumberFormatException e) {
                System.out.println(strX + " not a number");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Integer askLocationY() {
        System.out.println("Enter Location Y");
        String strY = "";
        if (fileMode) {
            strY = userScanner.nextLine().trim();
            detectArgument(strY);
            strY = convertStringAsArgument(strY);
            if (strY.isEmpty()) {
                ConsoleWorker.printParam(strY);
                return null;
            }
            ConsoleWorker.printParam(strY);
            return Integer.parseInt(strY);

        }
        while (true) {
            try {
                printArgumentSymbol();
                strY = console.readLine().trim();
                if (strY.isEmpty()) {
                    return null;
                }
                return Integer.parseInt(strY);
            } catch (NumberFormatException e) {
                System.out.println(strY + " not a number");
            }
        }
    }

    private Float askLocationZ() {
        System.out.println("Enter Location Z");
        String strZ = "";
        if (fileMode) {
            strZ = userScanner.nextLine().trim();
            detectArgument(strZ);
            strZ = convertStringAsArgument(strZ);
            validator.validateLocationZ(strZ);
            ConsoleWorker.printParam(strZ);
            return Float.parseFloat(strZ);
        }
        while (true) {
            try {
                printArgumentSymbol();
                strZ = console.readLine().trim();
                validator.validateLocationZ(strZ);
                return Float.parseFloat(strZ);
            } catch (NumberFormatException e) {
                System.out.println(strZ + " not a number");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String askLocationName() {
        System.out.println("Enter Location's name");
        String name;
        if (fileMode) {
            name = userScanner.nextLine().trim();
            detectArgument(name);
            name = convertStringAsArgument(name);
            validator.validateName(name);
            ConsoleWorker.printParam(name);
            return name;

        }
        while (true) {
            printArgumentSymbol();
            name = console.readLine().trim();
            try {
                validator.validateName(name);
                return name;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Location location() {
        Long x = askLocationX();
        int y = askLocationY();
        Float z = askLocationZ();
        String name = askLocationName();
        return new Location(x, y, z, name);
    }

    public Person startMaker() {
        String personName = askPersonName();
        Coordinates coordinates = this.coordinates();
        Long height = askHeight();
        float weight = askWeight();
        EyesColor eyesColor = askEyesColor();
        HairsColor hairsColor = askHairColor();
        Location location = this.location();
        return new Person(personName, coordinates, height, weight, eyesColor, hairsColor, location);
    }

    public PersonDto makeDto() {
        String personName = askPersonName();
        Coordinates coordinates = this.coordinates();
        Long height = askHeight();
        float weight = askWeight();
        EyesColor eyesColor = askEyesColor();
        HairsColor hairsColor = askHairColor();
        Location location = this.location();
        return new PersonDto(personName, coordinates, height, weight, eyesColor, hairsColor, location);
    }

    private String[] askFields() {
        while (true) {
            Fields.showFieldList();
            ConsoleWorker.println("Choose param's names:");
            ConsoleWorker.printSymbol(false);
            return console.readLine().trim().split("(,\\s)|(,)");
        }
    }

    enum Fields {
        NAME,
        COORDINATES,
        HEIGHT,
        WEIGHT,
        HAIRSCOLOR,
        LOCATION;

        static public void showFieldList() {
            for (int i = 0; i < values().length - 1; i++) {
                System.out.println(values()[i].toString().toLowerCase(Locale.ROOT));
            }
        }
    }

    public PersonDto update() {
        String[] params = askFields();
        PersonDto dto = new PersonDto();
        Arrays.stream(params).forEach(param -> setPersonDtoByFields(dto, param));
        return dto;
    }

    public void setPersonDtoByFields(PersonDto dto, String param) {

        try {
            if (Fields.valueOf(param.toUpperCase(Locale.ROOT)).equals(Fields.NAME)) {
//                String name = askPersonName();
                dto.setName(askPersonName());
            }
            if (Fields.valueOf(param.toUpperCase(Locale.ROOT)).equals(Fields.COORDINATES)) {
                dto.setCoordinates(coordinates());
            }
            if (Fields.valueOf(param.toUpperCase(Locale.ROOT)).equals(Fields.HEIGHT)) {
                dto.setHeight(askHeight());
            }
            if (Fields.valueOf(param.toUpperCase(Locale.ROOT)).equals(Fields.WEIGHT)) {
                dto.setWeight(askWeight());
            }
            if (Fields.valueOf(param.toUpperCase(Locale.ROOT)).equals(Fields.HAIRSCOLOR)) {
                dto.setHairsColor(askHairColor());
            }
            if (Fields.valueOf(param.toUpperCase(Locale.ROOT)).equals(Fields.LOCATION)) {
                dto.setLocation(location());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong field, please try one more time");
        }
    }
}


