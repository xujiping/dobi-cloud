package com.cloud.base.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * 图片与base64字符串的编解码
 * com.voice.util
 *
 * @author MENGJUN
 * @date 2017/3/30.
 */
public class Base64Util {

    private static final Logger LOG = LoggerFactory.getLogger(Base64Util.class);

    /**
     * 图片编码成base64字符串
     *
     * @param filePath 图片的地址
     * @return base64图片的字符串
     */
    public static String generateImageBase64(String filePath) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(filePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * 将base64的图片字符串解码成图片文件
     *
     * @param imgBase64 图片base64的字符串
     * @param filePath  生成的文件位置
     * @return 文件是否生成成功
     */
    public static Boolean decodeBase64ToImage(String imgBase64, String filePath) {
        if (StringUtils.isEmpty(imgBase64)) {
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] bytes = decoder.decodeBuffer(imgBase64);
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            OutputStream os = new FileOutputStream(filePath);
            os.write(bytes);
            os.flush();
            os.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将base64的图片字符串解码成图片文件
     *
     * @param imgBase64 图片base64的字符串
     * @param filePath  生成的文件位置
     * @return 文件是否生成成功
     */
    public static Boolean decodeBase64ToImageFromAndroid(String imgBase64, String filePath) {
        if (StringUtils.isEmpty(imgBase64)) {
            return false;
        }
        try {
            byte[] bytes = decodeToByteArray(imgBase64);
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            OutputStream os = new FileOutputStream(filePath);
            os.write(bytes);
            os.flush();
            os.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static InputStream base64ToInputStream(String strBase64) throws IOException {
        // 解码，然后将字节转换为文件
        //将字符串转换为byte数组
        byte[] bytes = new BASE64Decoder().decodeBuffer(strBase64);
        InputStream in = new ByteArrayInputStream(bytes);
        return in;
    }

    /**
     * 判断是不是base64字符串
     *
     * @param str
     * @return
     */
    public static boolean isBase64(String str) {
        if (StringUtil.isEmpty(str)) {
            return false;
        }
        return Base64.isBase64(str.getBytes());
    }

    /**
     * 编码
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encode(String str) {
        String result = "";
        if (StringUtil.isEmpty(str)) {
            return result;
        }
        byte[] bytes = Base64.encodeBase64(str.getBytes());
        try {
            result = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error("Base64编码错误-", e);
        }
        return result;
    }

    /**
     * 解码
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decode(String str) {
        String result = "";
        if (StringUtil.isEmpty(str)) {
            return result;
        }
        byte[] bytes = Base64.decodeBase64(str.getBytes());
        try {
            result = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOG.error("Base64解码错误-", e);
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "test中文";
        String aa = "";
        encode(s);
        System.out.println("编码：" + encode(s));
        System.out.println("解码：" + decode(encode(s)));
        System.out.println("----------------------");
        BASE64Encoder  encoder = new BASE64Encoder();
        String encode = encoder.encode(s.getBytes());
        System.out.println("编码：" + encode);
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] bytes = decoder.decodeBuffer(encode);
            System.out.println("解码：" + new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            byte[] bytes = decodeToByteArray(encode);
            System.out.println("解码：" + new String(bytes));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 解码
     *
     * @param decodeContent 要解码的内容
     * @return Base64解码之后的内容
     */
    public static String decodeAndroid(String decodeContent) {

        byte buf[];
        try {
            buf = decodeToByteArray(decodeContent);
            decodeContent = new String(buf, "UTF-8");
            decodeContent = decodeContent.replaceAll("[\\n|\\r]", "");
        } catch (Exception e) {
            return "";
        }
        return decodeContent;
    }

    /**
     * 解码
     *
     * @param decodeContent 要解码的内容
     * @return Base64解码之后的字节数组
     */
    public static byte[] decodeToByteArray(String decodeContent) throws UnsupportedEncodingException {
        byte hold[];

        if (decodeContent.length() == 0) {
            return null;
        }
        byte buf[] = decodeContent.getBytes("iso-8859-1");
        byte debuf[] = new byte[buf.length * 3 / 4];
        byte tempBuf[] = new byte[4];
        int index = 0;
        int index1 = 0;
        int temp;
        int count = 0;
        int count1 = 0;

        // decode to byte array
        for (int i = 0; i < buf.length; i++) {
            if (buf[i] >= 65 && buf[i] < 91) {
                tempBuf[index++] = (byte) (buf[i] - 65);

            } else if (buf[i] >= 97 && buf[i] < 123) {
                tempBuf[index++] = (byte) (buf[i] - 71);
            } else if (buf[i] >= 48 && buf[i] < 58) {
                tempBuf[index++] = (byte) (buf[i] + 4);
            } else if (buf[i] == '+') {
                tempBuf[index++] = 62;
            } else if (buf[i] == '/') {
                tempBuf[index++] = 63;
            } else if (buf[i] == '=') {
                tempBuf[index++] = 0;
                count1++;
            }

            // Discard line breaks and other nonsignificant characters
            else {
                if (buf[i] == '\n' || buf[i] == '\r' || buf[i] == ' '
                        || buf[i] == '\t') {
                    continue;

                } else {
                    throw new RuntimeException("Illegal character found in encoded string!");
                }
            }
            if (index == 4) {
                temp = ((tempBuf[0] << 18)) | ((tempBuf[1] << 12))
                        | ((tempBuf[2] << 6)) | (tempBuf[3]);
                debuf[index1++] = (byte) (temp >> 16);
                debuf[index1++] = (byte) ((temp >> 8) & 255);
                debuf[index1++] = (byte) (temp & 255);
                count += 3;
                index = 0;
            }
        }
        hold = new byte[count - count1];
        System.arraycopy(debuf, 0, hold, 0, count - count1); // trim to size
        return hold;
    }

}
