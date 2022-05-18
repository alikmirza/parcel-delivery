package com.mscourier.dto.request;

public class SignUpRequestDto {

    private final String username;
    private final String password;
    private final String name;
    private final String surname;
    private final String role;

    public SignUpRequestDto(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.name = builder.name;
        this.surname = builder.surname;
        this.role = builder.role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String password;
        private String name;
        private String surname;
        private String role;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setRole(String role) {
            this.role = role;
            return this;
        }

        public SignUpRequestDto build() {
            return new SignUpRequestDto(this);
        }

        public static Builder of(CreateCourierRequest request) {
            return builder()
                    .setName(request.getName())
                    .setPassword(request.getPassword())
                    .setUsername(request.getUsername())
                    .setSurname(request.getSurname());
        }
    }
}
