package nl.zoo.management.models;

public enum AnimalType {
    CAT("Cat"),
    DOG("Dog");

    AnimalType(String animalType) {
        this.type = animalType;
    }
    private String type;
}
