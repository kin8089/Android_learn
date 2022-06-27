package com.cookandroid.login2.vo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MemberVO implements Parcelable {
    @SerializedName("Username")
    private String Username;
    @SerializedName("Password")
    private String Password;


    public MemberVO(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String id) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }


    protected MemberVO(Parcel in) {
        Username = in.readString();
        Password = in.readString();
    }

    public static final Creator<MemberVO> CREATOR = new Creator<MemberVO>() {
        @Override
        public MemberVO createFromParcel(Parcel in) {
            return new MemberVO(in);
        }

        @Override
        public MemberVO[] newArray(int size) {
            return new MemberVO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Username);
        parcel.writeString(Password);
    }
}
