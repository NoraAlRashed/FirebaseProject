package com.firebaseproject.app;


public class Students {
    //COLUMNS
    static final String ROW_ID="id";
    static final String STUDENTID = "studentId";
    static final String NAME = "name";
    static final String SURNAME = "surname";
    static final String FATHERNAME = "fatherName";
    static final String NATIONALID = "nationalId";
    static final String DOB = "dob";
    static final String GENDER = "gender";


    //DB PROPERTIES
    static final String DB_NAME="b_DB";
    static final String TB_NAME="b_TB";
    static final int DB_VERSION='1';


//CREATE TABLE STATEMENTS
    static final String CREATE_TB="CREATE TABLE b_TB(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "studentId TEXT NOT NULL,name TEXT NOT NULL,surname TEXT NOT NULL,fatherName TEXT NOT NULL,nationalId TEXT NOT NULL,dob TEXT NOT NULL,gender TEXT NOT NULL);";
}

