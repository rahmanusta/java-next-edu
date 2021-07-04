package com.kodedu.linker;

import jdk.incubator.foreign.*;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static jdk.incubator.foreign.CLinker.C_INT;
import static jdk.incubator.foreign.CLinker.C_POINTER;
/*
    --enable-native-access=java17edu
 */
public class RenameApp {

    public static void main(String[] args) throws Throwable {
        MethodHandle rename = CLinker.getInstance()
                .downcallHandle(
                        CLinker.systemLookup().lookup("rename").get(),
                        MethodType.methodType(int.class, MemoryAddress.class, MemoryAddress.class),
                        FunctionDescriptor.of(C_INT, C_POINTER, C_POINTER)
                );

        MemoryAddress file1 = CLinker.toCString("file.txt", ResourceScope.newConfinedScope()).address();
        MemoryAddress file2 = CLinker.toCString("file_renamed.txt", ResourceScope.newConfinedScope()).address();
        Object invoke = rename.invoke(file1, file2);
        System.out.println(invoke);
    }
}
