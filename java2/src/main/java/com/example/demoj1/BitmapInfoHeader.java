package com.example.demoj1;

public class BitmapInfoHeader {

    int biSize;
    long biWidth;
    long biHeight;
    short biPlanes;
    short biBitCount;
    int biCompression;
    int biSizeImage;
    long biXPelsPerMeter;
    long biYPelsPerMeter;
    int biClrUsed;
    int biClrImportant;

    private BitmapInfoHeader() {

    }

    public static BitmapInfoHeader readFromImage(byte[] image) {

        BitmapInfoHeader bitmap = new BitmapInfoHeader();
        // LittleEndian order ...
        int index = 14;

        bitmap.biSize = (image[index + 3] & 0xff) << 24 | (image[index + 2] & 0xFF) << 16 | (image[index + 1] & 0xFF) << 8
                | (image[index + 0] & 0xFF);
        index += 4;

        bitmap.biWidth = ((image[index + 3] & 0xff) << 24) | (image[index + 2] & 0xFF) << 16 | (image[index + 1] & 0xFF) << 8
                | (image[index + 0] & 0xFF);
        index += 4;

        bitmap.biHeight = (image[index + 3] & 0xff) << 24 | (image[index + 2] & 0xFF) << 16 | (image[index + 1] & 0xFF) << 8
                | (image[index + 0] & 0xFF);
        index += 4;

        bitmap.biPlanes = (short) (((image[index + 1] & 0xFF) << 8) | (image[index + 0] & 0xFF));
        index += 2;

        bitmap.biBitCount = (short) (((image[index + 1] & 0xFF) << 8) | (image[index + 0] & 0xFF));
        index += 2;

        bitmap.biCompression = (image[index + 3] & 0xff) << 24 | (image[index + 2] & 0xFF) << 16 | (image[index + 1] & 0xFF) << 8
                | (image[index + 0] & 0xFF);
        index += 4;

        bitmap.biSizeImage = (image[index + 3] & 0xff) << 24 | (image[index + 2] & 0xFF) << 16 | (image[index + 1] & 0xFF) << 8
                | (image[index + 0] & 0xFF);
        index += 4;

        bitmap.biXPelsPerMeter =
                // ByteBuffer.wrap(image, index, index +
                // 4).order(ByteOrder.LITTLE_ENDIAN).getLong();
                (int) (image[index + 3] & 0xff) << 24 | (image[index + 2] & 0xFF) << 16 | (image[index + 1] & 0xFF) << 8 | (image[index + 0] & 0xFF);
        index += 4;

        bitmap.biYPelsPerMeter =
                //ByteBuffer.wrap(image, index, index + 4).order(ByteOrder.LITTLE_ENDIAN).getLong();
                (int) (image[index + 3] & 0xff) << 24 | (image[index + 2] & 0xFF) << 16 | (image[index + 1] & 0xFF) << 8 | (image[index + 0] & 0xFF);
        index += 4;

        bitmap.biClrUsed = (image[index + 3] & 0xff) << 24 | (image[index + 2] & 0xFF) << 16 | (image[index + 1] & 0xFF) << 8
                | (image[index + 0] & 0xFF);
        index += 4;

        bitmap.biClrImportant = (image[index + 3] & 0xff) << 24 | (image[index + 2] & 0xFF) << 16 | (image[index + 1] & 0xFF) << 8
                | (image[index + 0] & 0xFF);
        index += 4;

        index += 10;
        return bitmap;

    }

}
