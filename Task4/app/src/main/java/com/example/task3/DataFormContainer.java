package com.example.task3;

import android.os.Parcel;
import android.os.Parcelable;

public class DataFormContainer implements Parcelable {
    private String avatarHref;
    private String name;
    private String phone;
    private String email;

    public DataFormContainer(String image, String name, String phone, String email) {
        this.avatarHref = image;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    protected DataFormContainer(Parcel in) {
        avatarHref = in.readString();
        name = in.readString();
        phone = in.readString();
        email = in.readString();
    }

    public static final Creator<DataFormContainer> CREATOR = new Creator<DataFormContainer>() {
        @Override
        public DataFormContainer createFromParcel(Parcel in) {
            return new DataFormContainer(in);
        }

        @Override
        public DataFormContainer[] newArray(int size) {
            return new DataFormContainer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(avatarHref);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarHref() {
        return avatarHref;
    }

    public void setAvatarHref(String avatarHref) {
        this.avatarHref = avatarHref;
    }
}
