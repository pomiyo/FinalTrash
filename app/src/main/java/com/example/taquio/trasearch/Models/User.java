package com.example.taquio.trasearch.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Edward 2018.
 */

public class User implements Parcelable {


    private String userID, Email, Name, Image,
            Image_thumb, UserName, device_token, PhoneNumber;
    private Boolean isVerify;

    public User() {

    }

    protected User(Parcel in) {
        userID = in.readString();
        Email = in.readString();
        Name = in.readString();
        Image = in.readString();
        Image_thumb = in.readString();
        UserName = in.readString();
        device_token = in.readString();
        PhoneNumber = in.readString();
        byte tmpIsVerify = in.readByte();
        isVerify = tmpIsVerify == 0 ? null : tmpIsVerify == 1;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImage_thumb() {
        return Image_thumb;
    }

    public void setImage_thumb(String image_thumb) {
        Image_thumb = image_thumb;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Boolean getVerify() {
        return isVerify;
    }

    public void setVerify(Boolean verify) {
        isVerify = verify;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userID);
        parcel.writeString(Email);
        parcel.writeString(Name);
        parcel.writeString(Image);
        parcel.writeString(Image_thumb);
        parcel.writeString(UserName);
        parcel.writeString(device_token);
        parcel.writeString(PhoneNumber);
        parcel.writeByte((byte) (isVerify == null ? 0 : isVerify ? 1 : 2));
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + userID + '\'' +
                ", Email='" + Email + '\'' +
                ", Name='" + Name + '\'' +
                ", Image='" + Image + '\'' +
                ", Image_thumb='" + Image_thumb + '\'' +
                ", UserName='" + UserName + '\'' +
                ", device_token='" + device_token + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", isVerify=" + isVerify +
                '}';
    }
}
