package com.example.demoj1;

public class BitmapFileHeader {

    char[] bfType;
    int bfSize;
    short bfReserved1;
    short bfReserved2;
    int bfOffBits;

    private BitmapFileHeader() {

    }

    public static BitmapFileHeader readFromImage(byte[] image) {

        BitmapFileHeader bitmap = new BitmapFileHeader();

        bitmap.bfType = new char[2];
        int index = 0;
        bitmap.bfType[0] = (char) image[index++];
        bitmap.bfType[1] = (char) image[index++];

        bitmap.bfSize = (image[index + 3] & 0xff) << 24 | (image[index + 2] & 0xFF) << 16 | (image[index + 1] & 0xFF) << 8
                | (image[index + 0] & 0xFF);
        index += 4;

        bitmap.bfReserved1 = (short) (((image[index + 1] & 0xFF) << 8) | (image[index + 0] & 0xFF));
        index += 2;

        bitmap.bfReserved2 = (short) (((image[index + 1] & 0xFF) << 8) | (image[index + 0] & 0xFF));
        index += 2;

        bitmap.bfOffBits = (image[index + 3] & 0xff) << 24 | (image[index + 2] & 0xFF) << 16 | (image[index + 1] & 0xFF) << 8
                | (image[index + 0] & 0xFF);

        return bitmap;
    }
}
