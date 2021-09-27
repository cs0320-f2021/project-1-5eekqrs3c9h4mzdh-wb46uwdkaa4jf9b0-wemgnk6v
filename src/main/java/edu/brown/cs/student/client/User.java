package edu.brown.cs.student.client;

public class User {

    String user_id;
    String weight;
    String bust_size;
    String height;
    String age;
    String body_type;
    String horoscope;

    public User(String user_id, String weight, String bust_size, String height, String age, String body_type, String horoscope) {
        this.user_id = user_id;
        this.weight = weight;
        this.bust_size = bust_size;
        this.height = height;
        this.age = age;
        this.body_type = body_type;
        this.horoscope = horoscope;
    }

    @Override
    public String toString() {
        return "[" + "user_id: " + user_id + ", weight: " + weight + " lbs, bust_size: " + bust_size
                + ", height: " + height + " in., age: " + age + ", body_type: " + body_type
                + ", horoscope: " + horoscope + "]";
    }
}
