package com.kodedu.linker;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.ResourceScope;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static jdk.incubator.foreign.CLinker.C_LONG;
import static jdk.incubator.foreign.CLinker.C_POINTER;

/*
    --enable-native-access=java17edu
 */
public class StringLength {

    public static void main(String[] args) throws Throwable {

        MethodHandle strlen = CLinker.getInstance().downcallHandle(
                CLinker.systemLookup().lookup("strlen").get(),
                MethodType.methodType(long.class, MemoryAddress.class),
                FunctionDescriptor.of(C_LONG, C_POINTER)
        );

        MemoryAddress helloWorldAddress = CLinker.toCString("Hello World!", ResourceScope.newConfinedScope()).address();
        Object text = strlen.invoke(helloWorldAddress);
        System.out.println("Length: " + text);
    }
}
