package com.syrgdev.testapplicationforrentateam.persistance;

import com.syrgdev.testapplicationforrentateam.common.UserData;
import com.syrgdev.testapplicationforrentateam.network.UserSchema;
import com.syrgdev.testapplicationforrentateam.persistance.room.UserEntity;

public class Converters {

    public static UserData toUserData(UserSchema userSchema) {
        return UserData.builder()
                .id(userSchema.getId())
                .email(userSchema.getEmail())
                .firstName(userSchema.getFirstName())
                .lastName(userSchema.getLastName())
                .avatar(userSchema.getAvatar())
                .build();
    }

    public static UserData toUserData(UserEntity userEntity) {
        return UserData.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .avatar(userEntity.getAvatar())
                .build();
    }

    public static UserEntity toUserEntity(UserData userData) {
        return new UserEntity(userData.getId(),
                userData.getEmail(),
                userData.getFirstName(),
                userData.getLastName(),
                userData.getAvatar()
        );
    }
}
