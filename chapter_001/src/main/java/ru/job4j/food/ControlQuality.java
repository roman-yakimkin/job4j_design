package ru.job4j.food;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Класс - контроль качества продуктов
 * @author Roman Yakimkin (r.yakimkin@yandex.ru)
 * @version 1.0
 * @since 30.03.2020
 */
public class ControlQuality {
    Date aDate;
    List<IFoodStorage> storages;
    List<IFood> temporary;

    public ControlQuality(Date aDate) {
        this.aDate = aDate;
        storages = new ArrayList<>();
        temporary = new ArrayList<>();
    }

    public void addStorage(IFoodStorage storage){
        storages.add(storage);
    }

    /**
     * Пересортировка продуктов
     */
    public void rearrangeFood() {

        for (IFoodStorage storage : storages) {
            Iterator<IFood> iter = storage.getFoodStorage().iterator();
            while (iter.hasNext()) {
                IFood food = iter.next();
                if (storage.shouldBeRemoved(food, aDate)) {
                    temporary.add(food);
                    iter.remove();
                }
            }
        }

        for (IFood food : temporary) {
            for (IFoodStorage storage : storages) {
                if (storage.shouldBeAdded(food, aDate)) {
                    storage.addFood(food);
                }
            }
        }

        temporary.clear();
    }
}
