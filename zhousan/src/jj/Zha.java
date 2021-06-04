package jj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class Zha extends Thread {

    @Override
    public void run() {
        //发送 POST 请求
        String url = "http://piao.cmbchina.com/yummy-portal/JSONServer/execute.do?";
        //body={payType='', mobilePhone='', cardType='1100', bakNo='991904110009900003', isCanRush='0', moduleType='1', productNo='9919041100099', accountNum=1}&syshead={chnlUserId='ae834428ed844278b80d58b000e6ca32', trans_code='SI_ORD0016', timestamp='1559058256134', sign='99D78A15560903A82CA167B47731EFE9', sessionId='c39cbd6424fc40b4b706e589e679204e', chnlId='01'}&p0=a&p1=91&p10=45525be617e146c58f589c89cfd7f262&p7=8b781c6dab28491483ffa0628152ac91&p6=586009985&groupFlag=0&p9=c39cbd6424fc40b4b706e589e679204e&p3=73ca23b06247439a94f62458b2b9fb16c&p8=a52cc6d6726b439582f6f995529b282b&p4=2525e209bf11497c95c325836ccff6db&p5=ae834428ed844278b80d58b000e6ca32&p2=tencent
        //body={payType='', mobilePhone='', cardType='1100', bakNo='991901240143200008', isCanRush='0', moduleType='1', productNo='9919012401432', accountNum=1}&syshead={chnlUserId='ae834428ed844278b80d58b000e6ca32', trans_code='SI_ORD0016', timestamp='1555465754529', sign='66D29B224EEDBA4E33DFBEDB2C3FE47C', sessionId='d61f5be477664508bfaf087995b1af98', chnlId='01'}&p0=a&p1=87&p10=778fd7bda31f4b3fad3589c17a774bdf&p7=8b781c6dab28491483ffa0628152ac91&p6=582417483&groupFlag=0&p9=d61f5be477664508bfaf087995b1af98&p3=73ca23b06247439a94f62458b2b9fb16c&p8=b58a60bc0b7c4a2096260640a617f772&p4=d05da2730b104110a297c2ebb86be17d&p5=ae834428ed844278b80d58b000e6ca32&p2=aliyun
        String body = "body={payType='', mobilePhone='', cardType='1100', bakNo='991904110009900003', isCanRush='0', moduleType='1', productNo='9919041100099', accountNum=1}&syshead={chnlUserId='85a214f566a0459b949fb78621c376a6', trans_code='SI_ORD0016', timestamp='1554225810567', sign='2F61DCC5CA2E29D71DC85F64C58534B2', sessionId='61a9cc6818a341168ca091c8bf36acf5', chnlId='01'}&p0=a&p1=87&p10=1d82c74563524c83b3aa3520ccb00291&p7=11499b145de14c0e88d9618d995322ca&p6=581177539&groupFlag=0&p9=61a9cc6818a341168ca091c8bf36acf5&p3=73ca23b06247439a94f62458b2b9fb16c&p8=23fcf2d59d2d4e0ba0da014196de8a79&p4=10785331f7bd42fb9ac4215d80d93594&p5=85a214f566a0459b949fb78621c376a6&p2=aliyun";
        int do_times = 1;
        int do_max_times = 999999;
        while (true) {
            if (do_times >= do_max_times) {
                break;
            }
            try {
                String sr = Zha.sendPost(url, body);
                System.out.println("jj:Zha-"+do_times + "\t" + sr);
                //if (sr.indexOf("\"orderNo\"") != -1 || sr.indexOf("还能购买") != -1 || sr.indexOf("超出") != -1) {
                //break;
                //}
            } catch (Exception e) {
                System.out.println(e);
            }
            do_times++;
        }
    }
    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) throws Exception {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Dalvik/2.1.0 (Linux; U; Android 6.0; MI 5 MIUI/V7.3.3.0.MAACNDD);(cmblife 5.3.2/46)");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=utf-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}
