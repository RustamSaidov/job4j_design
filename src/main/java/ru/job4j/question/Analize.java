package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Iterator<User> cIterator = current.iterator();
        Iterator<User> pIterator = previous.iterator();
        Set<Integer> setOfPrevId = new HashSet<>();
        Set<Integer> setOfCurrId = new HashSet<>();

        while (pIterator.hasNext()) {
            setOfPrevId.add(pIterator.next().getId());
        }
        while (cIterator.hasNext()) {
            setOfCurrId.add(cIterator.next().getId());
        }

        cIterator = current.iterator();
        pIterator = previous.iterator();
        User cUser = cIterator.next();
        User pUser = pIterator.next();

        for (int i = 0; i < Math.max(current.size(), previous.size()); i++) {

            if (!previous.contains(cUser) && setOfPrevId.contains(cUser.getId())) {
                info.setChanged(info.getChanged() + 1);
            }

            if (!previous.contains(cUser) && !setOfPrevId.contains(cUser.getId())) {
                info.setAdded(info.getAdded() + 1);
            }

            if (!current.contains(pUser) && !setOfCurrId.contains(pUser.getId())) {
                info.setDeleted(info.getDeleted() + 1);
            }

            if (cIterator.hasNext()) {
                cUser = cIterator.next();
            }
            if (pIterator.hasNext()) {
                pUser = pIterator.next();
            }
        }
        return info;
    }
}
