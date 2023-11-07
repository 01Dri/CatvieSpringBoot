package me.dri.Catvie.domain.exceptions;

import java.util.Date;
import java.util.Objects;

public class ExceptionEntity {

    private Date timestamp;

    private String message;

    private String details;
    private Integer errorstatus;

    public ExceptionEntity() {

    }

    public ExceptionEntity(Date timestamp, String message, String details, Integer errorstatus) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errorstatus = errorstatus;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getErrorstatus() {
        return errorstatus;
    }

    public void setErrorstatus(Integer errorstatus) {
        this.errorstatus = errorstatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionEntity that = (ExceptionEntity) o;
        return Objects.equals(timestamp, that.timestamp) && Objects.equals(message, that.message) && Objects.equals(details, that.details) && Objects.equals(errorstatus, that.errorstatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, message, details, errorstatus);
    }

    @Override
    public String toString() {
        return "ExceptionEntity{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                ", errorstatus=" + errorstatus +
                '}';
    }
}
