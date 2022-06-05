package ru.job4j.question;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Iterator<User> iterator = current.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();
            if(!previous.contains(user)){
                if(user == null){
                    info.setDeleted(info.getDeleted()+1);
                } else if(){
                    info.
                }
            }
        }



        return info;

    }

}
