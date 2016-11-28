package com.chensen.meizhi.bean;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * author：chensen on 2016/11/25 13:44
 * desc：
 */

public class GirlBean extends RealmObject implements Parcelable {
    private String _id;
    private String desc;
    private String type;
    private String url;
    private int height;

    public GirlBean() {
    }

    protected GirlBean(Parcel in) {
        _id = in.readString();
        desc = in.readString();
        type = in.readString();
        url = in.readString();
        height = in.readInt();
    }


    public static final Creator<GirlBean> CREATOR = new Creator<GirlBean>() {
        @Override
        public GirlBean createFromParcel(Parcel in) {
            return new GirlBean(in);
        }

        @Override
        public GirlBean[] newArray(int size) {
            return new GirlBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(_id);
        parcel.writeString(desc);
        parcel.writeString(type);
        parcel.writeString(url);
        parcel.writeInt(height);

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
