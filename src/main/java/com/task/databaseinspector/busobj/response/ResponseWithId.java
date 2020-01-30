package com.task.databaseinspector.busobj.response;

public class ResponseWithId<I> extends GeneralResponse {
    private final I id;

    private ResponseWithId(I id, boolean success, String errorMessage) {
        super(success, errorMessage);
        this.id = id;
    }

    public I getId() {
        return id;
    }

    public static <I> ResponseWithId<I> of(I id) {
        return new ResponseWithId<>(id, true, null);
    }

    public static <I> ResponseWithId<I> error(String err) {
        return new ResponseWithId<>(null, false, err);
    }

    @Override
    public String toString() {
        return "GeneralCreateResponse{" +
                "id=" + id +
                ", success=" + isSuccess() +
                ", errorMessage='" + getErrorMessage() + '\'' +
                '}';
    }
}
