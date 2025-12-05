#preprocess main.i

#compile
#The next step is to compile filename.i 
#and produce an; intermediate compiled output 
#file filename.s. This file is in assembly-level instructions. Let’s see through this file using $nano filename.s  terminal command.

#assembly In this phase the filename.s is taken as input 
#and turned into filename.o by the assembler. 
#This file contains machine-level instructions
#only existing code is converted into machine language, 
#and the function calls like printf() are not resolved

#This is the final phase in which all the linking of function calls with their
#definitions is done. Linker knows where all these functions are implemented. 
#Linker does some extra work also, it adds some extra code to our program 
#which is required when the program starts and ends. For example, 
#there is a code that is required for setting up the environment 
#like passing command line arguments. This task can be easily 
#verified by using $size filename.o and $size filename. 
#Through these commands, we know how the output file increases 
#from an object file to an executable file.
#This is because of the extra code that Linker adds to our program. 

echo executing gcc -Wall -save-temps main.c -o main
gcc -Wall -save-temps main.c -o main