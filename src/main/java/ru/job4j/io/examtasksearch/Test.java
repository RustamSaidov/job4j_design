package ru.job4j.io.examtasksearch;

import ru.job4j.collection.list.List;
import ru.job4j.collection.list.SimpleLinkedList;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        String mask = "*.???";
        List<Path> list = new SimpleLinkedList<>();
        list.add(Path.of("some.doc"));
        list.add(Path.of("some.docx"));
        list.add(Path.of("some.txt"));
        list.add(Path.of("new.docx"));


        Iterator<Path> iterator = list.iterator();
        while (iterator.hasNext()){
            Path path = iterator.next();
            if(path.toString().equals("some.docx")){
                System.out.println(path);
            }

        }
        System.out.println("-------------");
        Iterator<Path> iterator1 = list.iterator();
        while (iterator1.hasNext()){
            Path path = iterator1.next();
            System.out.println("FFF:"+path);
            String str = "glob:"+mask;
            System.out.println(str);
            matches(path, str);

        }
//        System.out.println("-------------");
//        Iterator iterator2 = list.iterator();
//        while (iterator2.hasNext()){
//
//        }
//        System.out.println("-------------");
    }

    public static void matches(Path path, String glob){
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher(glob);
        System.out.println(matcher.matches(path));
    }
}
