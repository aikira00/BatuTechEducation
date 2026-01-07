https://datatracker.ietf.org/doc/html/rfc1350

usare classe File

File f = new File("nomefile.txt");
ha controllo se file esiste


isWritable si può fare solo se il file non esiste. Trivial File 
Transfer Protocolo non permette la scrittura di un file già esistente.
Il file non esiste e devo avere i permessi di scrittura.

Dimensione del file: serve a sapere quando terminare perché invio 
blocchi di 512 trannè ultimo. 


byte[] block ;
piazzo posizione vettore a 512 (sono nel caso sia ultima la cambio)
long dim = blockSize


long length = new file(filename).length();

long pos = (blockNumber-1) * blockSize;
se vado oltre allora questo è ultimo blocco e quindi assegno
a ultimo blocco una dimensione che sarà minore di 512
if(pos + blockSize > length){
    dim = lenght - pos;
}

a questo punto posso allocare  perché conosco dimensione
del blocco
block =  new [(int)dim];
raf.seek(pos);
rag.read(block);
raf.close();
return block;


//scrittura meno complessa
RandoAccessFile raf = new RandomAccessFile(file,
raf.seek(pos);
raf.write(block);
raf.close();
