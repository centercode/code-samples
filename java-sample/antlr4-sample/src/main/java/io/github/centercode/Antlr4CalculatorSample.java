package io.github.centercode;

import io.github.centercode.sample.CalculatorBaseVisitor;
import io.github.centercode.sample.CalculatorLexer;
import io.github.centercode.sample.CalculatorParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Antlr4CalculatorSample {

    public static void main(String[] args) {
        CharStream input = CharStreams.fromString("12 * 2 + 13");
        CalculatorLexer lexer = new CalculatorLexer(input);
        CommonTokenStream stream = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(stream);
        CalculatorParser.ExprContext context = parser.expr();

        CalculatorAddVisitor visitor = new CalculatorAddVisitor();
        visitor.visit(context);
    }

    static class CalculatorAddVisitor extends CalculatorBaseVisitor<Void> {
        @Override
        public Void visitAdd(CalculatorParser.AddContext ctx) {
            String arg0 = ctx.expr(0).getText();
            String arg1 = ctx.expr(1).getText();
            System.out.println("Calculator add: [" + arg0 + ", " + arg1 + "]");
            return super.visitAdd(ctx);
        }
    }
}
