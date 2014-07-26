package com.bizbuzz.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class SaveAllSizedImagesThread implements Runnable{
  private Thread t;
  private String imageId;//and threadName
  byte[] bytes;


  SaveAllSizedImagesThread(String imageId, byte[] bytes){
    this.imageId = imageId;
    this.bytes = bytes;
  }

  @Override
  public void run() {
    // TODO Auto-generated method stub
    saveImage(bytes, 600, 800, "600");
    BufferedImage buffImage = createImageFromBytes(bytes);
    processImage(buffImage, 360);
    processImage(buffImage, 30);
  }
  
  private void processImage(BufferedImage buffImage, int width){
    int height = (width * 800)/600;
    BufferedImage newImage = resize(buffImage, width, height);
    byte[] byteImage = createByteStreamFromImage(newImage);
    saveImage(byteImage, width, height, new Integer(width).toString());
  }
  
  private BufferedImage resize(BufferedImage image, int width, int height){
    return Scalr.resize(image, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH, width, height);
  }
  
  private byte[] createByteStreamFromImage(BufferedImage image){
    //byte[] array = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    try {
      ImageIO.write(image, "jpg", baos);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      baos.flush();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    byte[] imageinBytes = baos.toByteArray();
    try {
      baos.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return imageinBytes;
  }

  private BufferedImage createImageFromBytes(byte[] imageData) {
    ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
    try {
      return ImageIO.read(bais);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  private void saveImage(byte[] imageBytes, int width,int height,  String middleDir){
    HelperFunctions.writeImage(imageId, imageBytes, getClass().getResourceAsStream("/application/AppConstants.xml"), middleDir);
  }

  public void start(){
    if(t==null){
      t = new Thread(this, imageId);
      t.start();
    }
  }

}
