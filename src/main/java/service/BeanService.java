package service;

import annotation.MyComponent;
import entity.BeanMetaData;

import java.util.List;
import java.util.Optional;

public class BeanService {

    private final String ANNOTATION_SERVICE_NAME = "annotationService";

    private final Class ANNOTATION_SERVICE_CLAZZ = AnnotationService.class;

    private final String INSTANCE_SERVICE_NAME = "instanceService";

    private final Class INSTANCE_SERVICE_CLAZZ = InstanceService.class;

    public void create() {
        // find annotation
        BeanMetaData annotationBeanMetaData = InstanceService.nameMap.getOrDefault(ANNOTATION_SERVICE_NAME, InstanceService.typeMap.getOrDefault(ANNOTATION_SERVICE_CLAZZ, null));
        if (annotationBeanMetaData == null) {
            return ;
        }
        AnnotationService annotationService = (AnnotationService) annotationBeanMetaData.getInstance();
        Optional<List<Class>> optional = annotationService.findRootClass();
        if (!optional.isPresent()) {
            return ;
        }

        List<Class> classList = optional.get();

        // init
        BeanMetaData instanceBeanMetaData = InstanceService.nameMap.getOrDefault(INSTANCE_SERVICE_NAME, InstanceService.typeMap.getOrDefault(INSTANCE_SERVICE_CLAZZ, null));
        InstanceService instanceService = (InstanceService) instanceBeanMetaData.getInstance();
        for (Class c : classList) {
            try {
                instanceService.init(c);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
