package com.uca;

public class RomanNumber extends Number{
	
	private String roman;
	
	private int value;
	
	public RomanNumber(){
		//Ignored
	}
	
	public RomanNumber(String roman){
		this.roman = roman;
		System.out.println(this.roman);
		this.value = RomanConverter.getNumberFromRoman(this.roman);
	}
	
	public RomanNumber(int value){
		this.value = value;
		this.roman = RomanConverter.getRomanFromNumber(this.value);
	}

	protected RomanNumber RomanNumberDefault(int value, String roman){
		this.value = value;
		this.roman = roman;
		return this;
	}
	
	public RomanNumber(int value, String roman){
		if (RomanConverter.getNumberFromRoman(roman) != value){
			throw new IllegalArgumentException("la valeur et le chiffre romain ne correspondent pas\n");
		}
		this.value = value;
		this.roman = roman;
	}
	
	public String getRoman(){
		return this.roman;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public void setRoman(String roman){
		this.roman = roman;
		this.value = RomanConverter.getNumberFromRoman(this.roman);
	}
	public void setValue(int value){
		this.value = value;
		this.roman = RomanConverter.getRomanFromNumber(this.value);
	}
	
	
	/**
	* @{inheritDoc}
	*/
	@Override
	public double doubleValue() {
		return this.getValue();
	}

	/**
	* @{inheritDoc}
	*/
	@Override
	public float floatValue() {
		return this.getValue();
	}

	/**
	* @{inheritDoc}
	*/
	@Override
	public int intValue() {
		return this.getValue();
	}

	/**
	* @{inheritDoc}
	*/
	@Override
	public long longValue() {
		return this.getValue();
	}

	@Override
	public String toString() {
		return this.getRoman();
	}
}