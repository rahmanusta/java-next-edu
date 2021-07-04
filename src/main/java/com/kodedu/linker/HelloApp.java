package com.kodedu.linker;

import jdk.incubator.foreign.*;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.nio.file.Path;
import java.nio.file.Paths;

import static jdk.incubator.foreign.CLinker.C_INT;
import static jdk.incubator.foreign.CLinker.C_POINTER;
/*
    --enable-native-access=java17edu
 */
public class HelloApp {

    public static void main(String[] args) throws Throwable {

        /*
         The 'nativecode' folder exist in the project,
         Change the full path as in your system, and
         Compile hello.c before run this class
          */
        Path path = Paths.get("/Users/usta/projects/java16-edu/nativecode/hello");
        System.load(path.toString());
        SymbolLookup helloLibrary = SymbolLookup.loaderLookup();

        MethodHandle hello = CLinker.getInstance()
                .downcallHandle(
                        helloLibrary.lookup("print").get(),
                        MethodType.methodType(int.class, MemoryAddress.class),
                        FunctionDescriptor.of(C_INT, C_POINTER)
                );

        final MemoryAddress address = CLinker.toCString("Istanbul JUG", ResourceScope.newConfinedScope()).address();
        hello.invoke(address);
    }
}
