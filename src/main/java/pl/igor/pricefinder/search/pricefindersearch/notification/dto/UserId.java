package pl.igor.pricefinder.search.pricefindersearch.notification.dto;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class UserId {
    @NonNull
    UUID id;
}
