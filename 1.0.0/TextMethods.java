/*//////////////////////////////////////
Methods shared by all clases of CryptoEX
//////////////////////////////////////*/
class TextMethods {

	String mostrarMensaje(char[] c){
		String m = new String(c);
		return m;
	}
	
	char[] cadenaTexto (String m){
		char[] cadena = m.toCharArray();
		return cadena;
	}
	
	char[] newChain(int l, char s){
		char[] c = new char[l];
		for (int i = 0; i < c.length; i++){
			c[i]=s;
		}
		return c;
	}
	
}