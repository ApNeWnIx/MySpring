package entity;

import lombok.Data;

import java.util.List;

@Data
public class BeanMetaData<T> {

    private String name;

    private T type;

    private String alias;

    private Object instance;

    private List<BeanMetaData> containBeansList;
}
