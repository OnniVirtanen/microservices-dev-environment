package com.virtanen.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroFactory;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import com.fasterxml.jackson.dataformat.avro.schema.AvroSchemaGenerator;
import org.apache.avro.Schema;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Convert Java classes to Avro schema (.avsc)
 */
public class App {
    public static void main(String[] args) {
        String packageName = "com.virtanen.event.events";
        List<Class<?>> classes = getClassesInPackage(packageName);

        // Ensure the target directory exists
        String outputDir = "src/main/java/com/virtanen/event/schemas/";
        File dir = new File(outputDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (Class<?> clazz : classes) {
            if (!Modifier.isAbstract(clazz.getModifiers())) {
                String fname = clazz.getSimpleName().toLowerCase();
                try (Writer bw = Files.newBufferedWriter(Paths.get(outputDir, fname + ".avsc"))) {
                    generateSchema(clazz, bw);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("Schema generation completed!");
    }

    public static <T> void generateSchema(Class<T> clazz, Writer writer) {
        ObjectMapper mapper = new ObjectMapper(new AvroFactory());
        AvroSchemaGenerator generator = new AvroSchemaGenerator();
        generator.enableLogicalTypes();
        try {
            mapper.acceptJsonFormatVisitor(clazz, generator);
            AvroSchema generatedSchema = generator.getGeneratedSchema();
            Schema avroSchema = generatedSchema.getAvroSchema();
            writer.write(avroSchema.toString(true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Class<?>> getClassesInPackage(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        String path = packageName.replace('.', '/');
        try {
            File directory = new File(Thread.currentThread().getContextClassLoader().getResource(path).getFile());
            for (File file : directory.listFiles()) {
                if (file.getName().endsWith(".class")) {
                    String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
                    classes.add(Class.forName(className));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to get classes in package: " + packageName, e);
        }
        return classes;
    }
}
