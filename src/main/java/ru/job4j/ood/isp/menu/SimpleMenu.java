package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean result = false;
        MenuItem menuItem = new SimpleMenuItem(childName,actionDelegate);
        if(Objects.equals(parentName, Menu.ROOT)){
            rootElements.add(menuItem);
            result = true;
        } else {
            Optional<ItemInfo> itemInfo = findItem(parentName);
            menuItem = itemInfo.get().menuItem;

            if (itemInfo.isPresent()) {
                int index = rootElements.indexOf(menuItem);
                rootElements.get(index).getChildren().add(menuItem);
                result = true;
            }
        }
        return result;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<ItemInfo> itemInfo = findItem(itemName);
        MenuItem menuItem = itemInfo.get().menuItem;
        MenuItemInfo menuItemInfo = new MenuItemInfo(menuItem, itemInfo.get().number);
        return Optional.of(menuItemInfo);
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return null;
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator dfsIterator = new DFSIterator();
        ItemInfo i = null;
        while (dfsIterator.hasNext()) {
            i = dfsIterator.next();
            if (name.equals(i.menuItem.getName())) {
                break;
            }
        }
        return Optional.ofNullable(i);
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
