import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private CloseableHttpClient client = HttpClients.createDefault();
    private HttpGet httpGet = new HttpGet("https://yandex.ru/maps");
    private CloseableHttpResponse response = client.execute(httpGet);
    private String csrfToken;


    public Main() throws IOException {
    }


    public String getToken() {

        try {
            httpGet.setHeader("Cookie", "maps_los=0; yandexuid=1086007521541671953; i=jlwVg8fbaUAcmYHi1lQB1x3/BiJmMXkZqdp/609VMZEJMIUUZNBAuzuYk+icdciGgB6/VZIezD2TS87awWbCxJkhXD8=; _ym_uid=1541756039280706898; mda=0; my=YwA=; yandex_gid=44; zm=m-white_bender.webp.css-https%3Awww_zmn5fRGIkJxyNVNvb1YAxfO-1Zw%3Al; fuid01=5c0e5e12415b2171.VjyU2nnhbSoU_QTLClpdaIe-eKX_KOoCViVKWxCTAmO0e153TmIWsmxdc9YF6PlDoeIH4dnP19fivedrkfWVAsaBc8Du6drXABT3POdyTHJP2b9WaWu4eidm0gpSlWI6; yc=1544704662.zen.cach%3A1544449058; _ym_d=1544446000; yabs-frequency=/4/0000000000000000/x5ImS0GpOEfKi704Cs40/; _ym_isad=2; device_id=\"bff6967dcd939252bd9b9c15d28eaf60749bffafa\"; yp=1857031953.yrts.1541671953#1857031953.yrtsi.1541671953#1560285510.szm.1:1920x1080:963x969#1547037458.ygu.1#1545655058.ysl.1#1547124410.csc.1");
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String inputLine = "";
            StringBuffer result = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
                result.append("\n");
                System.out.println(inputLine);
            }
            Pattern p = Pattern.compile("csrfToken\":\"[^\"]++");
            Matcher m = p.matcher(result);
            while (m.find()){
                csrfToken=m.group();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return csrfToken;
    }
//    public String getYandexuid() {
//
//    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        String csrfToken = main.getToken();
//        String getYandexuid = main.getYandexuid();
//        System.out.println(getYandexuid);
        System.out.println(csrfToken);


    }
}
