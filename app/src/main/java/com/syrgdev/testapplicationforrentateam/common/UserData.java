package com.syrgdev.testapplicationforrentateam.common;

import android.os.Parcel;
import android.os.Parcelable;

public class UserData implements Parcelable {

    private int mId;

    private String mEmail;

    private String mFirstName;

    private String mLastName;

    private String mAvatar;

    private UserData(int id,
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

    /**
     * Clones existing PostData. Set fields and then call {@link UserData.Builder#build}
     * in order to create new PostData object.
     * <p>
     * Example:
     * <p>
     * PostData.toBuilder()
     * .id("5")
     * .title("Title")
     * .body("body")
     * .images(new ArrayList<String>())
     * .build();
     *
     * @return Builder object.
     */
    public Builder toBuilder() {
        return new Builder()
                .id(this.getId())
                .email(this.getEmail())
                .firstName(this.getFirstName())
                .lastName(this.getLastName())
                .avatar(this.getAvatar());
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mEmail);
        dest.writeString(mFirstName);
        dest.writeString(mLastName);
        dest.writeString(mAvatar);
    }

    public static final Parcelable.Creator<UserData> CREATOR = new Parcelable.Creator<UserData>() {
        public UserData createFromParcel(Parcel in) {
            return UserData.builder()
                    .id(in.readInt())
                    .email(in.readString())
                    .firstName(in.readString())
                    .lastName(in.readString())
                    .avatar(in.readString())
                    .build();
        }

        public UserData[] newArray(int size) {
            return new UserData[size];
        }
    };

    public static class Builder {

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

        public UserData build() {
            return new UserData(
                    mId,
                    mEmail,
                    mFirstName,
                    mLastName,
                    mAvatar
            );
        }
    }

}