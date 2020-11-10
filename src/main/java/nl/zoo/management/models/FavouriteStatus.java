package nl.zoo.management.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FavouriteStatus {
    HAPPY("happy"),
    UNHAPPY("unhappy");
    private final String status;

}
