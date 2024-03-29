![Icon](https://gitlab.com/rodrigovalla/cryptoeX/-/raw/master/assets/img/icon.png)
# CryptoEX

### About the different tools available
- **CEXPrime** reorders the symbols from a message using multiplication in a Z_p set
(a set of prime cardinality). The prime cardinality ensures that the function can be
reversed to recover the message.  

- **CEXMirror** encrypt the message showing the symbols in reverse order. It offers
two options:
	1. Reverse all the message as a unit.
	2. Divide the message using a character selected by the user and then reverse
	each "word".  


- **CEXCaesarCipher** uses a very simple encryption technique known as _Caesar Cipher_
(yes, it was used by the Roman Empire). The technique consists in replacing each
letter of the message by a another letter at a constant distance in the alphabet.
For example, shifting 2 places the A becomes C, the B becomes D and so on... You can
learn more at [Wikipedia](https://en.wikipedia.org/wiki/Caesar_cipher).  

- **CEXKey** ciphers the message using one of four secret keys, simply changing each
character from the message to another. You can make your own keys changing the code
although you must be careful with the _reference char array_ size. I do not recommend
changing the _reference char array_ because its symbols are ordered taking into account
the letter frequency in English and Spanish (lear more at
[Wikipedia](https://en.wikipedia.org/wiki/Letter_frequency)).  

*Version 1.0.0 - May 2018*

Rodrigo Valla
