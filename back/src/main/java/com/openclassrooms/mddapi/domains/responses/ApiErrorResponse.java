package com.openclassrooms.mddapi.domains.responses;

public class ApiErrorResponse {
    private final int errorCode;
    private final String description;

    public ApiErrorResponse(int errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiErrorResponse that = (ApiErrorResponse) o;

        if (errorCode != that.errorCode) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = errorCode;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApiErrorResponse{" +
                "errorCode=" + errorCode +
                ", description='" + description + '\'' +
                '}';
    }
}


