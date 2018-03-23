package com.example.taquio.trasearch.Samok;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Del Mar on 2/24/2018.
 */

public class BusinessInfo implements Parcelable {

    String bsnEmail;
    String bsnBusinessName;
    String bsnLocation;
    String bsnMobile;
    String bsnPhone;
    String imagePermit;
    String image;
    String image_thumb;
    String deviceToken;
    String userId;
    String userType;
    Boolean isVerify;

   BusinessInfo(String bsnMail, String bsnBusinessName, String bsnLocation, String bsnMobile, String bsnPhone, String s, String none, String none1, String deviceToken, String user_id, String business, boolean b){}

    protected BusinessInfo(Parcel in) {
        bsnEmail = in.readString();
        bsnBusinessName = in.readString();
        bsnLocation = in.readString();
        bsnMobile = in.readString();
        bsnPhone = in.readString();
        imagePermit = in.readString();
        image = in.readString();
        image_thumb = in.readString();
        deviceToken = in.readString();
        userId = in.readString();
        userType = in.readString();
        byte tmpIsVerify = in.readByte();
        isVerify = tmpIsVerify == 0 ? null : tmpIsVerify == 1;
    }

    public static final Creator<BusinessInfo> CREATOR = new Creator<BusinessInfo>() {
        @Override
        public BusinessInfo createFromParcel(Parcel in) {
            return new BusinessInfo(in);
        }

        @Override
        public BusinessInfo[] newArray(int size) {
            return new BusinessInfo[size];
        }
    };

    @Override
    public String toString() {
        return "BusinessInfo{" +
                "bsnEmail='" + bsnEmail + '\'' +
                ", bsnBusinessName='" + bsnBusinessName + '\'' +
                ", bsnLocation='" + bsnLocation + '\'' +
                ", bsnMobile='" + bsnMobile + '\'' +
                ", bsnPhone='" + bsnPhone + '\'' +
                ", imagePermit='" + imagePermit + '\'' +
                ", image='" + image + '\'' +
                ", image_thumb='" + image_thumb + '\'' +
                ", deviceToken='" + deviceToken + '\'' +
                ", userId='" + userId + '\'' +
                ", userType='" + userType + '\'' +
                ", isVerify=" + isVerify +
                '}';
    }

    public String getBsnEmail() {
        return bsnEmail;
    }

    public void setBsnEmail(String bsnEmail) {
        this.bsnEmail = bsnEmail;
    }

    public String getBsnBusinessName() {
        return bsnBusinessName;
    }

    public void setBsnBusinessName(String bsnBusinessName) {
        this.bsnBusinessName = bsnBusinessName;
    }

    public String getBsnLocation() {
        return bsnLocation;
    }

    public void setBsnLocation(String bsnLocation) {
        this.bsnLocation = bsnLocation;
    }

    public String getBsnMobile() {
        return bsnMobile;
    }

    public void setBsnMobile(String bsnMobile) {
        this.bsnMobile = bsnMobile;
    }

    public String getBsnPhone() {
        return bsnPhone;
    }

    public void setBsnPhone(String bsnPhone) {
        this.bsnPhone = bsnPhone;
    }

    public String getImagePermit() {
        return imagePermit;
    }

    public void setImagePermit(String imagePermit) {
        this.imagePermit = imagePermit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_thumb() {
        return image_thumb;
    }

    public void setImage_thumb(String image_thumb) {
        this.image_thumb = image_thumb;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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
        parcel.writeString(bsnEmail);
        parcel.writeString(bsnBusinessName);
        parcel.writeString(bsnLocation);
        parcel.writeString(bsnMobile);
        parcel.writeString(bsnPhone);
        parcel.writeString(imagePermit);
        parcel.writeString(image);
        parcel.writeString(image_thumb);
        parcel.writeString(deviceToken);
        parcel.writeString(userId);
        parcel.writeString(userType);
        parcel.writeByte((byte) (isVerify == null ? 0 : isVerify ? 1 : 2));
    }
}
