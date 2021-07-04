package com.kodedu.linker;

import jdk.incubator.foreign.*;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static jdk.incubator.foreign.CLinker.*;
/*
    --enable-native-access=java17edu
 */
public class FileWriteApp {

    public static void main(String[] args) throws Throwable {

        MethodHandle fopen = CLinker.getInstance()
                .downcallHandle(
                        CLinker.systemLookup().lookup("fopen").get(),
                        MethodType.methodType(MemoryAddress.class, MemoryAddress.class, MemoryAddress.class),
                        FunctionDescriptor.of(C_POINTER, C_POINTER, C_POINTER)
                );

        MemoryAddress file = CLinker.toCString("file2.txt", ResourceScope.newConfinedScope()).address();
        MemoryAddress access = CLinker.toCString("w", ResourceScope.newConfinedScope()).address();
        MemoryAddress openedFile = (MemoryAddress) fopen.invoke(file, access);

        MethodHandle fwrite = CLinker.getInstance()
                .downcallHandle(
                        CLinker.systemLookup().lookup("fwrite").get(),
                        MethodType.methodType(long.class, MemoryAddress.class, int.class, int.class, MemoryAddress.class),
                        FunctionDescriptor.of(C_LONG, C_POINTER, C_INT, C_INT, C_POINTER)
                );

        String helloWorld = "Hello World";
        Long result = (Long) fwrite.invoke(CLinker.toCString(helloWorld, ResourceScope.newConfinedScope()).address(), 1, helloWorld.length(), openedFile);

        System.out.println(result);
    }
}
