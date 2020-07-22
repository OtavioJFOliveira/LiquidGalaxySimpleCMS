package com.lglab.diego.simple_cms.create.utility.model.shape;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.lglab.diego.simple_cms.create.utility.IJsonPacker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is in charge of the points that you can send to LG to create shapes
 */
public class Point implements IJsonPacker, Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Point createFromParcel(Parcel in) {
            return new Point(in);
        }

        public Point[] newArray(int size) {
            return new Point[size];
        }
    };

    private long id;
    private double longitude;
    private double latitude;
    private double altitude;

    public Point(){}

    public Point(long id, double longitude, double latitude, double altitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public Point(Parcel in){
        this.id = in.readLong();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.altitude = in.readDouble();
    }

     static List<Point> getPoints(List pointsDB) {
        List<Point> points = new ArrayList<>();

        com.lglab.diego.simple_cms.db.entity.shape.Point pointDB;
        for(int i = 0; i < pointsDB.size(); i++){
            pointDB = (com.lglab.diego.simple_cms.db.entity.shape.Point) pointsDB.get(i);
            Point point = new Point();
            point.setLongitude(pointDB.pointLongitude);
            point.setLatitude(pointDB.pointLatitude);
            point.setAltitude(pointDB.pointAltitude);
            points.add(point);
        }

        return points;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    @Override
    public JSONObject pack() throws JSONException {
        JSONObject obj = new JSONObject();

        obj.put("point_id", id);
        obj.put("longitude", longitude);
        obj.put("latitude", latitude);
        obj.put("altitude", altitude);

        return obj;
    }

    @Override
    public Point unpack(JSONObject obj) throws JSONException {

        id = obj.getLong("point_id");
        longitude = obj.getDouble("longitude");
        latitude = obj.getDouble("latitude");
        altitude = obj.getDouble("altitude");

        return this;
    }

    @NonNull
    @Override
    public String toString() {
        return "Longitude: " + longitude + " Latitude: " + latitude +  " Altitude: " + altitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeDouble(longitude);
        parcel.writeDouble(latitude);
        parcel.writeDouble(altitude);
    }
}
