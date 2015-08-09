package utilities;

public class SecurityCode {  
    
    /**
     * �������Ⱥ��Ѷ��������֤��
     * @param length  ����
     * @return  String ��֤��
     */
    public static String getSecurityCode(int length){
        
        //�����ȡlen���ַ�
        int len=length;
        
        //�ַ�����(��ȥ�׻���������0������1����ĸl����ĸo����ĸO)
        char[] codes={'1','2','3','4','5','6','7','8','9',
                      'a','b','c','d','e','f','g','h','i',
                      'j','k','m','n','p','q','r','s','t',
                      'u','v','w','x','y','z','A','B','C',
                      'D','E','F','G','H','I','J','K','L',
                      'M','N','P','Q','R','S','T','U','V',
                      'W','X','Y','Z'};
        
        //�ַ����ϳ���
        int n=codes.length;
        
        //��ų�ȡ�������ַ�
        char[] result=new char[len];
        
       for(int i=0;i<result.length;i++){
    	   result[i]=codes[(int)(Math.random()*n)];
       }
       
       return String.valueOf(result);
    }
}
