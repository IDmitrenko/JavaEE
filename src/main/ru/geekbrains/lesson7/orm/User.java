package ru.geekbrains.lesson7.orm;

@Table(tableName = "USERS1")
public class User {

    @PrimaryKey
    @Field(name = "id")
    private int id;

    @Unique
    @Index
    @NotNull
    @Field(name = "login", length = 25)
    private String login;

    @NotNull
    @Field(name = "password", length = 25)
    private String password;

    @Field(name = "address", length = 255)
    private String address;

    public User(int id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }


    public User(int id, String login, String password, String address) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }
}