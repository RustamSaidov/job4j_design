package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CinemaObjectMain {
    public static void main(String[] args) {

        /* Пример создания JSONObject из json-строки строки */
        //JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");
        JSONObject jsonNameOfCinema = new JSONObject("{\"nameOfCinema\":\"Terminator\"}");

        /* JSONArray из ArrayList */
        List<Integer> list = new ArrayList<>();
        list.add(500000);
        list.add(400000);
        list.add(300000);
        JSONArray jsonMontlyIncome = new JSONArray(list);

        /* JSONObject напрямую методом put */
        //final Person person = new Person(false, 30,new Contact("11-111"),new String[]{"Worker", "Married"});
        final CinemaObject cinemaObject = new CinemaObject(true, (short) 1984, "Terminator",
                new CinemaCreatorInfo("James", "Cameron33", "Gale", "Anne Hurd"), new int[]{500000, 400000, 300000});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isRealised", cinemaObject.isRealised());
        jsonObject.put("yearOfRealise", cinemaObject.getYearOfRealise());
        /*Демонстрация того, что можно пихнуть строку напрямую, а не из обьекта взять:*/
        jsonObject.put("nameOfCinema", jsonNameOfCinema);
        jsonObject.put("cinemaCreatorInfo", cinemaObject.getCinemaCreatorInfo());
        jsonObject.put("montlyIncome", jsonMontlyIncome);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(cinemaObject).toString());

        /*Простое преобразование в JSON объект и обратно:
        final CinemaObject cinemaObject = new CinemaObject(true, (short) 1984, "Terminator",
                new CinemaCreatorInfo("James", "Cameron", "Gale", "Anne Hurd"), new int[]{500000, 400000, 300000});

        Преобразуем объект cinemaObject в json-строку.
        final Gson gson = new GsonBuilder().create();
        String stringFromGson = gson.toJson(cinemaObject);
        System.out.println("GSONCHIK:" + stringFromGson);

        Создаем объект CinemaObjectForXMLTask из json-строки:
        final CinemaObject cinemaObjectFromJson = gson.fromJson(stringFromGson, CinemaObject.class);

        System.out.println("JAVA OBJ Fron JSON:" + cinemaObjectFromJson);
        */
    }
}
