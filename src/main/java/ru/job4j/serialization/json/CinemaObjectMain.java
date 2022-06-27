package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CinemaObjectMain {
    public static void main(String[] args) {

        final CinemaObject cinemaObject = new CinemaObject(true, (short) 1984, "Terminator",
                new CinemaCreatorInfo("James", "Cameron", "Gale", "Anne Hurd"), new int[]{500000, 400000, 300000});

        /* Преобразуем объект cinemaObject в json-строку. */
        final Gson gson = new GsonBuilder().create();
        String stringFromGson = gson.toJson(cinemaObject);
        System.out.println("GSONCHIK:" + stringFromGson);

        /* Создаем объект CinemaObjectForXMLTask из json-строки: */
        final CinemaObject cinemaObjectFromJson = gson.fromJson(stringFromGson, CinemaObject.class);

        System.out.println("JAVA OBJ Fron JSON:" + cinemaObjectFromJson);
    }
}
