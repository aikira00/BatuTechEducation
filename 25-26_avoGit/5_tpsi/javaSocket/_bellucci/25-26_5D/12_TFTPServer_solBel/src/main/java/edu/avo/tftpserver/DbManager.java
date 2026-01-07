/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.tftpserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author palma
 */
public class DbManager {

    private final int blockSize;

    public DbManager(int blockSize) {
        this.blockSize = blockSize;
    }

    public boolean exists(String filename) {
        return new File(filename).exists();
    }

    public boolean isReadable(String filename) {
        File file = new File(filename);
        return file.exists() && file.canRead();
    }

    public boolean isWritable(String filename) {
        File file = new File(filename);
        File parent = file.getParentFile() != null ? file.getParentFile() : new File(".");
        return !file.exists() && parent.canWrite();
    }

    public long getNumberOfBlocks(String filename) {
        long length = -1;
        File file = new File(filename);
        if (file.exists() && file.canRead()) {
            length = file.length() / blockSize + 1;
        }
        return length;
    }

    public byte[] readBlock(int blockNumber, String filename) {
        byte[] block = null;
        long length = new File(filename).length();
        int myBlock = blockSize;
        try {
            RandomAccessFile in = new RandomAccessFile(filename, "r");
            int skip = (blockNumber - 1) * blockSize;
            if (skip + blockSize > length) {
                if (skip < length) {
                    myBlock = (int) (length - skip);
                }
            }
            in.seek(skip);
            block = new byte[myBlock];
            in.read(block);
            in.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return block;
    }

    public void writeBlock(int blockNumber, byte[] block, String filename) {
        try {
            RandomAccessFile out = new RandomAccessFile(filename, "rw");
            int skip = (blockNumber - 1) * blockSize;
            out.seek(skip);
            out.write(block);
            out.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
