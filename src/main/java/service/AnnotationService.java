package service;

import annotation.MyComponent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class AnnotationService {

    private String packageName = "com.news";

    Optional<List<Class>> findRootClass() {
        // get all classes from package
        Set<Class> classSet = findAllClassesUsingClassLoader(packageName);

        // get annotation of each class
        List<Class> classList = new ArrayList<>();
        for (Class clazz : classSet) {
            if (clazz.getAnnotation(MyComponent.class) != null) {
                classList.add(clazz);
            }
        }

        return Optional.of(classList);
    }

    public Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
