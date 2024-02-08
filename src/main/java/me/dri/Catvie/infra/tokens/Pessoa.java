package me.dri.Catvie.infra.tokens;

public class Pessoa {


    private String username;
    private String password;

    public Pessoa(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static class Builder {
        private String username;
        private String password;

        public Builder() {

        }

        public Builder withUsername(String username) {
                this.username = username;
                return this; //
            }
            public Builder withPassword(String password) {
                this.password = password;
                return this; //
            }

            public Pessoa build() {
                return new Pessoa(username, password);
            }
        }

    @Override
    public String toString() {
        return "Pessoa{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
