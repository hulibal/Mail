package utilities;

public class SecurityCode {  
    
    /**
     * 产生长度和难度任意的验证码
     * @param length  长度
     * @return  String 验证码
     */
    public static String getSecurityCode(int length){
        
        //随机抽取len个字符
        int len=length;
        
        //字符集合(除去易混淆的数字0、数字1、字母l、字母o、字母O)
        char[] codes={'1','2','3','4','5','6','7','8','9',
                      'a','b','c','d','e','f','g','h','i',
                      'j','k','m','n','p','q','r','s','t',
                      'u','v','w','x','y','z','A','B','C',
                      'D','E','F','G','H','I','J','K','L',
                      'M','N','P','Q','R','S','T','U','V',
                      'W','X','Y','Z'};
        
        //字符集合长度
        int n=codes.length;
        
        //存放抽取出来的字符
        char[] result=new char[len];
        
       for(int i=0;i<result.length;i++){
    	   result[i]=codes[(int)(Math.random()*n)];
       }
       
       return String.valueOf(result);
    }
}
