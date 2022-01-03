import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE

    @BeforeEach
    public void add_restaurant_for_each_testcase(){
        restaurant = new Restaurant("Amelie's cafe","Chennai",LocalTime.parse("10:30:00"),LocalTime.parse("22:00:00"));
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        restaurant.openingTime = LocalTime.parse("00:00:00");
        restaurant.closingTime = LocalTime.parse("23:59:59");
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        restaurant.openingTime = LocalTime.parse("08:00:59");
        restaurant.closingTime = LocalTime.parse("08:01:00");
        assertFalse(restaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>ORDERCOST<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
     @Test
    public void get_total_order_price_value_when_item_are_passed(){
        List<String> itemname = new ArrayList<>();
        itemname.add("Sweet corn soup");
        itemname.add("Vegetable lasagne");

        assertTrue(restaurant.getTotalOrderCost(itemname) > 0);

    }

    @Test
    public void get_total_order_price_value_when_no_items_were_passed(){
        List<String> itemname = new ArrayList<>();
        assertTrue(restaurant.getTotalOrderCost(itemname) == 0);

    }

    @Test
    public void get_total_order_value_when_an_item_is_removed_total_cost_should_be_reduced (){
        List<String> itemname = new ArrayList<>();
        itemname.add("Sweet corn soup");
        itemname.add("Vegetable lasagne");

        int totalCost = restaurant.getTotalOrderCost(itemname);
        itemname.remove(1);
        assertTrue(restaurant.getTotalOrderCost(itemname) < totalCost);

    } 
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>ORDERCOST<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
