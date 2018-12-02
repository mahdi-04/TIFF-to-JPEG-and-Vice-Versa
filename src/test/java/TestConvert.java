import oracle.sql.CLOB;
//import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import javax.sql.rowset.serial.SerialClob;
import java.io.File;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;


public class TestConvert {

    private String path ="/Users/mac/Developer/temp";
//
//    @Test
//    public void convert() throws IOException, SQLException {
//        String args[]= new String[2];
//        args[0]= path+"/sample.tif";
//        args[1]= path+"/outjpg.jpg";
//        Clob serialClob = new SerialClob(new BASE64Encoder().encode(FileUtils.readFileToByteArray(new File(args[0]))).toCharArray());
////        CT.TJ( serialClob);
//
//        Clob jpegserial= new SerialClob(new BASE64Encoder().encode(FileUtils.readFileToByteArray(new File(args[1]))).toCharArray());
////        CT.JT(jpegserial);
//
//    }
//    @Test(expected = Exception.class)
//    public void convert2() throws IOException, SQLException {
//
//        String args[]= new String[2];
//        args[0]= null;
//        args[1]= path+"/outjpg.jpg";
//        Clob serialClob = new SerialClob(new BASE64Encoder().encode(FileUtils.readFileToByteArray(new File(args[0]))).toCharArray());
//        CT.TJ((CLOB) serialClob);
//
//        Clob jpegserial= new SerialClob(new BASE64Encoder().encode(FileUtils.readFileToByteArray(new File(args[1]))).toCharArray());
////        CT.JT(jpegserial);
//    }
    @Test
    public void convert3() throws IOException, SQLException {

        String args[]= new String[2];
        args[0]= path+"/tiff_base64.txt";
        args[1] = "/Users/mac/Developer/projects/thirdparty/jpgtotiff/test01.jpeg";
//        Clob serialClob = new SerialClob(new BASE64Encoder().encode(FileUtils.readFileToByteArray(new File(args[0]))).toCharArray());
//        Clob serialClob = new SerialClob(FileUtils.readFileToString(new File(args[0])).toCharArray());
//        CLOB tj = CT.TJ(serialClob);
//        FileUtils.writeByteArrayToFile(new File("/Users/mac/Developer/temp/out.jpeg.txt"), tj.getSubString(0, (int) tj.length()).getBytes());
//        FileUtils.writeByteArrayToFile(new File("/Users/mac/Developer/temp/out.jpeg"), new BASE64Decoder().decodeBuffer((tj.getSubString(0, (int) tj.length()))));

//        byte[] bytes = encodeBase64(FileUtils.readFileToString(new File("/Users/mac/Developer/temp/out.jpeg.txt")).getBytes());
//        Clob jpegserial = new SerialClob(new String(FileUtils.readFileToString(new File("/Users/mac/Developer/temp/out.jpeg.txt")).getBytes()).toCharArray());
        Clob jpegserial = new SerialClob(new String(FileUtils.readFileToString(new File("/Users/mac/Developer/temp/jpgbase64-by-CT.txt")).getBytes()).toCharArray());
        String jt= CT.JT2(jpegserial);
        FileUtils.writeByteArrayToFile(new File("/Users/mac/Developer/temp/out.tif"), new BASE64Decoder().decodeBuffer(jt));
        jpegserial = new SerialClob(new String(FileUtils.readFileToString(new File("/Users/mac/Developer/temp/out.jpeg.txt")).getBytes()).toCharArray());
        jt= CT.JT2(jpegserial);
        FileUtils.writeByteArrayToFile(new File("/Users/mac/Developer/temp/out2.tif"), new BASE64Decoder().decodeBuffer(jt));
    }




}
