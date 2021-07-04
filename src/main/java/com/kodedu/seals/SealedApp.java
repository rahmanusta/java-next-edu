package com.kodedu.seals;

import com.kodedu.seals.products.*;

public class SealedApp {

    public static void main(String[] args) {

        // y = x * 2 + 1

        var x = 3;
        int y = calculate(
                new PlusExpr(
                        new TimesExpr(
                                new ConstantExpr(x),
                                new ConstantExpr(2)
                        ),
                        new ConstantExpr(1)
                )
        );
        System.out.println("Value of y: " + y);

    }

    private static int calculate(Expr expr) {
        return switch (expr){
            case ConstantExpr e -> e.value();
            case PlusExpr e -> calculate(e.first()) + calculate(e.second());
            case TimesExpr e -> calculate(e.first()) * calculate(e.second());
            case NegExpr e -> -calculate(e.expr());
        };
    }
}
