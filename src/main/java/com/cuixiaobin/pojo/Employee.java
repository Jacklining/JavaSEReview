package com.cuixiaobin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Employee implements Serializable {

String name;
Character sex;
int salary;
int bonus;
String info;




}
