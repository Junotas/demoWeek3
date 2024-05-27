package org.example.demoemployee;

public class User {
    private final String username;
    private final String email;
    private final String personalId;
    private final String age;
    private final String gender;

    private User(Builder builder) {
        this.username = builder.username;
        this.email = builder.email;
        this.personalId = builder.id;
        this.age = builder.age;
        this.gender = builder.gender;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPersonalId() {
        return personalId;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }



    public static class Builder {
        private String username;
        private String email;
        private String id;
        private String age;
        private String gender;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder age(String age) {
            this.age = age;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
