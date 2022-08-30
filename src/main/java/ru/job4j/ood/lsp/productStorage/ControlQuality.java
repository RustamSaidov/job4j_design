package ru.job4j.ood.lsp.productStorage;

import ru.job4j.gc.prof.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ControlQuality {
    private Date currentDate = new Date();

    private static double getShelfLifePersentage(Date futureDay, Date day) {
        int shelfLife;
        int remainingShelfLife;
        double shelfLifePercentage;


        shelfLife = getDateDifferenceInDays(futureDay, day);
        System.out.println("11111 " + shelfLife);
        remainingShelfLife = getDateDifferenceInDays(futureDay, new Date());
        System.out.println("22222 " + remainingShelfLife);
        shelfLifePercentage = (double) remainingShelfLife / shelfLife;
        System.out.println("shelfLifePercentage:" + shelfLifePercentage);
        return shelfLifePercentage;
    }

    public static int getDateDifferenceInDays(Date futureDay, Date day) {
        int differenceInDays = 0;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Calendar cEx = new GregorianCalendar();
            cEx.setTime(futureDay);
            Calendar cCr = new GregorianCalendar();
            cCr.setTime(day);

            Date createDate = dateFormat.parse(String.format("%s.%s.%s", cCr.get(Calendar.DAY_OF_MONTH),
                    cCr.get(Calendar.MONTH), cCr.get(Calendar.YEAR)));
            Date expiryDate = dateFormat.parse(String.format("%s.%s.%s", cEx.get(Calendar.DAY_OF_MONTH),
                    cEx.get(Calendar.MONTH), cEx.get(Calendar.YEAR)));

            long milliseconds = expiryDate.getTime() - createDate.getTime();
            differenceInDays = (int) (milliseconds / (24 * 60 * 60 * 1000));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return differenceInDays;
    }

    public void distribute(Food food, List<Store> stores) {
        double foodSheltLifePers = getShelfLifePersentage(food.getExpiryDate(), food.getCreateDate());
        for(int i=0; i<stores.size(); i++){
            stores.get(i).checkToAdd(food,foodSheltLifePers);
        }
    }
}
