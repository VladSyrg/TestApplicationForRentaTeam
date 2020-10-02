package com.syrgdev.testapplicationforrentateam.persistance.room;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

@Entity(tableName = "userTable",
        primaryKeys = {"id"})
public class UserEntity {

    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "email")
    private String mEmail;

    @ColumnInfo(name = "first_name")
    private String mFirstName;

    @ColumnInfo(name = "last_name")
    private String mLastName;

    @ColumnInfo(name = "avatar")
    private String mAvatar;

    public UserEntity(int id,
                      String email,
                      String firstName,
                      String lastName,
                      String avatar) {
        mId = id;
        mEmail = email;
        mFirstName = firstName;
        mLastName = lastName;
        mAvatar = avatar;
    }

    public int getId() {
        return mId;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getAvatar() {
        return mAvatar;
    }

    @Dao
    public interface DAO {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insert(List<UserEntity> entities);

        @Query("SELECT * FROM userTable ORDER BY id DESC")
        Single<List<UserEntity>> fetchAll();
    }

}
