package com.uca;


import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class RomanConverter{
	
	// Table des symboles
	private static final Collection<RomanNumber> SYMBOLS = new ArrayList<>();
	static {
		SYMBOLS.add(new RomanNumber(1000, "M"));
		SYMBOLS.add(new RomanNumber(900, "CM"));
		SYMBOLS.add(new RomanNumber(500, "D"));
		SYMBOLS.add(new RomanNumber(400, "CD"));
		SYMBOLS.add(new RomanNumber(100, "C"));
		SYMBOLS.add(new RomanNumber(90, "XC"));
		SYMBOLS.add(new RomanNumber(50, "L"));
		SYMBOLS.add(new RomanNumber(40, "XL"));
		SYMBOLS.add(new RomanNumber(10, "X"));
		SYMBOLS.add(new RomanNumber(9, "IX"));
		SYMBOLS.add(new RomanNumber(5, "V"));
		SYMBOLS.add(new RomanNumber(4, "IV"));
		SYMBOLS.add(new RomanNumber(1, "I"));
	  }

	// Expression reguliere de validation
	private static final Pattern VALIDATION_RE = Pattern.compile("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$");


	public static String getRomanFromNumber(int val) throws IllegalArgumentException{
		if (val < 1 | val > 3999){
            throw new IllegalArgumentException("Impossible de convertir " + val + " en chiffre romain car pas dans l'ensemble [1; 3999]");
        }
        String res = "";
        for (RomanNumber num : SYMBOLS){
            while(val >= num.getValue()){
                res += num.getRoman();
                val -= num.getValue();
            }
        }
        return res;
	}
	
	public static int getNumberFromRoman(String rom) throws IllegalArgumentException{
		if (!VALIDATION_RE.matcher(rom).matches()){
			throw new IllegalArgumentException("le chiffre romain n'est pas correct");
		}

		if (rom.isEmpty()){
            throw new IllegalArgumentException("Chaine vide");
        }

        //Calcul
        int res = 0;
        while (rom.length() > 1){
            String lettre = String.valueOf(rom.charAt(0));
			String suivante = String.valueOf(rom.charAt(1));
			
			for (RomanNumber num : SYMBOLS){
				if (num.getRoman().equals(String.valueOf (lettre + suivante))){
					lettre += suivante;
					rom = rom.substring(1);
					break;
				}
			}

			//permet de retirer l'élément qui nous sera plus utile (ou le deuxième si élement à 2 char)
			for (RomanNumber num : SYMBOLS){
				if (num.getRoman().equals(lettre)){
					res += num.getValue();
					break;
				}
			}
			rom = rom.substring(1);
			
        }
		
        if (rom.length() == 1){
			String lettre = String.valueOf ( rom.charAt(0));
			for (RomanNumber num : SYMBOLS){
				if (num.getRoman().equals(lettre)){
					res += num.getValue();
					break;
				}
			}
		}
        return res;
	}
}