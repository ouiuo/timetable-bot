package com.ouiuo.timetablebot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity(name = "classes")
public class TrainingPair {
    @Id
    private Long id;
    private String className;
    private String lectureName;
    private String addressName;
    //todo change string to ??
    private String classType;
    private Date startDate;
    private Date endDate;

    private Date updateDate;


    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return String.format("%s %s %s \n" +
                "%s-%s \n" +
                "%s ", className.substring(0, 16), classType, addressName, sdf.format(startDate), sdf.format(endDate), lectureName);
    }

    public StringBuffer toStringBuffer() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        StringBuffer stringBuffer = new StringBuffer()
                .append(className, 0, Math.min(className.length(), 16)).append(" ")
                .append(classType).append(" ")
                .append(addressName).append("\n")
                .append(sdf.format(startDate)).append("-").append(sdf.format(endDate)).append("\n")
                .append(lectureName).append("\n");

        return stringBuffer;
    }
}
