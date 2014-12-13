package com.alfy.wccr.endpoints;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.alfy.wccr.db.ConnectionPool;
import com.alfy.wccr.dto.FoodItem;
import com.alfy.wccr.mapper.FoodItemMapper;
import com.sun.jersey.api.ConflictException;
import com.sun.jersey.api.NotFoundException;

/**
 * Author: Alan Created: 12/10/2014.
 */

@Path("/food-item")
public class FoodItemsEndpoint {

  /**
   * Get a list of all the foodItems from the database.
   *
   * @return List of FoodItems
   */
  @GET
  @Path("/")
  @Produces("application/json")
  public List<FoodItem> getValues() {
    List<FoodItem> values = new ArrayList<>();

    try (Connection conn = ConnectionPool.getInstance().getConnection()) {
      try (PreparedStatement statement = conn.prepareStatement(
          "SELECT food_item_id, item, gross, tare, net, calg, cal, date, user " +
              " FROM food_item"
      )) {
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
          values.add(FoodItemMapper.resultSetToFoodItem(resultSet));
        }
      }
    }
    catch (SQLException e) {
      throw new WebApplicationException(e, Response
          .status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("SQL Exception: " + e.getMessage())
          .build()
      );
    }

    return values;
  }

  /**
   * Get a specific FoodItem from the database.
   *
   * @param id The id of the value to get
   * @return The id and value
   */
  @GET
  @Path("/{id}")
  @Produces("application/json")
  public FoodItem getValues(@PathParam("id") long id) {
    try (Connection conn = ConnectionPool.getInstance().getConnection()) {
      try (PreparedStatement statement = conn.prepareStatement(
          "SELECT food_item_id, item, gross, tare, net, calg, cal, date, user " +
              " FROM food_item " +
              " WHERE food_item_id = ?"
      )) {
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
          throw new NotFoundException();
        }

        return FoodItemMapper.resultSetToFoodItem(resultSet);
      }
    }
    catch (SQLException e) {
      throw new WebApplicationException(e, Response
          .status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("SQL Exception: " + e.getMessage())
          .build()
      );
    }
  }

  /**
   * Add a foodItem value to the database.
   *
   * @param foodItem A json object with the value to be added.
   * @return Created or error code.
   */
  @POST
  @Path("/")
  @Consumes("application/json")
  public Response addValue(FoodItem foodItem) {
    try (Connection conn = ConnectionPool.getInstance().getConnection()) {
      try (PreparedStatement statement = conn.prepareStatement(
          "INSERT INTO food_item (item, gross, tare, net, calg, cal, user) " +
              "VALUES (?,?,?,?,?,?,?)"
      )) {
        statement.setString(1, foodItem.getItem());
        statement.setDouble(2, foodItem.getGross());
        statement.setDouble(3, foodItem.getTare());
        statement.setDouble(4, foodItem.getNet());
        statement.setDouble(5, foodItem.getCal());
        statement.setDouble(6, foodItem.getCalg());
        statement.setString(7, foodItem.getUser());

        int result = statement.executeUpdate();
        if (result != 1) {
          throw new ConflictException("Failed: " + result);
        }
      }
    }
    catch (SQLException e) {
      throw new WebApplicationException(e, Response
          .status(Response.Status.INTERNAL_SERVER_ERROR)
          .entity("SQL Exception: " + e.getMessage())
          .build()
      );
    }

    return Response.created(null).build();
  }
}
