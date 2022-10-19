package service;

import assembler.BeanMetaDataAssembler;
import entity.BeanMetaData;
import utils.ClassUtil;

import java.util.Map;

public class InstanceService {

    public static Map<String, BeanMetaData> nameMap;

    public static Map<Class, BeanMetaData> typeMap;

    <T> void init(T t) throws ClassNotFoundException {
        // if not be instanced, instance it.
        if (!typeMap.containsKey(t.getClass())) {
            Class<?> clazz = Class.forName(t.getClass().getName());
            BeanMetaData beanMetaData = BeanMetaDataAssembler.assemble();
            typeMap.put(clazz, beanMetaData);
            nameMap.put(ClassUtil.getClassDefiniteName(t.getClass().getSimpleName()), beanMetaData);
        }
    }
}