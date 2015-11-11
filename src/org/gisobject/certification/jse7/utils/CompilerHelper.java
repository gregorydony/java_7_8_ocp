package org.gisobject.certification.jse7.utils;

import org.abstractmeta.toolbox.compilation.compiler.JavaSourceCompiler;
import org.abstractmeta.toolbox.compilation.compiler.impl.JavaSourceCompilerImpl;

/**
 * Created by Gregory on 14/03/2015.
 */
public class CompilerHelper {

    public static void compile(String expression) throws IllegalStateException, ClassNotFoundException {
        JavaSourceCompiler javaSourceCompiler = new JavaSourceCompilerImpl();
        JavaSourceCompiler.CompilationUnit compilationUnit = javaSourceCompiler.createCompilationUnit();
        String javaSourceCode =  "package org.gisobject.test;\n" +
                "public class Test {\n" +
               expression +
                "}";
        compilationUnit.addJavaSource("org.gisobject.test.Test", javaSourceCode);
        ClassLoader classLoader = javaSourceCompiler.compile(compilationUnit);
        classLoader.loadClass("org.gisobject.test.Test");
    }
}
