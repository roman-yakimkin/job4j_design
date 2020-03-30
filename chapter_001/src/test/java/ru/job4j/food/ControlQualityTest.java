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
        IFood eggs = new Food("Eggs", df.parse("2015-01-01"), df.parse("2015-02-01"), 100);
        IFoodStorage warehouse = new Warehouse();
        warehouse.addFood(eggs);
        IFoodStorage shop = new Shop(currentDate);
        IFoodStorage trash = new Trash();
        cq.addStorage(warehouse);
        cq.addStorage(shop);
        cq.addStorage(trash);

        cq.rearrangeFood();
        assertThat(warehouse.getFoodStorage().contains(eggs), is(false));
        assertThat(trash.getFoodStorage().contains(eggs), is(true));
    }

    @Test
    public void moveFoodFromWarehouseToShop() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = df.parse("2015-01-12");
        ControlQuality cq = new ControlQuality(currentDate);
        IFood eggs = new Food("Eggs", df.parse("2015-01-01"), df.parse("2015-02-01"), 100);
        IFoodStorage warehouse = new Warehouse();
        warehouse.addFood(eggs);
        IFoodStorage shop = new Shop(currentDate);
        IFoodStorage trash = new Trash();
        cq.addStorage(warehouse);
        cq.addStorage(shop);
        cq.addStorage(trash);

        cq.rearrangeFood();
        assertThat(warehouse.getFoodStorage().contains(eggs), is(false));
        assertThat(shop.getFoodStorage().contains(eggs), is(true));
    }

    @Test
    public void reducePriceOfFood() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = df.parse("2015-01-29");
        ControlQuality cq = new ControlQuality(currentDate);
        IFood eggs = new Food("Eggs", df.parse("2015-01-01"), df.parse("2015-02-01"), 100);
        IFoodStorage warehouse = new Warehouse();
        IFoodStorage shop = new Shop(currentDate);
        shop.addFood(eggs);
        IFoodStorage trash = new Trash();
        cq.addStorage(warehouse);
        cq.addStorage(shop);
        cq.addStorage(trash);

        cq.rearrangeFood();
        assertThat(shop.getFoodStorage().contains(eggs), is(true));
        assertThat(eggs.getDiscount(), is((byte) 50));
    }



}
