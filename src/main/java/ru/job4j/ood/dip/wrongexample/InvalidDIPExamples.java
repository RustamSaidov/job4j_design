package ru.job4j.ood.dip.wrongexample;

import java.util.ArrayList;

public class InvalidDIPExamples {
    /*Пример 1. У нас реализовано поле для хранения информации о книгах. В итоге класс зависит именно от этой формы хранения. */
    public class CustomLibrary {
        private ArrayList<Book> libraryStorage = new ArrayList<>();
    }

    public class Book {
        int bookId;
        String bookName;
    }

    /*Пример 2. У нас класс Plane зависит от класса Jets, откуда берет метод для полета fly.
    Таким образом мы ограничиваем наш самолет реализацией метода для полета только реактивного самолета.  */

    public class Plane {
        String planeType;
        Jets jets;

        public Plane(String planeType, Jets jets) {
            this.planeType = planeType;
            this.jets = jets;
        }

        public void fly() {
            jets.fly();
        }
    }

    public class Jets {

        public void fly() {
            /*something happens here*/
        }
    }

    /*Пример 3. У нас есть класс MilitaryMenChecker проверяющий годность военнослужащих по определенным критериям.
    В метод сравнения checkForReadiness при этом поступает класс реализация Soldier, чем окраничивается его применение
    например на офицеров.*/

    public class MilitaryMenChecker {

        public Soldier checkForReadiness(Soldier soldier) {

            /*something happens here*/
            return soldier;
        }
    }

    public interface MilitaryMen {

    }

    public class Soldier implements MilitaryMen {

    }

    public class Officer implements MilitaryMen {

    }

}
