/*
*- @TestCaseID:jdk17/FastSerializer/StringSerialize
*- @TestCaseName:jdk17_StringSerialize
*- @TestCaseType:Function test
*- @RequirementID:AR.SR.IREQ02478866.001.001
*- @RequirementName:FastSeralizer 功能实现
*- @Condition:UseFastSerializer
*- @Brief:
*   -#step1 将对象写入数据流
*   -#step2 从数据流中读取对象
*- @Expect: 读取对象与写入对象相同
*- @Priority:Level 1
*/

/*
* @test
* @summary test serialize and deserialize of String object
* @run main/othervm -XX:+UnlockExperimentalVMOptions -XX:+UseFastSerializer -DfastSerializerEscapeMode=true -DprintFastSerializer=true StringSerialize
*/


import java.io.*;


public class StringSerialize {
    public static void main(String[] args) throws Exception{
		String configFile = System.getProperty("test.src") + "/../logging.properties";
		System.setProperty("java.util.logging.config.file",configFile);
        ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream("Foo1.ser"));
        String sin1 = "Hello World1";
		String sin2 = "Hello World2";
		String sin3 = "Hello World3";
		String sin4 = "Hello World4";
        oout.writeObject(sin1);
		oout.writeObject(sin2);
		oout.writeObject(sin3);
		oout.writeObject(sin4);
        oout.close();
        ObjectInputStream oin = new ObjectInputStream(new FileInputStream("Foo1.ser"));
        String sout1 = (String)oin.readObject();
		String sout2 = (String)oin.readObject();
		String sout3 = (String)oin.readObject();
		String sout4 = (String)oin.readObject();
        oin.close();
		if(!sout1.equals(sin1) || !sout2.equals(sin2) ||!sout3.equals(sin3) ||!sout4.equals(sin4)){
			throw new Exception("deserialized string different");
		} 
		ObjectInputStream oin2 = new ObjectInputStream(new FileInputStream("Foo1.ser"));
        String sout5 = (String)oin2.readObject();
		String sout6 = (String)oin2.readObject();
		String sout7 = (String)oin2.readObject();
		String sout8 = (String)oin2.readObject();
		if(!sout5.equals(sin1) || !sout6.equals(sin2) ||!sout7.equals(sin3) ||!sout8.equals(sin4)){
			throw new Exception("deserialized string different");
		} 
    }
}