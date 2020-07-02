package com.example.simple_cms.create.utility.model.shape;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.simple_cms.create.utility.IJsonPacker;
import com.example.simple_cms.create.utility.model.Action;
import com.example.simple_cms.create.utility.model.ActionIdentifier;
import com.example.simple_cms.create.utility.model.movement.Movement;
import com.example.simple_cms.create.utility.model.poi.POI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * This class is in charge of the action shape that you can send to LG
 */
public class Shape extends Action implements IJsonPacker, Parcelable{

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Shape createFromParcel(Parcel in) {
            return new Shape(in);
        }

        public Shape[] newArray(int size) {
            return new Shape[size];
        }
    };

    private POI poi;
    private ArrayList points;
    private boolean isExtrude;

    public Shape(){
        super(ActionIdentifier.SHAPES_ACTIVITY.getId());
        poi = null;
        points = new ArrayList<>();
        isExtrude = false;
    }


    public Shape(ArrayList<Point> points, boolean isExtrude, POI poi) {
       super(ActionIdentifier.SHAPES_ACTIVITY.getId());
       this.poi = poi;
       this.points = points;
       this.isExtrude = isExtrude;
    }

    public Shape(Parcel in) {
        super(ActionIdentifier.SHAPES_ACTIVITY.getId());
        this.poi = in.readParcelable(POI.class.getClassLoader());
        this.points = in.readArrayList(Point.class.getClassLoader());
        this.isExtrude = in.readInt() != 0;
    }

    public Shape(Shape shape){
        super(ActionIdentifier.SHAPES_ACTIVITY.getId());
        this.poi = shape.poi;
        this.points = shape.points;
        this.isExtrude = shape.isExtrude;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public Shape setPoints(ArrayList<Point> points) {
        this.points = points;
        return this;
    }

    public boolean isExtrude() {
        return isExtrude;
    }

    public Shape setExtrude(boolean extrude) {
        isExtrude = extrude;
        return this;
    }

    public POI getPoi() {
        return poi;
    }

    public Shape setPoi(POI poi) {
        this.poi = poi;
        return this;
    }

    @Override
    public JSONObject pack() throws JSONException {
        JSONObject obj = new JSONObject();

        obj.put("shape_poi", poi);
        JSONArray jsonPoints = new JSONArray(points);
        obj.put("jsonPoints: ", jsonPoints);
        obj.put("isExtrude", isExtrude);

        return obj;
    }

    @Override
    public Object unpack(JSONObject obj) throws JSONException {

        poi = (POI) obj.get("shape_poi");
        JSONArray jsonPoints =  obj.getJSONArray("jsonPoints");
        ArrayList<Point> arrayPoint = new ArrayList<>();
        for(int i = 0; i < jsonPoints.length(); i++){
            arrayPoint.add(arrayPoint.get(i));
        }
        this.points = arrayPoint;
        this.isExtrude = obj.getBoolean("isExtrude");

        return this;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Location Name: ").append(poi.getPoiLocation().getName());
        for (int i = 0; i < points.size(); i++){
            stringBuilder.append("Point ").append(i).append(": ").append(points.get(i).toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(poi, flags);
        parcel.writeList(points);
        parcel.writeInt(isExtrude ? 1 : 0);
    }
}