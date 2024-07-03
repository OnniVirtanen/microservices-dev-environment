package com.virtanen.event;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("EventConsumer")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class EventConsumerProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(EventConsumer.class)) {
            if (annotatedElement.getKind() != ElementKind.CLASS) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Only classes can be annotated with @EventConsumer");
                return true;
            }

            TypeElement typeElement = (TypeElement) annotatedElement;
            boolean hasConsumeMethod = false;

            for (Element enclosedElement : typeElement.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.METHOD) {
                    ExecutableElement method = (ExecutableElement) enclosedElement;
                    if (method.getSimpleName().toString().equals("consume")
                            && method.getParameters().size() == 1) {
                        hasConsumeMethod = true;
                        break;
                    }
                }
            }

            if (!hasConsumeMethod) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                        "The class " + typeElement.getQualifiedName() + " must implement a consume method with one parameter.");
            }
        }
        return true;
    }
}