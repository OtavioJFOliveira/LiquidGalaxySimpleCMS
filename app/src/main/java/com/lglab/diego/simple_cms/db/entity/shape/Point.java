package com.lglab.diego.simple_cms.db.entity.shape;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represent a Point of a Shape in the DB
 */
@Entity
public class Point {

    @PrimaryKey(autoGenerate = true)
    public long pointId;

    public long shapeCreatorId;

    public double pointLongitude;
    public double pointLatitude;
    public double pointAltitude;

    public static List getPointsDBMODEL(List points) {
        List pointsDBMODEL = new ArrayList();

        for(int i = 0; i < points.size(); i++){
            Point pointDBMODEL = new Point();
            com.lglab.diego.simple_cms.create.utility.model.shape.Point point = (com.lglab.diego.simple_cms.create.utility.model.shape.Point) points.get(i);
            pointDBMODEL.pointLongitude = point.getLongitude();
            pointDBMODEL.pointLatitude = point.getLatitude();
            pointDBMODEL.pointAltitude = point.getAltitude();
            pointsDBMODEL.add(pointDBMODEL);
        }

        return pointsDBMODEL;
    }
}
