package com.example.animalcare;

/**
 * Created by Blindsight3D on 10/10/2017.
 */

public class cageStatus {

    private Object _id;
    private int EmpID;
    private int CageID;
    private String Status;
    private int _v;

    public cageStatus(int empID, int cageID, String status, int _v) {
        EmpID = empID;
        CageID = cageID;
        Status = status;
        this._v = _v;
    }

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public int getEmpID() {
        return EmpID;
    }

    public void setEmpID(int empID) {
        EmpID = empID;
    }

    public int getCageID() {
        return CageID;
    }

    public void setCageID(int cageID) {
        CageID = cageID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int get_v() {
        return _v;
    }

    public void set_v(int _v) {
        this._v = _v;
    }
}
