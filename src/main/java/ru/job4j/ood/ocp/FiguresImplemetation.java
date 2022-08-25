package ru.job4j.ood.ocp;

import java.util.List;
/*Верный подход. В данном случае надо расширять функционал, создавая объекты через интерфейс: */
public class FiguresImplemetation {

    private interface Drawable {
        String draw();
    }

    private static class Rectangle implements Drawable {
        @Override
        public String draw() {
            return "...";
        }
    }

    public static void main(String[] args) {
        List<Drawable> rectangles = List.of(new Rectangle());
        rectangles.forEach(Drawable::draw);
    }
}

/*Неверный подход. Наследование тут не сработает, ибо создание фигуры типа "Круг" (расширение) невозможно при так выполненном классе:

package ru.job4j.ood.ocp;

import java.util.List;

public class FiguresImplemetation {

    private static class Rectangle {
        public String draw() {
            return "...";
        }
    }

    public static void main(String[] args) {
        List<Rectangle> rectangles = List.of(new Rectangle());
        rectangles.forEach(Rectangle::draw);
    }
}
 */