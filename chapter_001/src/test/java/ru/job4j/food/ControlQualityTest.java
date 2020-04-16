package ru.job4j.food;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ControlQualityTest {

    @Test
    public void moveFoodFromWarehouseToTrash() throws ParseException {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = df.parse("2015-02-10");
        ControlQuality cq = new ControlQuality(currentDate);
        Food eggs = new ExpirableFood("Eggs", df.parse("2015-01-01"), df.parse("2015-02-01"), 100);

        FoodStorage warehouse = new CommonFoodStorage(new WarehousePermissions());
        warehouse.addFood(eggs);
        FoodStorage shop = new ShopFoodStorage(new CommonFoodStorage(new ShopPermissions()), currentDate);
        FoodStorage trash = new CommonFoodStorage(new TrashPermissions());
        cq.addStorage(warehouse);
        cq.addStorage(shop);
        cq.addStorage(trash);

        cq.rearrangeFood();
        assertThat(warehouse.contains(eggs), is(false));
        assertThat(trash.contains(eggs), is(true));
    }

    @Test
    public void moveFoodFromWarehouseToShop() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = df.parse("2015-01-12");
        ControlQuality cq = new ControlQuality(currentDate);
        Food eggs = new ExpirableFood("Eggs", df.parse("2015-01-01"), df.parse("2015-02-01"), 100);

        FoodStorage warehouse = new CommonFoodStorage(new WarehousePermissions());
        warehouse.addFood(eggs);
        FoodStorage shop = new ShopFoodStorage(new CommonFoodStorage(new ShopPermissions()), currentDate);
        FoodStorage trash = new CommonFoodStorage(new TrashPermissions());

        cq.addStorage(warehouse);
        cq.addStorage(shop);
        cq.addStorage(trash);

        cq.rearrangeFood();
        assertThat(warehouse.contains(eggs), is(false));
        assertThat(shop.contains(eggs), is(true));
    }

    @Test
    public void reducePriceOfFood() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = df.parse("2015-01-29");
        ControlQuality cq = new ControlQuality(currentDate);
        Food eggs = new ExpirableFood("Eggs", df.parse("2015-01-01"), df.parse("2015-02-01"), 100);

        FoodStorage warehouse = new CommonFoodStorage(new WarehousePermissions());
        warehouse.addFood(eggs);
        FoodStorage shop = new ShopFoodStorage(new CommonFoodStorage(new ShopPermissions()), currentDate);
        shop.addFood(eggs);
        FoodStorage trash = new CommonFoodStorage(new TrashPermissions());

        cq.addStorage(warehouse);
        cq.addStorage(shop);
        cq.addStorage(trash);

        cq.rearrangeFood();
        assertThat(shop.contains(eggs), is(true));
        assertThat(eggs.getDiscount(), is((byte) 50));
    }
}
