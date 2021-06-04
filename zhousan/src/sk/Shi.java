package sk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class Shi extends Thread {

    @Override
    public void run() {
        //发送 POST 请求
        String url = "http://piao.cmbchina.com/yummy-portal/JSONServer/execute.do?";

        String body = "body={payType='', mobilePhone='', cardType='1100', bakNo='991902280426200022', isCanRush='0', moduleType='1', productNo='9919022804262', accountNum=1}&syshead={chnlUserId='ae834428ed844278b80d58b000e6ca32', trans_code='SI_ORD0016', timestamp='1567555915609', sign='6573EBEEE1C88A6455BB248D6D338540', sessionId='ab667ac1de61423bb6e32b6a93707a4e', chnlId='01'}&p0=a&p1=97&p10=f96ed728f2a844df9c67837b95a5505d&p7=8b781c6dab28491483ffa0628152ac91&p6=594507644&groupFlag=0&p9=ab667ac1de61423bb6e32b6a93707a4e&p3=73ca23b06247439a94f62458b2b9fb16c&p8=349da3586c8c40bd94aaa74499d4b1cc&p4=ecf8c8ce42964f9f84c5f9f23ff73838&p5=ae834428ed844278b80d58b000e6ca32&p2=anzhuo";
        int do_times = 1;
        int do_max_times = 999999;
        while (true) {
            if (do_times >= do_max_times) {
                break;
            }
            try {
                String sr = Shi.sendPost(url, body);
                System.out.println("SK:Shi-"+do_times + "\t" + sr);
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
