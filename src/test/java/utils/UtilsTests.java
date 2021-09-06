package utils;

import com.github.crab2died.exceptions.Excel4JException;
import com.github.crab2died.utils.Utils;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class UtilsTests {

    @Test
    public void getterAndSetter() {

        try {
            Method aField = Utils.getterOrSetter(TestBean.class, "AField", Utils.FieldAccessType.GETTER);
            System.out.println(aField.getName());
            Method aField1 = Utils.getterOrSetter(TestBean.class, "AField", Utils.FieldAccessType.SETTER);
            System.out.println(aField1.getName());

            Utils.getterOrSetter(TestBean.class, "BFiled", Utils.FieldAccessType.GETTER);
            Utils.getterOrSetter(TestBean.class, "BFiled", Utils.FieldAccessType.SETTER);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bigDecimalTest() {
        BigDecimal bigDecimal = new BigDecimal("12989E19");
        System.out.println(bigDecimal);
    }

    @Test
    public void copyPropTest() {
        TestBean bean = new TestBean();
        try {
            Utils.copyProperty(bean,"AField",20);
            Utils.copyProperty(bean,"BFiled","张三");
        } catch (Excel4JException e) {
            e.printStackTrace();
        }
        System.out.println(bean.getAField());
        System.out.println(bean.getBFiled());
    }
}
