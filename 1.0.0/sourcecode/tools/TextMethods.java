package tools;
/*//////////////////////////////////////
Methods shared by all clases of CryptoEX
//////////////////////////////////////*/
public class TextMethods {

	//Global variables
	char specialaUC[] = "ÁÉÍÓÚ".toCharArray();
	char specialalc[] = "áéíóú".toCharArray();
	char simpleaUC[] = "AEIOU".toCharArray();
	char simplealc[] = "aeiou".toCharArray();
	
	public String getMessage(char[] c){
		String m = new String(c);
		return m;
	}
	
	public char[] textChain (String m){
		char[] cadena = m.toCharArray();
		return cadena;
	}
	
	public char[] newChain(int l, char s){
		char[] c = new char[l];
		for (int i = 0; i < c.length; i++){
			c[i]=s;
		}
		return c;
	}
	
	public String catString(String[] s, String c){
		String cat = new String();
		for (int i = 0; i < s.length; i++){
			cat += s[i];
			cat += c;
		}
		return cat;
	}
	
	public char[] simplifyChain (char[] c){
		for (int i = 0; i < c.length; i++){
			for (int e = 0; e < 5; e++){
				if (c[i] == specialaUC[e]){
					c[i] = simpleaUC[e];
					break;
				} else if (c[i] == specialalc[e]){
					c[i] = simplealc[e];
					break;
				}
			}
		}
		return c;
	}
	
}