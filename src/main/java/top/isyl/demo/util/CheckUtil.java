package top.isyl.demo.util;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 校验微信工具类
 * @author Ylong
 *
 */
public class CheckUtil {

	//Token
	private static final String token = "isyl";
	
	/**
	 * 校验Signature
	 * @param signature 微信签名
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @return
	 */
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		String[] arr = new String[]{token,timestamp,nonce};
		//排序
		Arrays.sort(arr);
		//生成字符串
		StringBuilder sb = new StringBuilder();
		for (String string : arr) {
			sb.append(string);
		}
		//sha1加密
		String result = getSha1(sb.toString());
		
		return result.equals(signature);
	}
	
	/**
	 * sha1加密
	 * @param str
	 * @return
	 */
	public static String getSha1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];      
            }
            return new String(buf);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

}
