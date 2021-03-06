package com.huatek.file.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;


@SuppressWarnings("restriction")
public class ImgCompress {  
    private Image img;  
    private int width;  
    private int height;  
    
    public static void main(String[] args) throws Exception {  
    	File file = new File("D:/show-image/busi/20161020/金佰利登录1.jpg");
        ImgCompress imgCom = new ImgCompress(file);  
        imgCom.resizeFix(800, 600,"D:/show-image/busi/20161020/456.jpg");  
    }  
    
    /** 
     * 构造函数 
     */  
//    public ImgCompress(String fileName) throws IOException {  
//        File file = new File(fileName);// 读入文件  
//        img = ImageIO.read(file);      // 构造Image对象  
//        width = img.getWidth(null);    // 得到源图宽  
//        height = img.getHeight(null);  // 得到源图长  
//    }  
    
    
    
    /** 
     * 构造函数 
     */  
    public ImgCompress(File file) throws IOException {  
        img = ImageIO.read(file);      // 构造Image对象  
        width = img.getWidth(null);    // 得到源图宽  
        height = img.getHeight(null);  // 得到源图长  
    } 
    /** 
     * 按照宽度还是高度进行压缩 
     * @param w int 最大宽度 
     * @param h int 最大高度 
     */  
    public void resizeFix(int w, int h,String filePath) throws IOException {  
        if (width / height > w / h) {  
            resizeByWidth(w, filePath);  
        } else {  
            resizeByHeight(h, filePath);  
        }  
    }  
    /** 
     * 以宽度为基准，等比例放缩图片 
     * @param w int 新宽度 
     */  
    public void resizeByWidth(int w,String filePath) throws IOException {  
        int h = (int) (height * w / width);  
        resize(w, h ,filePath);  
    }  
    /** 
     * 以高度为基准，等比例缩放图片 
     * @param h int 新高度 
     */  
    public void resizeByHeight(int h,String filePath) throws IOException {  
        int w = (int) (width * h / height);  
        resize(w, h, filePath);  
    }  
    /** 
     * 强制压缩/放大图片到固定的大小 
     * @param w int 新宽度 
     * @param h int 新高度 
     */  
	public void resize(int w, int h,String filePath) throws IOException {  
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢  
        BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );   
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图  
        File destFile = new File(filePath);  
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流  
        // 可以正常实现bmp、png、gif转jpg  
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
        encoder.encode(image); // JPEG编码  
        out.close();  
    }  
}  