package com.activiti.service;

public class Calculator {
	public int calculate(int num1, int num2){
		int sum = num1 + num2;
		System.out.println(""+num1 + " + " + num2+ " = "+sum);
		return sum;
	}
}
