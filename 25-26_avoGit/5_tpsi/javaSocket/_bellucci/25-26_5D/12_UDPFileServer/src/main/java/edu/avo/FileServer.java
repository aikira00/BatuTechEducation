package edu.avo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileServer {
    private final int blockSize;

    public FileServer(int blockSize) {
        this.blockSize = blockSize;
    }

    public boolean exists(String filename){
        File f = new File(filename);
        return f.exists();
    }

    public boolean isreadable(String filename){
        File f = new File(filename);
        return f.exists() && f.canRead();
    }

    public boolean iswritable(String filename){
        File f = new File(filename);
        return !f.exists() && f.canWrite();
    }

    public long getNumberOfBlocks(String filename) {
        long length = -1;
        File file = new File(filename);
        if (file.exists() && file.canRead()) {
            length = file.length() / blockSize + 1;
        }
        return length;
    }

    public void writeBlock (int blockNumber, byte[] block, String filename) throws FileNotFoundException, IOException {
        RandomAccessFile raf = null;
        raf = new RandomAccessFile(filename, "rw");
        int pos = (blockNumber - 1) * blockSize;
        raf.seek(pos);
        raf.write(block);
        raf.close();
    }

    public byte[] readBlock(int blockNumber, String filename) throws FileNotFoundException, IOException {
        RandomAccessFile raf = null;
        raf = new RandomAccessFile(filename, "r");
        byte[] block ;
        int dim = blockSize;
        long length = new File(filename).length();
        int pos = (blockNumber-1) * blockSize;
        // se vado oltre allora questo è ultimo blocco e quindi assegno
        // a ultimo blocco una dimensione che sarà minore di 512
        if(pos + blockSize > length){
            dim = (int) length - pos;
        }

        block = new byte[(int)dim];
        raf.seek(pos);
        raf.read(block);
        raf.close();
        return block;
    }
}
