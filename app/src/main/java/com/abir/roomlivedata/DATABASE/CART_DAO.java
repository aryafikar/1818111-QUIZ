package com.abir.roomlivedata.DATABASE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CART_DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CART_MODEL cart_model);

    @Update
    void updatecart(CART_MODEL cart_model);

    @Delete
    void deletecart(CART_MODEL cart_model);

    @Query("DELETE FROM cart_table WHERE cartNumber = :cart_model")
    void deletecart_single(String cart_model);

    @Query("DELETE FROM cart_table")
    void deleteAll();

    @Query("SELECT * from cart_table ORDER BY cartNumber ASC")
    LiveData<CART_MODEL> getAllWords();

    @Query("SELECT * FROM cart_table WHERE cartNumber =:c")
    CART_MODEL findcart(String c);

    @Query("UPDATE cart_table SET cartNumber=:new_cart WHERE cartNumber = :old_cart")
    void updatecartdata(String old_cart, String new_cart);

    @Query("SELECT * FROM cart_table")
    CART_MODEL user_profile();
}
