package com.realize.eroute_process;

import com.google.auto.service.AutoService;

import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import com.realize.router_note.EAction;

/**
 * Created by SongWenjun
 * 2021/10/1
 * ∩ _ ∩
 * (??ω??)っ一? ?? ???
 * っ 丿         ? ????
 * 乚― J               ???
 * This class is ...
 */
@AutoService(Processor.class)
public class RouterProcesss extends AbstractProcessor {

    private Filer filer;
    private Messager messager;
    private Elements elementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
        elementUtils = processingEnv.getElementUtils();
        messager.printMessage(Diagnostic.Kind.NOTE, "start init");
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new TreeSet<>();
        types.add(EAction.class.getCanonicalName());
        messager.printMessage(Diagnostic.Kind.NOTE, "start getSupportedAnnotationTypes");
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        messager.printMessage(Diagnostic.Kind.NOTE, "start getSupportedSourceVersion");
        return SourceVersion.latest();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (TypeElement type : set) {
            messager.printMessage(Diagnostic.Kind.NOTE, "start process");
            Set<? extends Element> annotatedWith = roundEnvironment.getElementsAnnotatedWith(EAction.class);
            Map<String, String> map = new HashMap<>();
            for (Element element : annotatedWith) {
                if (element instanceof TypeElement) {
                    TypeElement typeElement = (TypeElement) element;
                    EAction annotation = typeElement.getAnnotation(EAction.class);
                    if (annotation != null) {
                        String key = annotation.path();
                        String value = typeElement.getQualifiedName().toString();
                        map.put(key, value + ".class");
                    }
                }
            }

            StringBuffer sb = new StringBuffer();
            String clazzName = "ActionRoureManager";
            sb.append("package router.easy.com;\n\n")
                    .append("import com.realize.routeeasy.IRouter;\n\n")
                    .append("import java.util.Map;\n\n")
                    .append("public class " + clazzName + " implements IRouter{\n")
                    .append("\t@Override\n")
                    .append("\tpublic void put(Map<String,Class<?>> map) {\n");
            for (String key : map.keySet()) {
                sb.append("\t\tmap.put(\"" + key + "\"," + map.get(key) + ");\n");
            }
            sb.append("\t}\n");
            sb.append("}");
            Writer writer = null;
            try {
                JavaFileObject sourceFile = filer.createSourceFile(clazzName);
                writer = sourceFile.openWriter();
                writer.write(sb.toString());
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
                messager.printMessage(Diagnostic.Kind.NOTE, "catch funcation");
            }
        }
        return false;
    }
}
