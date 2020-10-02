package com.syrgdev.testapplicationforrentateam.network;

import com.google.gson.annotations.SerializedName;

public class UserSchema {

    @SerializedName("id")
    private int mId;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("last_name")
    private String mLastName;

    @SerializedName("avatar")
    private String mAvatar;

    public UserSchema(int id,
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

    /*public static class Builder {

        private int mId;

        private String mEmail;

        private String mFirstName;

        private String mLastName;

        private String mAvatar;

        private Builder() {
        }

        public Builder id(int id) {
            mId = id;
            return this;
        }

        public Builder email(String email) {
            mEmail = email;
            return this;
        }

        public Builder firstName(String firstName) {
            mFirstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            mLastName = lastName;
            return this;
        }

        public Builder avatar(String avatar) {
            mAvatar = avatar;
            return this;
        }

        public UserSchema build() {
            return new UserSchema(
                    mId,
                    mEmail,
                    mFirstName,
                    mLastName,
                    mAvatar
            );
        }
    }*/
}
