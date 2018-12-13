package com.kumquat.cart.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kumquat.cart.model.Cart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/search")
public class CartController {


    List<Cart> cartList;
    public CartController() {
         cartList = Arrays.asList(new Cart("suit","Versace","black",111.25), new Cart("dress","Gucci","black",110),
                 new Cart("jeans","Armani","blue", 90));

    }

    @GetMapping(value = "/articles/{column}/{value}")
    public List<Cart> sarchByBrand(@PathVariable String column, @PathVariable String value) throws JsonProcessingException {
        List<Cart> result = new ArrayList<>();
        for(Cart cart: cartList){
            if(column.equals("category")){
                searchByCategory(value, result, cart, cart.getCatgory());
            } else if(column.equals("brand")){
                searchByBrand(value, result, cart, cart.getBrand());
            } else if(column.equals("color")){
                searchByColor(value, result, cart, cart.getColor());
            } else if(column.equals("priceRange")){
                searchByPriceRange(value, result, cart);
            } else if(column.contains(":")){
                combinedSearch(value, result, cart);
            }
        }
        return result;
    }

    @GetMapping(value = "/sort/price/{value}")
    public List<Cart> sarchBySort(@PathVariable String value) throws JsonProcessingException {
        System.out.println("is the value::" + value);
        if(value.equals("asc")) {
            cartList.sort(Comparator.comparing(Cart::getPrice));
        } else {
            cartList.sort(Comparator.comparing(Cart::getPrice).reversed());

        }
    return cartList;

    }

    private void combinedSearch(@PathVariable String value, List<Cart> result, Cart cart) {
        String[] columnSplit = value.split(":");
        for(String columnname: columnSplit){
            if(columnname.equals("color")){
                searchByColor(value, result, cart, cart.getColor());
            }
            if(columnname.equals("brand")){
                searchByBrand(value, result, cart, cart.getBrand());
            }
            if(columnname.equals("category")){
                searchByCategory(value, result, cart, cart.getCatgory());
            }
        }
    }

    private void searchByPriceRange(@PathVariable String value, List<Cart> result, Cart cart) {
        String[] priceSplit = value.split("-");
        double min = Double.parseDouble(priceSplit[0]);
        double max = Double.parseDouble(priceSplit[1]);
        if(cart.getPrice()>=min && cart.getPrice()<=max){
            result.add(cart);
        }
    }

    private void searchByColor(@PathVariable String value, List<Cart> result, Cart cart, String color) {
        if (color.equals(value)) {
            result.add(cart);
        }
    }

    private void searchByBrand(@PathVariable String value, List<Cart> result, Cart cart, String brand) {
        if (brand.equals(value)) {
            result.add(cart);
        }
    }

    private void searchByCategory(@PathVariable String value, List<Cart> result, Cart cart, String catgory) {
        if (catgory.equals(value)) {
            result.add(cart);
        }
    }


}
