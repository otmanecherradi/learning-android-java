package me.otmane.ntic.models;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Schedule {
    @SerializedName("id")
    private String id;
    @SerializedName("week_start")
    private Date weekStart;
    @SerializedName("bucket_url")
    private String bucketURL;
    @SerializedName("schedule_url")
    private String scheduleURL;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(Date weekStart) {
        this.weekStart = weekStart;
    }

    public String getBucketURL() {
        return bucketURL;
    }

    public void setBucketURL(String bucketURL) {
        this.bucketURL = bucketURL;
    }

    public String getScheduleURL() {
        return scheduleURL;
    }

    public void setScheduleURL(String scheduleURL) {
        this.scheduleURL = scheduleURL;
    }

    @NonNull
    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return simpleDateFormat.format(weekStart);
    }
}
