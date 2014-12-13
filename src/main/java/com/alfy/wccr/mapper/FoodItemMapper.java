package com.alfy.wccr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.alfy.wccr.dto.FoodItem;

/**
 * Author: Alan Created: 12/13/2014.
 */
public class FoodItemMapper {
  public static FoodItem resultSetToFoodItem(ResultSet resultSet) throws SQLException {
    FoodItem foodItem = new FoodItem();
    foodItem.setId(resultSet.getLong("food_item_id"));
    foodItem.setItem(resultSet.getString("item"));
    foodItem.setGross(resultSet.getDouble("gross"));
    foodItem.setTare(resultSet.getDouble("tare"));
    foodItem.setNet(resultSet.getDouble("net"));
    foodItem.setCalg(resultSet.getDouble("calg"));
    foodItem.setCal(resultSet.getDouble("cal"));
    foodItem.setDate(resultSet.getString("date"));
    foodItem.setUser(resultSet.getString("user"));
    return foodItem;
  }
}
