import com.github.jaiimageio.impl.plugins.tiff.TIFFImageWriterSpi;
import oracle.sql.CLOB;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
/**
 * @version 1.0
 * @Classname CT
 * @Date 1397/08/10 -- 2018/11/06
 * @CopyRight GNU General Public License v3.0
 * @Author : Mahdi Ghaffari
 * @Email mahdi.ghaffari1994@gmail.com
 * This class compiles an TIFF to JPEG and vice versa!
 */
public class CT {

    /**
     *
     * @param tiff as CLOB, encoded in base64, TIFF images as input received.
     * @return CLOB of base64 of jpeg image
     * @throws IOException internal Error may happen!
     */
    public static oracle.sql.CLOB TJ(CLOB tiff ) throws IOException, SQLException {

        byte[] bytes = new BASE64Decoder().decodeBuffer(tiff.getSubString(1, (int) tiff.length()));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        final BufferedImage tif = ImageIO.read(byteArrayInputStream);
        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        ImageIO.write(tif, "jpeg", fos);
        tiff.setString(1,new String(new BASE64Encoder().encode(fos.toByteArray())));
        return  tiff;
    }
    /**
     *
     * @param jpeg as in CLOB , encoded in base64, and formatted as JPEG image
     * @return BASE64 of TIFF image in CLOB!
     * @throws IOException: for internal affair's!
     */
    public static oracle.sql.CLOB JT(CLOB jpeg ) throws IOException, SQLException {

        byte[] bytes = new BASE64Decoder().decodeBuffer(jpeg.getSubString(1L, (int)jpeg.length()));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        BufferedImage tif = ImageIO.read(byteArrayInputStream);
        TIFFImageWriterSpi tiffspi = new TIFFImageWriterSpi();
        ImageWriter writer = tiffspi.createWriterInstance();
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionType("LZW");
        param.setCompressionQuality(1.0f);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MemoryCacheImageOutputStream memoryCacheImageOutputStream = new MemoryCacheImageOutputStream(baos);
        writer.setOutput(memoryCacheImageOutputStream);
        writer.write(null, new IIOImage(tif, null, null), param);
        ByteArrayInputStream bai = new ByteArrayInputStream(baos.toByteArray());
        memoryCacheImageOutputStream.flush();
        jpeg.setString(1L, new BASE64Encoder().encode(baos.toByteArray()));
        return jpeg;
    }

    public static String JT2(Clob jpeg) throws IOException, SQLException {

        byte[] bytes = new BASE64Decoder().decodeBuffer(jpeg.getSubString(1, (int) jpeg.length()));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        final BufferedImage tif = ImageIO.read(byteArrayInputStream);
        TIFFImageWriterSpi tiffspi = new TIFFImageWriterSpi();
        ImageWriter writer = tiffspi.createWriterInstance();
        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionType("LZW");
        param.setCompressionQuality(0.0f);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        MemoryCacheImageOutputStream memoryCacheImageOutputStream = new MemoryCacheImageOutputStream(baos);
        writer.setOutput(memoryCacheImageOutputStream);
        writer.write(null, new IIOImage(tif, null, null), param);
        memoryCacheImageOutputStream.flush();
        System.out.println(new BASE64Encoder().encode(baos.toByteArray()));
//        FileUtils.writeByteArrayToFile(new File("/Users/mac/Developer/temp/out.tif"), new BASE64Decoder().decodeBuffer(new BASE64Encoder().encode(baos.toByteArray())));
//        jpeg.setString(1, new BASE64Encoder().encode(baos.toByteArray()));
        return new BASE64Encoder().encode(baos.toByteArray());
    }
}

