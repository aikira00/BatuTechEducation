conversione numeri con due byte

int a = 0X80
1000 0000

operazone and bitwise per togliere il segno 
perché in java non ho tipo unsigned

1000 0000 = -128

1000 0000
1111 1111
1000 0000 => + 128


se numero su due byte
a = 265
00000001 00001001

devo prendere il byte alto e il byte basso

sposto a destra per prendere il primo byte
0000 0001
byte h = (byte)(a >> 8) & 0Xff 
prendo secondo byte
0000 1001
byte b = (byte)(a & 0Xff)

per ricostruirlo
sposto a sinistra per recuperare byte alto 
0000 0001 0000 0000
0000 0000 0000 1001
int a = (h << 8) | (b& 0Xff)

