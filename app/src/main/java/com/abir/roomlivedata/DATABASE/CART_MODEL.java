package com.abir.roomlivedata.DATABASE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_table")
public class CART_MODEL {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "cartNumber")
    public String cart_Number;

    public CART_MODEL(@NonNull String cart_Number) {
        this.cart_Number = cart_Number;
    }

    public String getcartcount() {
        return this.cart_Number;
    }

    @NonNull
    public String getCart_Number() {
        return cart_Number;
    }
}
