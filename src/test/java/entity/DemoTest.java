package entity;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class DemoTest {
    @Test
    public void testAYesBYes() throws InvocationTargetException, IllegalAccessException {
        FirstObject firstObject = new FirstObject();
        firstObject.setName("first");
        firstObject.setAge(1);
        SecondObject secondObject = new SecondObject();
        secondObject.setName("second");
        secondObject.setAge(2);
        BeanUtils.copyProperties(secondObject, firstObject);
        System.out.println(secondObject);
    }

    @Test
    public void testAYesBNo() throws InvocationTargetException, IllegalAccessException {
        FirstObject firstObject = new FirstObject();
        firstObject.setName("first");
        firstObject.setAge(1);
        SecondObject secondObject = new SecondObject();
        BeanUtils.copyProperties(secondObject, firstObject);
        System.out.println(secondObject);
    }

    @Test
    public void testANoBYes() throws InvocationTargetException, IllegalAccessException {
        FirstObject firstObject = new FirstObject();
        //firstObject.setName("first");
        //firstObject.setAge(1);
        SecondObject secondObject = new SecondObject();
        secondObject.setName("second");
        secondObject.setAge(2);
        CopyOptions copyOptions = CopyOptions.create().setIgnoreNullValue(true).setIgnoreError(true);
        BeanUtil.copyProperties(firstObject, secondObject, copyOptions);
        System.out.println(secondObject);
    }
}
