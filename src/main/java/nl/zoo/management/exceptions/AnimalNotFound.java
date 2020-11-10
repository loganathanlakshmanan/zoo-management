package nl.zoo.management.exceptions;

public class AnimalNotFound extends RuntimeException{
    public AnimalNotFound(String message){
        super(message);
    }
}
