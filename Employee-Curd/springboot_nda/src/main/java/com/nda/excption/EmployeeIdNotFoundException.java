package com.nda.excption;

public class EmployeeIdNotFoundException extends Exception{
    public EmployeeIdNotFoundException(String mesg){
        super(mesg);
    }
    public EmployeeIdNotFoundException(){

    }
}
