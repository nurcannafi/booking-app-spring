package az.edu.turing.model.dto;

import lombok.Getter;

@Getter
public class PassengerDto {

    private String firstName;
    private String lastName;
    private int age;

    public PassengerDto(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "PassengerDto{firstName='%s', lastName='%s', age=%d}".formatted(firstName, lastName, age);
    }

}
